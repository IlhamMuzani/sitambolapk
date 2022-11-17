package com.amar.sitambol.ui.kelurahan

import com.amar.sitambol.data.model.kelurahan.ResponseKelurahan

interface KelurahanContract {
    interface Presenter {
        fun kelurahanList(kecamatan_id: Long)
        fun kelurahanSearch(keyword: String)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean, message: String? = "Loading...")
        fun onResult(responseKelurahan: ResponseKelurahan)
        fun showError(message: String)
    }
}