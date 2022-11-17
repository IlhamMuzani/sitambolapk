package com.amar.sitambol.ui.bengkel.all

import com.amar.sitambol.data.model.bengkel.ResponseBengkelList

interface BengkelAllContract {
    interface Presenter {
        fun bengkelAll()
        fun bengkelSearch(keyword: String)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean, message: String? = "Loading...")
        fun onResultList(responseBengkelList: ResponseBengkelList)
        fun onResultSearch(responseBengkelList: ResponseBengkelList)
        fun showError(message: String)
    }

}