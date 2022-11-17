package com.amar.sitambol.ui.bengkel.detail

import com.amar.sitambol.data.model.bengkel.ResponseBengkelDetail

interface BengkelDetailContract {
    interface Presenter {
        fun bengkelDetail(id: Long)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean, message: String? = "Loading...")
        fun onResult(responseBengkelDetail: ResponseBengkelDetail)
        fun showError(message: String)
    }

}