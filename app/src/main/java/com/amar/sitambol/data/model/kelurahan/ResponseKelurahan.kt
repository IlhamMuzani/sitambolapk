package com.amar.sitambol.data.model.kelurahan

import com.google.gson.annotations.SerializedName

data class ResponseKelurahan (
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String?,
    @SerializedName("kelurahan") val kelurahan: List<DataKelurahan>?
)