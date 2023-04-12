package com.dnc.buendeal.core.core.domain.model

import com.dnc.buendeal.core.core.data.response.QuestionAnswerResponse

data class QuestionAnswer(
    val id: Long,
    val question: Question,
    val lastAnswer: String,
    val product: Product? = null
)

fun QuestionAnswerResponse.domain() = QuestionAnswer(
    id = id,
    question = question.domain(),
    lastAnswer = lastAnswer,
    product = product?.domain()
)
