package com.amar.sitambol.ui.all

import com.amar.sitambol.data.model.bengkel.ResponseBengkelList

interface BengkelMapContract {
    interface Presenter {
        fun bengkelAll()
    }

    interface View {
        fun initFragment(view: android.view.View)
        fun onResult(responseBengkelList: ResponseBengkelList)
        fun onLoading(loading: Boolean, message: String? = "Loading...")
    }
}