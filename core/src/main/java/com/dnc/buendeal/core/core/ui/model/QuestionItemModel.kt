package com.dnc.buendeal.core.core.ui.model

import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.domain.model.Question

data class QuestionItemModel(
    val id: Long,
    val question: String,
    val answers: List<AnswerItemModel>? = null,
    val quantityAnswers: Int?,
    val date: String,
    val userName: String
) : ItemModel

fun Question.itemModel() = QuestionItemModel(
    id = id,
    question = question,
    answers = answers?.map { it.itemModel() },
    quantityAnswers = quantityAnswers,
    date = date,
    userName = userName,
)
