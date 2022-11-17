package com.amar.sitambol.ui.kecamatan

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import com.amar.sitambol.R
import com.amar.sitambol.data.model.kecamatan.DataKecamatan
import com.amar.sitambol.data.model.kecamatan.ResponseKecamatan
import com.amar.sitambol.util.sweetalert.SweetAlertDialog
import kotlinx.android.synthetic.main.activity_tempat.*
import kotlinx.android.synthetic.main.toolbar.*

class KecamatanActivity : AppCompatActivity(), KecamatanContract.View{

    lateinit var presenter: KecamatanPresenter

    private lateinit var sLoading: SweetAlertDialog
    private lateinit var sSuccess: SweetAlertDialog
    private lateinit var sError: SweetAlertDialog
    private lateinit var sAlert: SweetAlertDialog

    private lateinit var kecamatanAdapter: KecamatanAdapter
    private lateinit var kecamatan: DataKecamatan

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tempat)

        presenter = KecamatanPresenter(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.kecamatanList()
    }

    @SuppressLint("SetTextI18n")
    override fun initActivity() {
        tv_title.text = "Pilih Kecamatan"

        sLoading = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
        sSuccess = SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE).setTitleText("Berhasil")
        sError = SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE).setTitleText("Gagal!")
        sAlert = SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE).setTitleText("Perhatian!")

        val layoutManager = LinearLayoutManager(applicationContext)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        kecamatanAdapter = KecamatanAdapter(this, ArrayList())
        rv_tempat.adapter = kecamatanAdapter
        rv_tempat.layoutManager = layoutManager
    }

    override fun initListener() {
        iv_back.setOnClickListener {
            onBackPressed()
        }
        et_search.setOnEditorActionListener { _, i, _ ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                presenter.kecamatanSearch(et_search.text.toString())
                true
            } else {
                false
            }
        }
    }

    override fun onLoading(loading: Boolean, message: String?) {
        when (loading) {
            true -> sLoading.setTitleText(message).show()
            false -> sLoading.dismiss()
        }
    }

    override fun onResult(responseKecamatan: ResponseKecamatan) {
        val status: Boolean = responseKecamatan.status
        val message: String = responseKecamatan.message!!

        if (status) {
            val kecamatan: List<DataKecamatan> = responseKecamatan.kecamatan!!
            sv_tempat.visibility = View.VISIBLE
            layout_empty.visibility = View.GONE
            kecamatanAdapter.setData(kecamatan)
        } else {
            sv_tempat.visibility = View.GONE
            layout_empty.visibility = View.VISIBLE
        }
    }

    override fun showError(message: String) {

    }
}