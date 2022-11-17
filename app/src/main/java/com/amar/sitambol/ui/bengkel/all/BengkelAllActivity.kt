package com.amar.sitambol.ui.bengkel.all

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import com.amar.sitambol.R
import com.amar.sitambol.data.model.bengkel.DataBengkel
import com.amar.sitambol.data.model.bengkel.ResponseBengkelList
import com.amar.sitambol.ui.bengkel.BengkelAdapter
import com.amar.sitambol.util.sweetalert.SweetAlertDialog
import kotlinx.android.synthetic.main.activity_tempat.*
import kotlinx.android.synthetic.main.toolbar.*

class BengkelAllActivity : AppCompatActivity(), BengkelAllContract.View{

    lateinit var presenter: BengkelAllPresenter

    private lateinit var sLoading: SweetAlertDialog
    private lateinit var sSuccess: SweetAlertDialog
    private lateinit var sError: SweetAlertDialog
    private lateinit var sAlert: SweetAlertDialog

    private lateinit var bengkelAdapter: BengkelAdapter
    private lateinit var bengkel: DataBengkel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bengkel)

        presenter = BengkelAllPresenter(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.bengkelAll()
    }

    @SuppressLint("SetTextI18n")
    override fun initActivity() {
        tv_title.text = "Daftar Bengkel & Tambal Ban"

        sLoading = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
        sSuccess = SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE).setTitleText("Berhasil")
        sError = SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE).setTitleText("Gagal!")
        sAlert = SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE).setTitleText("Perhatian!")

        val layoutManager = LinearLayoutManager(applicationContext)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        bengkelAdapter = BengkelAdapter(this, ArrayList())
        rv_tempat.adapter = bengkelAdapter
        rv_tempat.layoutManager = layoutManager
    }

    override fun initListener() {
        iv_back.setOnClickListener {
            onBackPressed()
        }
        et_search.setOnEditorActionListener { _, i, _ ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                presenter.bengkelSearch(et_search.text.toString())
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

    override fun onResultList(responseBengkelList: ResponseBengkelList) {
        val status: Boolean = responseBengkelList.status
        val message: String = responseBengkelList.message!!

        if (status) {
            val bengkel: List<DataBengkel> = responseBengkelList.bengkel!!
            layout_search.visibility = View.VISIBLE
            sv_tempat.visibility = View.VISIBLE
            layout_empty.visibility = View.GONE
            bengkelAdapter.setData(bengkel)
        } else {
            layout_search.visibility = View.GONE
            sv_tempat.visibility = View.GONE
            layout_empty.visibility = View.VISIBLE
        }
    }

    override fun onResultSearch(responseBengkelList: ResponseBengkelList) {
        val status: Boolean = responseBengkelList.status
        val message: String = responseBengkelList.message!!

        if (status) {
            val bengkel: List<DataBengkel> = responseBengkelList.bengkel!!
            layout_search.visibility = View.VISIBLE
            sv_tempat.visibility = View.VISIBLE
            layout_empty.visibility = View.GONE
            bengkelAdapter.setData(bengkel)
        } else {
            layout_search.visibility = View.GONE
            sv_tempat.visibility = View.GONE
            layout_empty.visibility = View.VISIBLE
        }
    }

    override fun showError(message: String) {

    }
}