package com.mzd.myapp.ui.home



import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.mzd.myapp.R
import com.mzd.myapp.databinding.ActivityHomeBinding
import com.mzd.myapp.ui.base.BaseToolbarActivity
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.java.KoinJavaComponent


class HomeActivity : BaseToolbarActivity<HomeActivityViewModel>(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // thanks to generics, viewModelImpl is of type MainActivityViewModelImpl, no cast needed
        viewModel = getViewModel()
        setContentView(R.layout.activity_home)

        // TODO Be Advised ! showBackButton() method should not be called here ! the decision must be performed from VM
        toolbarShowHomeIcons(true)
        toolbarShowNameHomeActivity(getString(R.string.HomeActivity))

        initViews()
        initObservers()

        viewModel.onActivityReady()

    }

    override fun initViews() {

    }
}

