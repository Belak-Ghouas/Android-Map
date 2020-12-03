package com.mzd.myapp.data.storage.sharedpreferences.sharedpreferences;

import android.content.Context;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mzd.myapp.R;
import com.mzd.myapp.data.models.LoginHistory;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;


/**
 * Created by cdebast on 30/01/2018.
 */

@SuppressWarnings("SpellCheckingInspection")
public class AppSharedPreferences extends CorePreferences {
    private static final String LOGIN_HISTORY = "key:login_history";

    public AppSharedPreferences(final Context context) {
        super(context);
    }

    public static List<LoginHistory> getLoginHistory(final Context context) {
        String serializedHistory = getString(context, LOGIN_HISTORY);
        List<LoginHistory> list = null;

        if (serializedHistory != null) {
            Type listType = new TypeToken<ArrayList<LoginHistory>>() {
            }.getType();

            list = new Gson().fromJson(serializedHistory, listType);

            // Now, we want to order them by: last login is first.
            Collections.sort(list, new Comparator<LoginHistory>() {
                @Override
                public int compare(LoginHistory o1, LoginHistory o2) {
                    return (int) (o2.getLoginDate().getTime() - o1.getLoginDate().getTime());
                }
            });
        }

        return list;
    }

    public static void addOrUpdateLoginHistory(final Context context, final LoginHistory history) {
        long historyDepth = context.getResources().getInteger(R.integer.login_history_depth);

        List<LoginHistory> list = getLoginHistory(context);

        if (list != null) {
            // Check if we already have logged this person. If so, the login date has to be updated, else, just add.
            for (Iterator<LoginHistory> iterator = list.iterator(); iterator.hasNext(); ) {
                LoginHistory historyItem = iterator.next();
                if (historyItem.getLoginString().equalsIgnoreCase(history.getLoginString())) {
                    // Remove the current element from the iterator and the list.
                    iterator.remove();
                    break;
                }
            }

            // Now, we can safely add it.
            list.add(0, history);

            if (list.size() > historyDepth) {
                list = list.subList(0, (int) historyDepth);
            }
        } else {
            list = new ArrayList<>();
            list.add(history);
        }

        // Last thing, serialize and store.
        Gson gson = new GsonBuilder().create();
        String serializedString = gson.toJson(list);

        setAndApply(context, LOGIN_HISTORY, serializedString);
    }

}
