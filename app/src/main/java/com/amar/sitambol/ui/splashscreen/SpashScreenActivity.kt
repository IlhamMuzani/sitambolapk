package com.amar.sitambol.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.amar.sitambol.R
import com.amar.sitambol.data.database.PrefManager
import com.amar.sitambol.ui.login.LoginActivity
import com.amar.sitambol.ui.main.MainActivity

class SpashScreenActivity : AppCompatActivity() {
    private lateinit var prefManager: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        prefManager = PrefManager(this)

        Handler().postDelayed({
//            if (!prefManager.prefLogin) {
                startActivity(Intent(this, LoginActivity::class.java))
//            } else {
//                startActivity(Intent(this, MainActivity::class.java))
//            }
            finish()
        }, 2000)
    }
}