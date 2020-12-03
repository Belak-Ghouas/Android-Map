package com.mzd.myapp.v2.data.features.maintenance;

import android.os.Build;

import com.mzd.myapp.app.MyMAppTest;
import com.mzd.myapp.data.base.live.NetworkConfig;


import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(sdk = Build.VERSION_CODES.O_MR1, application = MyMAppTest.class)
@PrepareForTest({NetworkConfig.class})
@RunWith(RobolectricTestRunner.class)
public class MaintenanceRepositoryTest {

//    private Context context;
//    private int resp_success = 0;
//    private int resp_exception = 0;
//    private int resp_error = 0;
//    private int onAny = 0;
//    private int errorCode = -1;
//    private String errorMessage = null;
//    private User user = new User();
//
//    @Before
//    public void init() {
//        resp_success = 0;
//        resp_exception = 0;
//        resp_error = 0;
//        onAny = 0;
//        errorCode = -1;
//        errorMessage = null;
//        context = ApplicationProvider.getApplicationContext();
//    }
//
//    @After
//    public void tearDown() {
//        MockCallGlobalConfig.getInstance().reset();
//    }
//
//    @Test
//    public void getMaintenanceDetails_success() {
//        MockServiceConfig mockServiceConfig = new MockServiceConfig(context,
//                "maintenance",
//                MockServiceConfig.MockMode.MOCK_SYNC);
//
//        MaintenanceServiceCall maintenanceServiceCall = new MaintenanceServiceCall(new NetworkConfig(), mockServiceConfig);
//
//        replayAll();
//
//        MaintenanceRepository maintenanceRepository = new MaintenanceRepository(maintenanceServiceCall, user);
//        maintenanceRepository.getMaintenanceDetails(new MaintenanceRepository.OnMaintenanceResponse() {
//
//            @Override
//            public void onMaintenanceError(int errorCode, @Nullable String errorMessage) {
//                resp_error++;
//                MaintenanceRepositoryTest.this.errorCode = errorCode;
//                MaintenanceRepositoryTest.this.errorMessage = errorMessage;
//            }
//
//            @Override
//            public void onAny() {
//                onAny++;
//            }
//
//            @Override
//            public void onError(@NotNull Throwable t) {
//                resp_exception++;
//            }
//
//            @Override
//            public void onMaintenance(@NotNull Maintenance maintenance) {
//                resp_success++;
//            }
//        });
//
//        Assert.assertEquals(1, onAny);
//        Assert.assertEquals(0, resp_exception);
//        Assert.assertEquals(0, resp_error);
//        Assert.assertEquals(1, resp_success);
//        Assert.assertEquals(-1, errorCode);
//        Assert.assertNull(errorMessage);
//    }
//
//    @Test
//    public void getMaintenanceDetails_nullCallback() {
//        MockServiceConfig mockServiceConfig = new MockServiceConfig(context,
//                "maintenance",
//                MockServiceConfig.MockMode.MOCK_SYNC);
//
//        MaintenanceServiceCall maintenanceServiceCall = new MaintenanceServiceCall(new NetworkConfig(), mockServiceConfig);
//
//        MaintenanceRepository maintenanceRepository = new MaintenanceRepository(maintenanceServiceCall, user);
//
//        replayAll();
//
//        maintenanceRepository.getMaintenanceDetails(null);
//
//        Assert.assertEquals(0, onAny);
//        Assert.assertEquals(0, resp_exception);
//        Assert.assertEquals(0, resp_error);
//        Assert.assertEquals(0, resp_success);
//        Assert.assertEquals(-1, errorCode);
//        Assert.assertNull(errorMessage);
//
//        Assert.assertNotNull(maintenanceRepository.getMaintenance().getValue());
//    }
//
//    @Test
//    public void getMaintenanceDetails_alreadyInCache() {
//        MockServiceConfig mockServiceConfig = new MockServiceConfig(context,
//                "maintenance",
//                MockServiceConfig.MockMode.MOCK_SYNC);
//
//        MaintenanceServiceCall maintenanceServiceCall = new MaintenanceServiceCall(new NetworkConfig(), mockServiceConfig);
//        MaintenanceRepository maintenanceRepository = new MaintenanceRepository(maintenanceServiceCall, user);
//
//        Maintenance maintenance = EasyMock.createMock(Maintenance.class);
//        maintenanceRepository.getMaintenance().setValue(maintenance);
//
//        replayAll();
//
//        maintenanceRepository.getMaintenanceDetails(new MaintenanceRepository.OnMaintenanceResponse() {
//
//            @Override
//            public void onMaintenanceError(int errorCode, @Nullable String errorMessage) {
//                resp_error++;
//                MaintenanceRepositoryTest.this.errorCode = errorCode;
//                MaintenanceRepositoryTest.this.errorMessage = errorMessage;
//            }
//
//            @Override
//            public void onAny() {
//                onAny++;
//            }
//
//            @Override
//            public void onError(@NotNull Throwable t) {
//                resp_exception++;
//            }
//
//            @Override
//            public void onMaintenance(@NotNull Maintenance maintenance) {
//                resp_success++;
//            }
//        });
//
//        Assert.assertEquals(1, onAny);
//        Assert.assertEquals(0, resp_exception);
//        Assert.assertEquals(0, resp_error);
//        Assert.assertEquals(1, resp_success);
//        Assert.assertEquals(-1, errorCode);
//        Assert.assertNull(errorMessage);
//    }
//
//    @Test
//    public void getMaintenanceDetails_error() {
//        MockServiceConfig mockServiceConfig = new MockServiceConfig(context,
//                "maintenance",
//                MockServiceConfig.MockMode.MOCK_SYNC);
//
//        MockCallGlobalConfig.getInstance().setSuffix("_error404");
//        MaintenanceServiceCall maintenanceServiceCall = new MaintenanceServiceCall(new NetworkConfig(), mockServiceConfig);
//
//        replayAll();
//
//        MaintenanceRepository maintenanceRepository = new MaintenanceRepository(maintenanceServiceCall, user);
//        maintenanceRepository.getMaintenanceDetails(new MaintenanceRepository.OnMaintenanceResponse() {
//
//            @Override
//            public void onMaintenanceError(int errorCode, @Nullable String errorMessage) {
//                resp_error++;
//                MaintenanceRepositoryTest.this.errorCode = errorCode;
//                MaintenanceRepositoryTest.this.errorMessage = errorMessage;
//            }
//
//            @Override
//            public void onAny() {
//                onAny++;
//            }
//
//            @Override
//            public void onError(@NotNull Throwable t) {
//                resp_exception++;
//            }
//
//            @Override
//            public void onMaintenance(@NotNull Maintenance maintenance) {
//                resp_success++;
//            }
//        });
//
//        Assert.assertEquals(1, onAny);
//        Assert.assertEquals(0, resp_exception);
//        Assert.assertEquals(1, resp_error);
//        Assert.assertEquals(0, resp_success);
//        Assert.assertEquals(404, errorCode);
//        Assert.assertEquals("Not found", errorMessage);
//    }
//
//    @Test
//    public void onFailure_default() {
//        MockServiceConfig mockServiceConfig = new MockServiceConfig(context,
//                "maintenance",
//                MockServiceConfig.MockMode.MOCK_SYNC);
//
//        MaintenanceServiceCall maintenanceServiceCall = new MaintenanceServiceCall(new NetworkConfig(), mockServiceConfig);
//        MaintenanceRepository maintenanceRepository = new MaintenanceRepository(maintenanceServiceCall, user);
//
//        Exception e = new Exception("some exception");
//        Call<Maintenance> call = EasyMock.createMock(Call.class);
//        maintenanceRepository.setCallback(new MaintenanceRepository.OnMaintenanceResponse() {
//            @Override
//            public void onMaintenanceError(int errorCode, @Nullable String errorMessage) {
//                resp_error++;
//            }
//
//            @Override
//            public void onAny() {
//                onAny++;
//            }
//
//            @Override
//            public void onError(@NotNull Throwable t) {
//                resp_exception++;
//            }
//
//            @Override
//            public void onMaintenance(@NotNull Maintenance maintenance) {
//                resp_success++;
//            }
//        });
//
//        replayAll();
//
//        maintenanceRepository.onFailure(call, e);
//
//        Assert.assertEquals(1, onAny);
//        Assert.assertEquals(1, resp_exception);
//        Assert.assertEquals(0, resp_error);
//        Assert.assertEquals(0, resp_success);
//        Assert.assertEquals(-1, errorCode);
//        Assert.assertNull(errorMessage);
//    }
//
//    @Test
//    public void drop_default() {
//        MockServiceConfig mockServiceConfig = new MockServiceConfig(context,
//                "maintenance",
//                MockServiceConfig.MockMode.MOCK_SYNC);
//
//        MaintenanceServiceCall maintenanceServiceCall = new MaintenanceServiceCall(new NetworkConfig(), mockServiceConfig);
//        MaintenanceRepository maintenanceRepository = new MaintenanceRepository(maintenanceServiceCall, user);
//
//        Maintenance maintenance = EasyMock.createMock(Maintenance.class);
//        maintenanceRepository.getMaintenance().setValue(maintenance);
//
//        replayAll();
//
//        Assert.assertNotNull(maintenanceRepository.getMaintenance().getValue());
//        maintenanceRepository.drop();
//        Assert.assertNull(maintenanceRepository.getMaintenance().getValue());
//    }


}
