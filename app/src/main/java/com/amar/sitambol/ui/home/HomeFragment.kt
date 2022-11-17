package com.amar.sitambol.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.amar.sitambol.R
import com.amar.sitambol.ui.bengkel.all.BengkelAllActivity
import com.amar.sitambol.ui.kecamatan.KecamatanActivity
import com.amar.sitambol.ui.kelurahan.all.KelurahanAllActivity
import com.amar.sitambol.ui.penggunaan.PenggunaanActivity
import com.amar.sitambol.ui.tentang.TentangActivity

class HomeFragment : Fragment(), HomeContract.View {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        initFragment(view)

        return view
    }

    @SuppressLint("SetTextI18n")
    override fun initFragment(view: View) {
        val tvTitle = view.findViewById<TextView>(R.id.tv_title)
        val ivBack = view.findViewById<ImageView>(R.id.iv_back)

        val cvKecamatan = view.findViewById<CardView>(R.id.cv_kecamatan)
        val cvKelurahan = view.findViewById<CardView>(R.id.cv_kelurahan)
        val cvBengkel = view.findViewById<CardView>(R.id.cv_bengkel)
        val cvPenggunaan = view.findViewById<CardView>(R.id.cv_penggunaan)
        val tvTentang = view.findViewById<TextView>(R.id.tv_tentang)

        tvTitle.text = "Dashboard"
        ivBack.visibility = View.GONE

        cvKecamatan.setOnClickListener {
            startActivity(Intent(requireActivity(), KecamatanActivity::class.java))
        }

        cvKelurahan.setOnClickListener {
            startActivity(Intent(requireActivity(), KelurahanAllActivity::class.java))
        }

        cvBengkel.setOnClickListener {
            startActivity(Intent(requireActivity(), BengkelAllActivity::class.java))
        }

        cvPenggunaan.setOnClickListener {
            startActivity(Intent(requireActivity(), PenggunaanActivity::class.java))
        }

        tvTentang.setOnClickListener {
            startActivity(Intent(requireActivity(), TentangActivity::class.java))
        }
    }
}