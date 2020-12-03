package com.mzd.myapp.data.base;

import android.util.Log;

import androidx.annotation.NonNull;
import com.mzd.myapp.utils.* ;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseCoordinator {
    private static final String TAG = BaseCoordinator.class.getSimpleName();
    private static int uid = 0;
    public final SingleLiveEvent<ActivityParams> activityParams = new SingleLiveEvent<>();
    public final SingleLiveEvent<Boolean> finishCurrentActivity = new SingleLiveEvent<>();
    // LOCAL to each coordinator
    protected String state = null;
    private final List<ActiveState> activeStates = new ArrayList<>();
    private final Map<String, ActivityParams> registry = new HashMap<>();

    public abstract void onNewState(String newState, String oldState);

    @SuppressWarnings("unused")
    public void resetAll() {
        //state = null;
        activeStates.clear();
        uid = 0;
    }

    public void removeActiveActivity(int uid) {
        Log.d(TAG, "Should remove the activity with uid=" + uid);

        ActiveState activeStateToRemove = null;
        for (ActiveState activeState : activeStates) {
            if (activeState.getUid() == uid) {
                activeStateToRemove = activeState;
                break;
            }
        }

        if (activeStateToRemove != null) {
            activeStates.remove(activeStateToRemove);
        }
    }

    protected void popCurrentAndPushNewActivity(@NonNull String to) {
        popCurrentAndPushNewActivity(to, null);
    }

    protected void popCurrentAndPushNewActivity(@NonNull String to, String params) {
        pushNewActivity(to, params);
        finishCurrentActivity();
    }


    protected void pushNewActivity(String to) {
        pushNewActivity(to, null);
    }

    protected void pushNewActivity(String to, String params) {
        ActivityParams activityParams = registry.get(to);

        if (activityParams != null) {
            uid++;

            activityParams.params = params;
            activityParams.uid = uid;

            this.activityParams.postValue(activityParams);

            activeStates.add(new ActiveState(to, uid));
            stackPush(to);
        } else {
            Log.w(TAG, "No activity registered for path '" + to + "'");
        }
    }

    public void backRequested() {
        finishCurrentActivity();
    }

    public void finishCurrentActivity() {
        finishCurrentActivity.postValue(Boolean.TRUE);
    }

    /**
     * Registers a route for this coordinator.
     *
     * @param key    the route to launch
     * @param params the mapped activity and launch parameters
     */
    protected void register(String key, ActivityParams params) {
        registry.put(key, params);
    }

    /**
     * by default, finishes the current activity and updates the stack. Override in dedicated
     * coordinator if this behaviour is not the expected one.
     */
    public void onBackPressed() {

        // And reflect in the view
        finishCurrentActivity();
    }

    private void stackPush(String newState) {
        if (newState != null) {
            // absolutePathSize++;

            String oldState = state;

            // Update the state to the new one
            state = newState;

            // Notify we are going to the next state
            onNewState(newState, oldState);
        }
    }

}
