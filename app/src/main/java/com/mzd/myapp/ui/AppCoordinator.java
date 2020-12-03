package com.mzd.myapp.ui;

import android.util.Log;


import com.mzd.myapp.data.base.ActivityParams;
import com.mzd.myapp.data.base.BaseCoordinator;

import com.mzd.myapp.ui.home.HomeActivity;
import com.mzd.myapp.ui.itineraryinformation.ItineraryInformationActivity;
import com.mzd.myapp.ui.login.LoginActivity;

import com.mzd.myapp.ui.register.RegisterActivity;
import com.mzd.myapp.ui.verifycontent.VerifyContentActivity;


public class AppCoordinator extends BaseCoordinator {

    private static final String TAG = AppCoordinator.class.getSimpleName();
    private static final String LOGIN_ACTIVITY_KEY = "loginActivity";
    private static final String HOME_ACTIVITY_KEY = "homeActivity";
    private static final String UNITY_ACTIVITY_KEY = "UnityActivity";
    private static final String CHECK_PALLET_ACTIVITY_KEY = "checkpaletActivity";
    private static final String CHECK_CONTAINER_ACTIVITY_KEY = "checkcontainerActivity";
    private static final String ITINERARYINFORMATION_ACTIVITY_KEY = "ItineraryInformationActivity";
    private static final String PALET_INFORMATION_TEMPORARY_Activity_KEY = "PaletInformationTemporaryActivity";
    private static final String VERIFY_CONTENT_ACTIVITY_KEY = "verifycontentActivity";
    private static final String CONTAINER_CONTENT_ACTIVITY_KEY = "ContainerContentActivity";

    //ME
    private  static  final String REGISTER_ACTIVITY_KEY="RegisterActivity";

    public AppCoordinator() {
        super();
        state = "splash";

        register(LOGIN_ACTIVITY_KEY, new ActivityParams(LoginActivity.class));
        register(HOME_ACTIVITY_KEY, new ActivityParams(HomeActivity.class));
        register(VERIFY_CONTENT_ACTIVITY_KEY ,new ActivityParams(VerifyContentActivity.class));
        register(ITINERARYINFORMATION_ACTIVITY_KEY, new ActivityParams(ItineraryInformationActivity.class));
        register(REGISTER_ACTIVITY_KEY,new ActivityParams(RegisterActivity.class));
    }


    @Override
    public void onNewState(final String newState, final String oldState) {
        Log.d(TAG, "Going from state " + oldState + " to " + newState);

        // Add here state transition specific stuff, related to StoreCoordinator
    }

    public void splashFinished() {
        popCurrentAndPushNewActivity(LOGIN_ACTIVITY_KEY);

    }
   /* public void logOut() {
        popCurrentAndPushNewActivity(LOGIN_ACTIVITY_KEY);

    }*/

    public void gotoHome() {
        pushNewActivity(HOME_ACTIVITY_KEY);
    }

    public void gotoCheckPallet() {
        pushNewActivity(CHECK_PALLET_ACTIVITY_KEY);
    }

    public void gotoCheckContainer() {
        pushNewActivity(CHECK_CONTAINER_ACTIVITY_KEY);
    }

    public void gotoPaletInfoTemporary(String palletID){
        pushNewActivity(PALET_INFORMATION_TEMPORARY_Activity_KEY,palletID);
    }
    public void gotoRegister(){
        pushNewActivity(REGISTER_ACTIVITY_KEY);
    }
}
