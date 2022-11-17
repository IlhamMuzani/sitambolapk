package com.amar.sitambol.ui.penggunaan

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.amar.sitambol.R
import kotlinx.android.synthetic.main.toolbar.*

class PenggunaanActivity : AppCompatActivity(), PenggunaanContract.View {

    lateinit var presenter: PenggunaanPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_penggunaan)

        presenter = PenggunaanPresenter(this)
    }

    @SuppressLint("SetTextI18n")
    override fun initActivity() {
        tv_title.text = "Penggunaan Aplikasi"
    }

    override fun initListener() {
        iv_back.setOnClickListener {
            onBackPressed()
        }
    }
}