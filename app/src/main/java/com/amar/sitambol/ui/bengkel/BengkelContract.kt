package com.amar.sitambol.ui.bengkel

import com.amar.sitambol.data.model.bengkel.ResponseBengkelList

interface BengkelContract {
    interface Presenter {
        fun bengkelList(kelurahan_id: Long)
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