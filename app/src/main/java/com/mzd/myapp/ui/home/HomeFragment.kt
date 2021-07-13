package com.mzd.myapp.ui.home


import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.mzd.myapp.R
import com.mzd.myapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import org.osmdroid.api.IMapController
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

class HomeFragment : BaseFragment() {

    private lateinit var homeViewModel: HomeViewModel
    private  lateinit var map:MapView
    private val latitude =36.7508896
    val longitude= 5.0567333

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        val ctx = context
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        map =root.findViewById<MapView>(R.id.mapview)
        initViews()
        return root
    }

    private fun initViews(){
        val tv = tv_tst
       initMap()

    }
    private  fun initMap(){
        map.setUseDataConnection(true)
        map.setTileSource(TileSourceFactory.MAPNIK)
        map.setUseDataConnection(true)
        map.setMultiTouchControls(true)
        val mapController: IMapController = map.controller

        //mapController.zoomTo(14, 1)
        mapController.setZoom(12.0)
        mapController.setCenter(GeoPoint(latitude,longitude))

        val mGpsMyLocationProvider = GpsMyLocationProvider(activity)
        val mLocationOverlay = MyLocationNewOverlay(mGpsMyLocationProvider, map)
        mLocationOverlay.enableMyLocation()
        mLocationOverlay.enableFollowLocation()
    }
}