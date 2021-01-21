package com.mzd.myapp.ui.login

import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.ContextThemeWrapper
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.mzd.myapp.R
import com.mzd.myapp.data.models.LoginHistory
import com.mzd.myapp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class LoginActivity : BaseActivity<LoginActivityViewModel>(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // thanks to generics, viewModelImpl is of type MainActivityViewModelImpl, no cast needed
        viewModel = getViewModel()
        setContentView(R.layout.activity_login)


        initViews()
        initObservers()
        viewModel.onActivityReady(this)


    }


    override fun initObservers() {
        super.initObservers()

        viewModel.loginHistories.observe(this, Observer {
            handleHistoryList(it)
        })

        viewModel.login.observe(this, Observer {
            sign_in_login.setText(it)
        })

        viewModel.password.observe(this, Observer {
            sign_in_password.setText(it)
        })

    }

    override fun onClick(v: View?) {
        viewModel.onHistorySelected(v!!.tag as LoginHistory)
    }


  override fun initViews() {
        // Now, add the specific observers.

   //     tv_app_nameL.text="Optimize Loading"

        sign_in_login.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.onLoginChanged(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        sign_in_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.onPasswordChanged(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        btn_sign_in_button.setOnClickListener {
            viewModel.onLogin(this@LoginActivity)
        }

        iv_login_suggestions_icon.setOnClickListener {
            handleSuggestionToggle()
        }
    }

    private fun handleHistoryList(loginHistory: List<LoginHistory>?) {
        // Populate the list accordingly.
        if (loginHistory?.isNotEmpty() == true) {
            iv_login_suggestions_icon.visibility = VISIBLE
            ll_login_history_container.removeAllViews()

            for (historyLogin in loginHistory) {
                val tv = TextView(ContextThemeWrapper(this, R.style.loginHistoryItem), null, 0)
                tv.text = historyLogin.loginString
                tv.tag = historyLogin
                tv.setOnClickListener(this)
                val ll = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                ll.marginEnd = resources.getDimension(R.dimen.margin_normal).toInt()
                ll_login_history_container.addView(tv, ll)
            }
        } else {
            iv_login_suggestions_icon.visibility = GONE
        }
    }

    // This is controlled by the view, because not really a business decision.
    private fun handleSuggestionToggle() {
        val drawable: Drawable?

        if (history_scroller.visibility == VISIBLE) {
            // Must hide it
            history_scroller.visibility = GONE
            drawable = ContextCompat.getDrawable(this, R.drawable.ic_arrow_rotate_up)
        } else {
            // Must make it visible.
            history_scroller.visibility = VISIBLE
            drawable = ContextCompat.getDrawable(this, R.drawable.ic_arrow_rotate_down)
        }

        drawable?.let {
            it.setTint(ContextCompat.getColor(this, R.color.clickable_color))
            iv_login_suggestions_icon.setImageDrawable(it)

            val animator = ObjectAnimator.ofInt(it, "level", 0, 10000).setDuration(200)
            animator.start()
        }
    }
}

