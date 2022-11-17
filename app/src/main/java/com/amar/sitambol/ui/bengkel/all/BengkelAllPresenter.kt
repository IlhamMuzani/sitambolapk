package com.amar.sitambol.ui.bengkel.all

import com.amar.sitambol.data.model.bengkel.ResponseBengkelList
import com.amar.sitambol.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BengkelAllPresenter(val view: BengkelAllContract.View) : BengkelAllContract.Presenter {
    init {
        view.initActivity()
        view.initListener()
    }

    override fun bengkelAll() {
        view.onLoading(true, "Menampilkan Bengkel...")
        ApiService.endPoint.bengkelAll().enqueue(object : Callback<ResponseBengkelList> {
            override fun onResponse(
                call: Call<ResponseBengkelList>,
                response: Response<ResponseBengkelList>
            ) {
                view.onLoading(false)
                if (response.isSuccessful) {
                    val responseBengkelList: ResponseBengkelList? = response.body()
                    view.onResultList(responseBengkelList!!)
                }
            }

            override fun onFailure(call: Call<ResponseBengkelList>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }

    override fun bengkelSearch(keyword: String) {
        view.onLoading(true, "Mencari Bengkel...")
        ApiService.endPoint.bengkelSearch(keyword).enqueue(object : Callback<ResponseBengkelList> {
            override fun onResponse(
                call: Call<ResponseBengkelList>,
                response: Response<ResponseBengkelList>
            ) {
                view.onLoading(false)
                if (response.isSuccessful) {
                    val responseBengkelList: ResponseBengkelList? = response.body()
                    view.onResultSearch(responseBengkelList!!)
                }
            }

            override fun onFailure(call: Call<ResponseBengkelList>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }
}