package com.amar.sitambol.ui.profile

import com.amar.sitambol.data.database.PrefManager

interface ProfileContract {
    interface Presenter {
        fun doLogin(prefManager: PrefManager)
        fun doLogout(prefManager: PrefManager)
    }

    interface View {
        fun initFragment(view: android.view.View)
        fun onResultLogin(prefManager: PrefManager)
        fun onResultLogout()
        fun showMessage(message: String)
    }
}