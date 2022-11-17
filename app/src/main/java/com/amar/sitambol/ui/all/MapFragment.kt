package com.amar.sitambol.ui.all

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.amar.sitambol.R
import com.amar.sitambol.data.model.bengkel.DataBengkel
import com.amar.sitambol.data.model.bengkel.ResponseBengkelList
import com.amar.sitambol.util.sweetalert.SweetAlertDialog
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(), BengkelMapContract.View, OnMapReadyCallback {

    lateinit var presenter: BengkelMapPresenter

    private lateinit var sLoading: SweetAlertDialog
    private lateinit var bengkel: List<DataBengkel>

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var arrayLatLng: ArrayList<LatLng>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_map, container, false)

        presenter = BengkelMapPresenter(this)

        initFragment(view)

        return view
    }

    override fun onStart() {
        super.onStart()
        presenter.bengkelAll()
    }

    @SuppressLint("SetTextI18n")
    override fun initFragment(view: View) {
        sLoading = SweetAlertDialog(requireActivity(), SweetAlertDialog.PROGRESS_TYPE)

        val tvTitle = view.findViewById<TextView>(R.id.tv_title)
        val ivBack = view.findViewById<ImageView>(R.id.iv_back)

        tvTitle.text = "Cari Cepat"
        ivBack.visibility = View.GONE
    }

    override fun onResult(responseBengkelList: ResponseBengkelList) {
        bengkel = responseBengkelList.bengkel!!

        arrayLatLng = ArrayList()

        for (b in bengkel) {
            arrayLatLng!!.add(LatLng(b.latitude!!.toDouble(), b.longitude!!.toDouble()))
        }

        val mapFragment =
            childFragmentManager.findFragmentById(R.id.fragment_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    override fun onLoading(loading: Boolean, message: String?) {
        when (loading) {
            true -> sLoading.setTitleText(message).show()
            false -> sLoading.dismiss()
        }
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.isMyLocationEnabled = true

        for (i in arrayLatLng!!.indices) {
            googleMap.addMarker(
                MarkerOptions().position(arrayLatLng!![i]).title(bengkel[i].nama)
            )
        }

        fusedLocationProviderClient.lastLocation.addOnSuccessListener(requireActivity()) { location ->
            if (location != null) {
                val currentLatLng = LatLng(location.latitude, location.longitude)
                googleMap.addMarker(MarkerOptions().position(currentLatLng).title("Lokasi Anda"))
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 13f))
            }
        }
    }
}