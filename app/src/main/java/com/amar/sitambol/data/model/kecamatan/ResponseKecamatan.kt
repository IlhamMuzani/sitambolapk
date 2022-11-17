package com.amar.sitambol.data.model.kecamatan

import com.google.gson.annotations.SerializedName

data class ResponseKecamatan (
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String?,
    @SerializedName("kecamatan") val kecamatan: List<DataKecamatan>?
)