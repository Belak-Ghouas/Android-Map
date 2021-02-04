package com.mzd.myapp.ui.base

import android.annotation.SuppressLint
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuBuilder
import com.mzd.myapp.R
import kotlinx.android.synthetic.main.custom_black_toolbar.*

abstract class BaseToolbarActivity<VM : BaseToolbarActivityInteraction> : BaseActivity<VM>(),
    View.OnClickListener {


    private var menu: Menu? = null
    var ivPSALogo: ImageView? = null
    var ivBackArrow: ImageView? = null
    var ivLogOut: ImageView? = null
    var toolbar_UserId: TextView?= null
    var tvScreenName: TextView? = null
    var tvUserName: TextView? = null

    @SuppressLint("RestrictedApi")
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        this.menu = menu

        if (menu is MenuBuilder) {
            val menuBuilder = menu
            menuBuilder.setOptionalIconsVisible(true)
        }
        return true
    }


    protected open fun toolbarShowHomeIcons(showLogo: Boolean) {
        ivPSALogo = findViewById(R.id.ic_logo)
        ivBackArrow = findViewById(R.id.toolbar_back)
        ivLogOut = findViewById(R.id.toolbar_Logout)
        tvUserName = findViewById(R.id.toolbar_UserId)
        //tvScreenName = findViewById(R.id.NameScreen)

        if (showLogo) {
            ivPSALogo?.visibility = View.VISIBLE
            ivLogOut?.visibility = View.VISIBLE
            tvUserName?.visibility = View.VISIBLE
            ivPSALogo!!.setOnClickListener(this)
            ivLogOut!!.setOnClickListener(this)
            tvUserName!!.setOnClickListener(this)
            ivBackArrow?.visibility = View.GONE
        } else {
            ivPSALogo?.visibility = View.GONE
            ivLogOut?.visibility = View.GONE
            tvUserName?.visibility = View.GONE
            ivBackArrow?.visibility = View.VISIBLE
            ivBackArrow!!.setOnClickListener(this)
        }

       // ivBackArrow?.visibility = if (showLogo) View.GONE else View.VISIBLE

        //ivLogOut?.visibility = if (showLogo) View.VISIBLE else View.GONE

        //ivScreenName?.visibility = if (showLogo) View.GONE else View.VISIBLE
    }

    protected open fun toolbarShowNameActivity(activityName:String?)
    {
        tvScreenName = findViewById(R.id.tvScreenName)
        if(activityName != null) {
            tvScreenName?.visibility = View.VISIBLE
            tvScreenName?.text = activityName
        } else {
            tvScreenName?.visibility = View.GONE
        }
    }
    protected open fun toolbarShowNameHomeActivity(activityName:String?)
    {
        toolbar_UserId = findViewById(R.id.toolbar_UserId)
        if(activityName != null) {
            toolbar_UserId?.visibility = View.VISIBLE
            toolbar_UserId?.text = activityName
        } else {
            toolbar_UserId?.visibility = View.GONE
        }
    }

    override fun onClick(v: View?) {
        //Toast.makeText(this, "test !",Toast.LENGTH_SHORT).show()
        if (R.id.toolbar_back == v?.id) {
            super.onBackPressed()
        }

        if (R.id.toolbar_Logout == v?.id) {

        }

        if(R.id.ic_menu==v?.id){
            openNavView()
        }


    }



    open fun openNavView(){

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // presenter.onOptionsItemSelected should return true if it's been handled. If not handled ('false'),
        // we want to leave it to the super class.
        return when (item.itemId) {
            android.R.id.home -> {
                // Get one activity back.
                viewModel.onMenuHome()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun initViews() {
        super.initViews()
        ic_menu.setOnClickListener(this)
    }

}
