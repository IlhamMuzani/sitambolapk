package com.amar.sitambol.ui.all

import com.amar.sitambol.data.model.bengkel.ResponseBengkelDetail
import com.amar.sitambol.data.model.bengkel.ResponseBengkelList
import com.amar.sitambol.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BengkelMapPresenter(val view: BengkelMapContract.View) :
    BengkelMapContract.Presenter {

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
                    view.onResult(responseBengkelList!!)
                }
            }

            override fun onFailure(call: Call<ResponseBengkelList>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }
}