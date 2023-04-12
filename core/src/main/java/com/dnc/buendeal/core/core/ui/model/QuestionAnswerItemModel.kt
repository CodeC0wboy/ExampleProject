package com.dnc.buendeal.core.core.ui.model

import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.domain.model.QuestionAnswer

data class QuestionAnswerItemModel(
    val id: Long,
    val question: QuestionItemModel,
    val lastAnswer: String,
    val expanded: Boolean = false,
    val product: ProductItemModel? = null
) : ItemModel

fun QuestionAnswer.itemModel() = QuestionAnswerItemModel(
    id = id,
    question = question.itemModel(),
    lastAnswer = lastAnswer,
    product = product?.itemModel()
)
