package com.amar.sitambol.data.model.kecamatan

import com.google.gson.annotations.SerializedName

data class DataKecamatan (
    @SerializedName("id") val id: Long?,
    @SerializedName("nama") val nama: String?,
)