package com.amar.sitambol.ui.tentang

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.amar.sitambol.R
import kotlinx.android.synthetic.main.toolbar.*

class TentangActivity : AppCompatActivity(), TentangContract.View {

    lateinit var presenter: TentangPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tentang)

        presenter = TentangPresenter(this)
    }

    @SuppressLint("SetTextI18n")
    override fun initActivity() {
        tv_title.text = "Tentang Aplikasi"
    }

    override fun initListener() {
        iv_back.setOnClickListener {
            onBackPressed()
        }
    }
}