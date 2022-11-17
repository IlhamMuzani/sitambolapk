package com.amar.sitambol.ui.register

import android.widget.EditText
import com.amar.sitambol.data.model.user.ResponseUser

interface RegisterContract {

    interface Presenter {
        fun userRegister(
            name: String,
            email: String,
            password: String,
            password_confirmation: String,
        )
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean, message: String? = "Loading")
        fun onResult(responseUser: ResponseUser)
        fun showAlertSuccess(message: String)
        fun showAlertError(message: String)
        fun validationError(editText: EditText, message: String)
    }

}