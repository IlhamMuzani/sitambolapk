package com.amar.sitambol.ui.login

import com.amar.sitambol.data.database.PrefManager
import com.amar.sitambol.data.model.user.DataUser
import com.amar.sitambol.data.model.user.ResponseUser

interface LoginContract {

    interface Presenter {
        fun doLogin(username: String, password: String)
        fun setPrefs(prefManager: PrefManager, dataUser: DataUser)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean, message: String? = "Loading...")
        fun onResult(responseUser: ResponseUser)
        fun showMessage(message: String)
    }

}