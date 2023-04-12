package com.dnc.buendeal.core.core.domain.model

import com.dnc.buendeal.core.core.data.response.AnswerResponse

data class Answer(
    val id: Long,
    val userName: String,
    val answer: String,
    val positive: Int,
    val negative: Int,
    val date: String
)

fun AnswerResponse.domain() = Answer(
    id = id,
    userName = userName,
    answer = answer,
    positive = positive,
    negative = negative,
    date = date
)
