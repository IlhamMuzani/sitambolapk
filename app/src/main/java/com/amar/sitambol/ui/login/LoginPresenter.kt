package com.amar.sitambol.ui.login

import com.amar.sitambol.data.database.PrefManager
import com.amar.sitambol.data.model.user.DataUser
import com.amar.sitambol.data.model.user.ResponseUser
import com.amar.sitambol.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(val view: LoginContract.View) : LoginContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
    }

    override fun doLogin(username: String, password: String) {
        view.onLoading(true)
        ApiService.endPoint.userLogin(username, password)
            .enqueue(object : Callback<ResponseUser> {
                override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                    view.onLoading(false)
                }

                override fun onResponse(
                    call: Call<ResponseUser>,
                    response: Response<ResponseUser>
                ) {
                    view.onLoading(false)
                    if (response.isSuccessful) {
                        val responseUser: ResponseUser? = response.body()
                        view.showMessage(responseUser!!.message!!)

                        if (responseUser.status) {
                            view.onResult(responseUser)
                        }
                    }
                }
            })
    }

    override fun setPrefs(prefManager: PrefManager, dataUser: DataUser) {
        prefManager.prefLogin = true
        prefManager.prefUsername = dataUser.username!!
        prefManager.prefEmail = dataUser.email!!
    }
}