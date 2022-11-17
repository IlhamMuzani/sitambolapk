package com.amar.sitambol.ui.kelurahan

import com.amar.sitambol.data.model.kelurahan.ResponseKelurahan
import com.amar.sitambol.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KelurahanPresenter(val view: KelurahanContract.View) : KelurahanContract.Presenter {
    init {
        view.initActivity()
        view.initListener()
    }

    override fun kelurahanList(kecamatan_id: Long) {
        view.onLoading(true, "Menampilkan Kelurahan...")
        ApiService.endPoint.kelurahanList(kecamatan_id).enqueue(object : Callback<ResponseKelurahan> {
            override fun onResponse(
                call: Call<ResponseKelurahan>,
                response: Response<ResponseKelurahan>
            ) {
                view.onLoading(false)
                if (response.isSuccessful) {
                    val responseKelurahan: ResponseKelurahan? = response.body()
                    view.onResult(responseKelurahan!!)
                }
            }

            override fun onFailure(call: Call<ResponseKelurahan>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }

    override fun kelurahanSearch(keyword: String) {
        view.onLoading(true, "Mencari kelurahan...")
        ApiService.endPoint.kelurahanSearch(keyword).enqueue(object : Callback<ResponseKelurahan>{
            override fun onResponse(
                call: Call<ResponseKelurahan>,
                response: Response<ResponseKelurahan>
            ) {
                view.onLoading(false)
                if (response.isSuccessful) {
                    val responseKelurahan: ResponseKelurahan? = response.body()
                    view.onResult(responseKelurahan!!)
                }
            }

            override fun onFailure(call: Call<ResponseKelurahan>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }
}