package com.amar.sitambol.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.amar.sitambol.R
import com.amar.sitambol.ui.all.BengkelMapActivity
import com.amar.sitambol.ui.bengkel.BengkelActivity
import com.amar.sitambol.ui.bengkel.all.BengkelAllActivity
import com.amar.sitambol.ui.kecamatan.KecamatanActivity
import com.amar.sitambol.ui.kelurahan.KelurahanActivity
import com.amar.sitambol.ui.kelurahan.all.KelurahanAllActivity
import com.amar.sitambol.ui.penggunaan.PenggunaanActivity
import com.amar.sitambol.ui.tentang.TentangActivity
import com.amar.sitambol.util.MapsHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}