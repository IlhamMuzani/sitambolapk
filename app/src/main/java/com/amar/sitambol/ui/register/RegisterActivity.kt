package com.amar.sitambol.ui.register

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.amar.sitambol.R
import com.amar.sitambol.data.model.user.ResponseUser
import com.amar.sitambol.ui.login.LoginActivity
import com.amar.sitambol.util.sweetalert.SweetAlertDialog
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterContract.View {

    lateinit var presenter: RegisterPresenter

    private lateinit var sLoading: SweetAlertDialog
    private lateinit var sSuccess: SweetAlertDialog
    private lateinit var sError: SweetAlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        presenter = RegisterPresenter(this)
    }

    override fun initActivity() {
        sLoading = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
        sSuccess = SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE).setTitleText("Berhasil")
        sError = SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE).setTitleText("Gagal!")
    }

    override fun initListener() {
        btn_register.setOnClickListener {
            val name = et_name.text
            val email = et_email.text
            val password = et_password.text
            val passwordConfirmation = et_password_confirmation.text
            when {
                name!!.isEmpty() -> {
                    validationError(et_name, "Username tidak boleh kosong!")
                }
                email!!.isEmpty() -> {
                    validationError(et_email, "Email tidak boleh kosong!")
                }
                password!!.isEmpty() -> {
                    validationError(et_password, "Password tidak boleh kosong!")
                }
                passwordConfirmation!!.isEmpty() -> {
                    validationError(
                        et_password_confirmation,
                        "Konfirmasi password tidak boleh kosong!"
                    )
                }
                else -> {
                    presenter.userRegister(
                        name.toString(),
                        email.toString(),
                        password.toString(),
                        passwordConfirmation.toString(),
                    )
                }
            }
        }

        btn_to_login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    override fun onLoading(loading: Boolean, message: String?) {
        when (loading) {
            true -> sLoading.setTitleText(message).show()
            false -> sLoading.dismiss()
        }
    }

    override fun onResult(responseUser: ResponseUser) {
        val status: Boolean = responseUser.status
        val message: String = responseUser.message!!

        if (status) {
            showAlertSuccess(message)
        } else {
            showAlertError(message)
        }
    }

    override fun showAlertSuccess(message: String) {
        sSuccess
            .setContentText(message)
            .setConfirmText("OK")
            .setConfirmClickListener {
                it.dismissWithAnimation()
                finish()
                startActivity(Intent(this, LoginActivity::class.java))
            }
            .show()
    }

    override fun showAlertError(message: String) {
        sError
            .setContentText(message)
            .setConfirmText("OK")
            .setConfirmClickListener {
                it.dismiss()
            }
            .show()
    }

    override fun validationError(editText: EditText, message: String) {
        editText.error = message
        editText.requestFocus()
    }
}