package com.amar.sitambol.data.model.bengkel

import com.google.gson.annotations.SerializedName

data class ResponseBengkelList (
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String?,
    @SerializedName("bengkel") val bengkel: List<DataBengkel>?
)