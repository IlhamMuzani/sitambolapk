package com.amar.sitambol.ui.bengkel.detail

import com.amar.sitambol.data.model.bengkel.ResponseBengkelDetail
import com.amar.sitambol.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BengkelDetailPresenter(val view: BengkelDetailContract.View) :
    BengkelDetailContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
    }

    override fun bengkelDetail(id: Long) {
        view.onLoading(true, "Mendapatkan Data Bengkel...")
        ApiService.endPoint.bengkelDetail(id).enqueue(object : Callback<ResponseBengkelDetail> {
            override fun onResponse(
                call: Call<ResponseBengkelDetail>,
                response: Response<ResponseBengkelDetail>
            ) {
                view.onLoading(false)
                if (response.isSuccessful){
                    val responseBengkelDetail: ResponseBengkelDetail? = response.body()
                    view.onResult(responseBengkelDetail!!)
                }
            }

            override fun onFailure(call: Call<ResponseBengkelDetail>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }

}