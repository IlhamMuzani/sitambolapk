package com.amar.sitambol.ui.kecamatan

import com.amar.sitambol.data.model.kecamatan.ResponseKecamatan

interface KecamatanContract {
    interface Presenter {
        fun kecamatanList()
        fun kecamatanSearch(keyword: String)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean, message: String? = "Loading...")
        fun onResult(responseKecamatan: ResponseKecamatan)
        fun showError(message: String)
    }

}