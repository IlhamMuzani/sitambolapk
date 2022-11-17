package com.amar.sitambol.ui.bengkel.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.amar.sitambol.R
import com.amar.sitambol.data.Constant
import com.amar.sitambol.data.model.bengkel.DataBengkel
import com.amar.sitambol.data.model.bengkel.ResponseBengkelDetail
import com.amar.sitambol.data.model.gambar.DataGambar
import com.amar.sitambol.ui.bengkel.BengkelSliderAdapter
import com.amar.sitambol.util.sweetalert.SweetAlertDialog
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_bengkel_detail.*
import kotlinx.android.synthetic.main.toolbar.*

class BengkelDetailActivity : AppCompatActivity(), BengkelDetailContract.View, OnMapReadyCallback {

    lateinit var presenter: BengkelDetailPresenter

    private lateinit var sLoading: SweetAlertDialog
    private lateinit var sSuccess: SweetAlertDialog
    private lateinit var sError: SweetAlertDialog
    private lateinit var sAlert: SweetAlertDialog

    private lateinit var dots: ArrayList<TextView>

    private lateinit var bengkel: DataBengkel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bengkel_detail)

        presenter = BengkelDetailPresenter(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.bengkelDetail(Constant.BENGKEL_ID)
    }

    @SuppressLint("SetTextI18n")
    override fun initActivity() {
        tv_title.text = "Detail Bengkel"

        sLoading = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
        sSuccess = SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE).setTitleText("Berhasil")
        sError = SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE).setTitleText("Gagal!")
        sAlert = SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE).setTitleText("Perhatian!")

//        adapter = BengkelSliderAdapter(this, list)
//        view_pager.adapter = adapter
//        dots = ArrayList()
//
//        view_pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                selectDot(position)
//                super.onPageSelected(position)
//            }
//        })
    }

    override fun initListener() {
        iv_back.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onLoading(loading: Boolean, message: String?) {
        when (loading) {
            true -> sLoading.setTitleText(message).show()
            false -> sLoading.dismiss()
        }
    }

    override fun onResult(responseBengkelDetail: ResponseBengkelDetail) {
        bengkel = responseBengkelDetail.bengkel!!

        tv_nama.text = bengkel.nama
        tv_fasilitas.text = bengkel.fasilitas
        tv_keterangan.text = bengkel.keterangan

        val arrayGambar = ArrayList<DataGambar>()
        arrayGambar.addAll(bengkel.gambar!!)

        val adapterSlider = BengkelSliderAdapter(this, arrayGambar)
        view_pager.adapter = adapterSlider

        if (responseBengkelDetail.status) {
            val mapFragment =
                supportFragmentManager.findFragmentById(R.id.fragment_map) as SupportMapFragment
            mapFragment.getMapAsync(this)
        }
    }

    override fun showError(message: String) {

    }

    override fun onMapReady(googleMap: GoogleMap) {
        val latLng = LatLng(bengkel.latitude!!.toDouble(), bengkel.longitude!!.toDouble())
        googleMap.addMarker(
            MarkerOptions().position(latLng).title("${bengkel.nama} - ${bengkel.nama}")
        )
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14f))
    }
}