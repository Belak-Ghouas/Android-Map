package com.mzd.myapp.ui.maintenance;

import android.os.Build;

import com.mzd.myapp.app.MyMAppTest;
import com.mzd.myapp.v2.data.features.maintenance.repositories.MaintenanceRepository;
import com.mzd.myapp.v2.data.features.maintenance.servermodels.Maintenance;

import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.powermock.api.easymock.PowerMock.createMock;

@Config(sdk = Build.VERSION_CODES.O_MR1, application = MyMAppTest.class)
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*"})
@PrepareForTest({MaintenanceRepository.class, Maintenance.class})
@RunWith(RobolectricTestRunner.class)
public class MaintenanceActivityViewModelImplTest {

//    // This is the key line that make PowerMock work along with Reobolectric
//    @Rule
//    public PowerMockRule rule = new PowerMockRule();
//
//    private MaintenanceActivityViewModelImpl maintenanceActivityViewModelImpl;
//    private MaintenanceRepository maintenanceRepository;
//
//    @Before
//    public void before() {
//        maintenanceRepository = createMock(MaintenanceRepository.class);
//        maintenanceActivityViewModelImpl = new MaintenanceActivityViewModelImpl(maintenanceRepository);
//    }
//
//    @After
//    public void after() {
//
//    }
//
//    @Test
//    public void fetchMaintenance_default() {
//
//        // When replaying, this method should be called, once only
//        maintenanceRepository.getMaintenanceDetails(maintenanceActivityViewModelImpl);
//
//        replayAll();
//
//        // Tests how the MV behaves when this method is called by the view.
//        maintenanceActivityViewModelImpl.fetchMaintenance();
//
//        // Check expected behaviour
//        Assert.assertTrue(maintenanceActivityViewModelImpl.getShowWait().getValue());
//    }
//
//    @Test
//    public void onMaintenance_default() {
//        Maintenance maintenance = createMock(Maintenance.class);
//
//        replayAll();
//
//        maintenanceActivityViewModelImpl.onMaintenance(maintenance);
//
//        // Check expected behaviour
//        Assert.assertEquals(maintenanceActivityViewModelImpl.getMaintenance().getValue(), maintenance);
//    }
//
//    @Test
//    public void onMaintenanceError_exception() {
//        final String SOME_ERROR_STRING = "Not found";
//        final int SOME_CODE = 404;
//
//        replayAll();
//
//        maintenanceActivityViewModelImpl.onMaintenanceError(SOME_CODE, SOME_ERROR_STRING);
//
//        // Check expected behaviour
//        Assert.assertEquals(maintenanceActivityViewModelImpl.getToastMessageString().getValue(), SOME_ERROR_STRING);
//    }
//
//    @Test
//    public void onError_exception() {
//        final String EXCEPTION_MESSAGE = "some exception";
//
//        Exception e = new Exception(EXCEPTION_MESSAGE);
//
//        replayAll();
//
//        maintenanceActivityViewModelImpl.onError(e);
//    }
//
//    @Test
//    public void onAny_default() {
//
//        // Force 'true' to insure this is being modified to false when 'onAny' is called.
//        maintenanceActivityViewModelImpl.showWait(true);
//
//        replayAll();
//
//        maintenanceActivityViewModelImpl.onAny();
//
//        // Check expected behaviour
//        // Wait indicator is removed from the view
//        Assert.assertFalse(maintenanceActivityViewModelImpl.getShowWait().getValue());
//    }
//
//    @Test
//    public void onClick_default() {
//
//        // No calls on mocked objects expected
//
//        replayAll();
//
//        // Call the method to test
//        maintenanceActivityViewModelImpl.onClick();
//
//        // Check expected behaviour
//        Assert.assertEquals(maintenanceActivityViewModelImpl.getToastMessageString().getValue(), "clicked!!");
//    }

}
