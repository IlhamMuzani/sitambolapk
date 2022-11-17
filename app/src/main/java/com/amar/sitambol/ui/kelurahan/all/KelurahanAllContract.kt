package com.amar.sitambol.ui.kelurahan.all

import com.amar.sitambol.data.model.kelurahan.ResponseKelurahan

interface KelurahanAllContract {
    interface Presenter {
        fun kelurahanAll()
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