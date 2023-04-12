package com.dnc.buendeal.core.core.domain.model

import com.dnc.buendeal.core.core.data.response.QuestionResponse

data class Question(
    val id: Long,
    val question: String,
    val answers: List<Answer>? = null,
    val quantityAnswers: Int?,
    val date: String,
    val userName: String
)

fun QuestionResponse.domain() = Question(
    id = id,
    question = question,
    answers = answers?.map { it.domain() },
    quantityAnswers = quantityAnswers,
    date = date,
    userName = userName,
)
