package com.amar.sitambol.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.amar.sitambol.R
import com.amar.sitambol.data.database.PrefManager
import com.amar.sitambol.data.model.user.ResponseUser
import com.amar.sitambol.ui.main.MainActivity
import com.amar.sitambol.ui.register.RegisterActivity
import com.amar.sitambol.util.sweetalert.SweetAlertDialog
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View {

    lateinit var presenter: LoginPresenter
    private lateinit var prefManager: PrefManager

    private lateinit var sLoading: SweetAlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenter(this)
        prefManager = PrefManager(this)
    }

    override fun onStart() {
        super.onStart()
        if (prefManager.prefLogin) {
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun initActivity() {
        sLoading = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
    }

    override fun initListener() {
        btn_login.setOnClickListener {
            presenter.doLogin(
                et_email.text.toString(),
                et_password.text.toString(),
            )
        }
        btn_to_register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    override fun onLoading(loading: Boolean, message: String?) {
        when (loading) {
            true -> sLoading.setTitleText(message).show()
            false -> sLoading.dismiss()
        }
    }

    override fun onResult(responseUser: ResponseUser) {
        presenter.setPrefs(prefManager, responseUser.user!!)
        finish()
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}