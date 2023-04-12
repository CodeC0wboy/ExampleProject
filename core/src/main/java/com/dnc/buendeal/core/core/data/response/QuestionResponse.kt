package com.dnc.buendeal.core.core.data.response

import com.google.gson.annotations.SerializedName

data class QuestionResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("question") val question: String,
    @SerializedName("answers") val answers: List<AnswerResponse>? = null,
    @SerializedName("quantityAnswers") val quantityAnswers: Int?,
    @SerializedName("date") val date: String,
    @SerializedName("userName") val userName: String
)
