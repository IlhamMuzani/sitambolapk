package com.amar.sitambol.ui.kecamatan

import com.amar.sitambol.data.model.kecamatan.ResponseKecamatan
import com.amar.sitambol.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KecamatanPresenter(val view: KecamatanContract.View): KecamatanContract.Presenter {
    init {
        view.initActivity()
        view.initListener()
    }

    override fun kecamatanList() {
        view.onLoading(true, "Menampilkan Kecamatan...")
        ApiService.endPoint.kecamatanList().enqueue(object : Callback<ResponseKecamatan>{
            override fun onResponse(
                call: Call<ResponseKecamatan>,
                response: Response<ResponseKecamatan>
            ) {
                view.onLoading(false)
                if (response.isSuccessful) {
                    val responseKecamatan: ResponseKecamatan? = response.body()
                    view.onResult(responseKecamatan!!)
                }
            }

            override fun onFailure(call: Call<ResponseKecamatan>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }

    override fun kecamatanSearch(keyword: String) {
        view.onLoading(true, "Mencari Kecamatan")
        ApiService.endPoint.kecamatanSearch(keyword).enqueue(object : Callback<ResponseKecamatan>{
            override fun onResponse(
                call: Call<ResponseKecamatan>,
                response: Response<ResponseKecamatan>
            ) {
                view.onLoading(false)
                if (response.isSuccessful){
                    val responseKecamatan: ResponseKecamatan? = response.body()
                    view.onResult(responseKecamatan!!)
                }
            }

            override fun onFailure(call: Call<ResponseKecamatan>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }
}