package com.mzd.myapp.ui.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.mzd.myapp.app.MyMAppTest;

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

@RunWith(RobolectricTestRunner.class)
public class BaseActivityViewModelTest {

    // This is the key line that make PowerMock work along with Reobolectric
    @Rule
    public PowerMockRule rule = new PowerMockRule();
    private BaseActivityViewModel baseActivityViewModelImpl;

    @Before
    public void before() {


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



}
