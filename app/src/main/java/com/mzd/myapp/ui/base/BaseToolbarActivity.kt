package com.mzd.myapp.ui.base

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuBuilder
import com.mzd.myapp.R

abstract class BaseToolbarActivity<VM : BaseToolbarActivityViewModelImpl> : BaseActivity<VM>(),
    View.OnClickListener {

    // private val baseToolbarViewModel: BaseToolbarActivityViewModelImpl by viewModel()
    private var menu: Menu? = null
    var pres:VM?=null
    var ivPSALogo: ImageView? = null
    var ivBackArrow: ImageView? = null
    var ivLogOut: ImageView? = null
    var toolbar_UserId: TextView?=null
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

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menuItemsVisibility(menu)

        return super.onPrepareOptionsMenu(menu)
    }

    private fun menuItemsVisibility(menu: Menu) {

    }

    /**
     * @param showIcons show logo
     */
    protected open fun toolbarShowHomeIcons(showLogo: Boolean) {
        ivPSALogo = findViewById(R.id.ic_psa_logo)
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
           // super.onBackPressed()
            showLogoutConfirmation()
        }


    }

    open fun showLogoutConfirmation() {
        AlertDialog.Builder(this)
            .setTitle(R.string.logout_dialog_title)
            .setMessage(R.string.logout_dialog_message)
            .setPositiveButton(
                R.string.logout_dialog_button_yes,
                DialogInterface.OnClickListener { dialog, which -> pres?.logoutConfirmed() })

            .setNegativeButton(R.string.logout_dialog_button_no, null)
            .show()
    }


    protected fun setClientName() {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // presenter.onOptionsItemSelected should return true if it's been handled. If not handled ('false'),
        // we want to leave it to the super class.
        when (item.itemId) {
            android.R.id.home -> {
                // Get one activity back.
                viewModelImpl.onMenuHome()
                return true
            }
//            R.id.menu_action_settings -> {
//                viewModelImpl.onMenuSettings()
//                return true
//            }
//            R.id.menu_action_about -> {
//                viewModelImpl.onMenuAbout()
//                return true
//            }
            else -> return super.onOptionsItemSelected(item)
        }

    }

}
