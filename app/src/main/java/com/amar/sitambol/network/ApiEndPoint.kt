package com.amar.sitambol.network

import com.amar.sitambol.data.model.bengkel.ResponseBengkelDetail
import com.amar.sitambol.data.model.bengkel.ResponseBengkelList
import com.amar.sitambol.data.model.kecamatan.ResponseKecamatan
import com.amar.sitambol.data.model.kelurahan.ResponseKelurahan
import com.amar.sitambol.data.model.user.ResponseUser
import retrofit2.Call
import retrofit2.http.*

interface ApiEndPoint {

    @FormUrlEncoded
    @POST("userLogin")
    fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Call<ResponseUser>

    @FormUrlEncoded
    @POST("userRegister")
    fun userRegister(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("password_confirmation") password_confirmation: String,
    ): Call<ResponseUser>

    @GET("kecamatanList")
    fun kecamatanList(): Call<ResponseKecamatan>

    @POST("kecamatanSearch")
    fun kecamatanSearch(
        @Query("keyword") keyword: String
    ): Call<ResponseKecamatan>

    @GET("kelurahanList/{kecamatan_id}")
    fun kelurahanList(
        @Path("kecamatan_id") kecamatan_id: Long
    ): Call<ResponseKelurahan>

    @GET("kelurahanAll")
    fun kelurahanAll(): Call<ResponseKelurahan>

    @POST("kelurahanSearch")
    fun kelurahanSearch(
        @Query("keyword") keyword: String
    ): Call<ResponseKelurahan>

    @GET("bengkelList/{kelurahan_id}")
    fun bengkelList(
        @Path("kelurahan_id") kelurahan_id: Long
    ): Call<ResponseBengkelList>

    @GET("bengkelAll")
    fun bengkelAll(): Call<ResponseBengkelList>

    @POST("bengkelSearch")
    fun bengkelSearch(
        @Query("keyword") keyword: String
    ): Call<ResponseBengkelList>

    @GET("bengkelDetail/{id}")
    fun bengkelDetail(
        @Path("id") id: Long
    ): Call<ResponseBengkelDetail>
}