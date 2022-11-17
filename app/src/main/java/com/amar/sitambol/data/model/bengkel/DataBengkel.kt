package com.amar.sitambol.data.model.bengkel

import com.amar.sitambol.data.model.gambar.DataGambar
import com.google.gson.annotations.SerializedName

data class DataBengkel (
    @SerializedName("id") val id: Long?,
    @SerializedName("kelurahan_id") val kelurahan_id: Long?,
    @SerializedName("nama") val nama: String?,
    @SerializedName("keterangan") val keterangan: String?,
    @SerializedName("fasilitas") val fasilitas: String?,
    @SerializedName("latitude") val latitude: String?,
    @SerializedName("longitude") val longitude: String?,
    @SerializedName("gambar") val gambar: List<DataGambar>?
)