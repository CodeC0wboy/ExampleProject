package com.dnc.buendeal.core.core.data.response

data class QuestionAnswerResponse(
    val id: Long,
    val question: QuestionResponse,
    val lastAnswer: String,
    val product: ProductMockResponse? = null
)
