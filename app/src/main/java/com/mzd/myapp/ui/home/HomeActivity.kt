package com.mzd.myapp.ui.home



import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.mzd.myapp.R
import com.mzd.myapp.databinding.ActivityHomeBinding
import com.mzd.myapp.ui.base.BaseToolbarActivity
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.java.KoinJavaComponent


class HomeActivity : BaseToolbarActivity<HomeActivityViewModelImpl>(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // thanks to generics, viewModelImpl is of type MainActivityViewModelImpl, no cast needed
        viewModelImpl = KoinJavaComponent.inject(HomeActivityViewModelImpl::class.java).value

        val binding: ActivityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.lifecycleOwner = this

        // TODO Be Advised ! showBackButton() method should not be called here ! the decision must be performed from VM
        toolbarShowHomeIcons(true)
        toolbarShowNameHomeActivity(getString(R.string.HomeActivity))

        initViews()
        initObservers()

        viewModelImpl.onActivityReady(this)

    }

    private fun initViews() {

       ib_check_palet_button.setOnClickListener {
            viewModelImpl.onCheckPalet(this@HomeActivity)
        }
        iv_logo_container.setOnClickListener {
            viewModelImpl.onOpenUnitySceneClicked()

        }
        ib_check_container.setOnClickListener {
            viewModelImpl.onCheckContainer(this@HomeActivity)
        }
    }
}

