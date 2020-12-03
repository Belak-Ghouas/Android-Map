package com.mzd.myapp.ui.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.mzd.myapp.app.MyMAppTest;
import com.mzd.myapp.ui.maintenance.MaintenanceActivity;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.powermock.api.easymock.PowerMock.createMock;
import static org.powermock.api.easymock.PowerMock.replayAll;

@Config(sdk = Build.VERSION_CODES.O_MR1, application = MyMAppTest.class)
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*"})
@PrepareForTest({BaseActivityViewModelImpl.SnackBarContent.class})
@RunWith(RobolectricTestRunner.class)
public class BaseActivityViewModelImplTest {

    // This is the key line that make PowerMock work along with Reobolectric
    @Rule
    public PowerMockRule rule = new PowerMockRule();
    private BaseActivityViewModelImpl baseActivityViewModelImpl;

    @Before
    public void before() {
        baseActivityViewModelImpl = new BaseActivityViewModelImpl();
    }

    @After
    public void after() {

    }

    @Test
    public void activityReady_default() {

        replayAll();

        baseActivityViewModelImpl.activityReady();

        // Nothing to test, this method is empty for the time being.
    }

    @Test
    public void finishActivity_default() {
        replayAll();

        baseActivityViewModelImpl.finishActivity();

        Assert.assertTrue(baseActivityViewModelImpl.getFinishActivity().getValue());
    }

    @Test
    public void navigateToActivity_noParamsNoFlags() {

        replayAll();

        baseActivityViewModelImpl.navigateToActivity(MaintenanceActivity.class);

        Assert.assertEquals(baseActivityViewModelImpl.getNavigateToActivity().getValue().getActivityToLaunch(), MaintenanceActivity.class);
        Assert.assertNull(baseActivityViewModelImpl.getNavigateToActivity().getValue().getBundle());
        Assert.assertEquals(baseActivityViewModelImpl.getNavigateToActivity().getValue().getFlags(), 0);
    }

    @Test
    public void navigateToActivity_withParamsNoFlags() {
        Bundle bundle = new Bundle();

        replayAll();

        baseActivityViewModelImpl.navigateToActivity(MaintenanceActivity.class, bundle);

        Assert.assertEquals(baseActivityViewModelImpl.getNavigateToActivity().getValue().getActivityToLaunch(), MaintenanceActivity.class);
        Assert.assertEquals(baseActivityViewModelImpl.getNavigateToActivity().getValue().getBundle(), bundle);
        Assert.assertEquals(baseActivityViewModelImpl.getNavigateToActivity().getValue().getFlags(), 0);
    }

    @Test
    public void navigateToActivity_withParamsWithFlags() {
        Bundle bundle = new Bundle();
        int flags = Intent.FLAG_ACTIVITY_SINGLE_TOP;

        replayAll();

        baseActivityViewModelImpl.navigateToActivity(MaintenanceActivity.class, bundle, flags);

        Assert.assertEquals(baseActivityViewModelImpl.getNavigateToActivity().getValue().getActivityToLaunch(), MaintenanceActivity.class);
        Assert.assertEquals(baseActivityViewModelImpl.getNavigateToActivity().getValue().getBundle(), bundle);
        Assert.assertEquals(baseActivityViewModelImpl.getNavigateToActivity().getValue().getFlags(), flags);
    }

    @Test
    public void showWait_showAndHide() {

        baseActivityViewModelImpl.showWait(true);
        Assert.assertTrue(baseActivityViewModelImpl.getShowWait().getValue());

        baseActivityViewModelImpl.showWait(false);
        Assert.assertFalse(baseActivityViewModelImpl.getShowWait().getValue());
    }

    @Test
    public void showToast_null() {

        baseActivityViewModelImpl.showToast(null);

        // Nothing to test
    }

    @Test
    public void showToast_someText() {
        final String toastMessage = "hi toast";
        replayAll();

        baseActivityViewModelImpl.showToast(toastMessage);

        Assert.assertEquals(baseActivityViewModelImpl.getToastMessageString().getValue(), toastMessage);
    }

    @Test
    public void showToast_res() {
        final Integer toastRes = 1234;

        replayAll();

        baseActivityViewModelImpl.showToast(toastRes);

        Assert.assertEquals(baseActivityViewModelImpl.getToastMessageRes().getValue(), toastRes);
    }

    @Test
    public void hideKeyboard_default() {

        replayAll();

        baseActivityViewModelImpl.hideKeyboard();
        Assert.assertTrue(baseActivityViewModelImpl.getHideKeyboard().getValue());
    }

    @Test
    public void showSnackBar_default() {

        BaseActivityViewModelImpl.SnackBarContent snackBarContent = createMock(BaseActivityViewModelImpl.SnackBarContent.class);

        replayAll();

        baseActivityViewModelImpl.showSnackBar(snackBarContent);

        Assert.assertEquals(baseActivityViewModelImpl.getSnackbarContent().getValue(), snackBarContent);
    }


}
