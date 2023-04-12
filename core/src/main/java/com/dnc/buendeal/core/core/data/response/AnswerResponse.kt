package com.dnc.buendeal.core.core.data.response

import com.google.gson.annotations.SerializedName

data class AnswerResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("userName") val userName: String,
    @SerializedName("answer") val answer: String,
    @SerializedName("positive") val positive: Int,
    @SerializedName("negative") val negative: Int,
    @SerializedName("date") val date: String
)
