package com.amar.sitambol.ui.kelurahan.all

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import com.amar.sitambol.R
import com.amar.sitambol.data.model.kelurahan.DataKelurahan
import com.amar.sitambol.data.model.kelurahan.ResponseKelurahan
import com.amar.sitambol.ui.kelurahan.KelurahanAdapter
import com.amar.sitambol.util.sweetalert.SweetAlertDialog
import kotlinx.android.synthetic.main.activity_tempat.*
import kotlinx.android.synthetic.main.toolbar.*

class KelurahanAllActivity : AppCompatActivity(), KelurahanAllContract.View {

    lateinit var presenter: KelurahanAllPresenter

    private lateinit var sLoading: SweetAlertDialog
    private lateinit var sSuccess: SweetAlertDialog
    private lateinit var sError: SweetAlertDialog
    private lateinit var sAlert: SweetAlertDialog

    private lateinit var kelurahanAdapter: KelurahanAdapter
    private lateinit var kelurahan: DataKelurahan

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tempat)

        presenter = KelurahanAllPresenter(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.kelurahanAll()
    }

    @SuppressLint("SetTextI18n")
    override fun initActivity() {
        tv_title.text = "Pilih Kelurahan"
        layout_search.visibility = View.VISIBLE

        sLoading = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
        sSuccess = SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE).setTitleText("Berhasil")
        sError = SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE).setTitleText("Gagal!")
        sAlert = SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE).setTitleText("Perhatian!")

        val layoutManager = LinearLayoutManager(applicationContext)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        kelurahanAdapter = KelurahanAdapter(this, ArrayList())
        rv_tempat.adapter = kelurahanAdapter
        rv_tempat.layoutManager = layoutManager
    }

    override fun initListener() {
        iv_back.setOnClickListener {
            onBackPressed()
        }
        et_search.setOnEditorActionListener { _, i, _ ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                presenter.kelurahanSearch(et_search.text.toString())
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

    override fun onResult(responseKelurahan: ResponseKelurahan) {
        val status: Boolean = responseKelurahan.status
        val message: String = responseKelurahan.message!!

        if (status) {
            val kelurahan: List<DataKelurahan> = responseKelurahan.kelurahan!!
            layout_search.visibility = View.VISIBLE
            sv_tempat.visibility = View.VISIBLE
            layout_empty.visibility = View.GONE
            kelurahanAdapter.setData(kelurahan)
        } else {
            layout_search.visibility = View.GONE
            sv_tempat.visibility = View.GONE
            layout_empty.visibility = View.VISIBLE
        }
    }

    override fun showError(message: String) {

    }
}