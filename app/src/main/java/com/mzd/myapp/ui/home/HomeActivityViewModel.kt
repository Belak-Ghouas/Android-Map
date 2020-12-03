package com.mzd.myapp.ui.home

import android.content.Context
import com.mzd.myapp.ui.base.BaseToolbarActivityViewModel

interface HomeActivityViewModel : BaseToolbarActivityViewModel {
    fun onActivityReady(context: Context)
    fun onOpenUnitySceneClicked()
    fun  onCheckPalet(context: Context)
    fun  onCheckContainer(context: Context)

}