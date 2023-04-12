package com.dnc.buendeal.core.core.ui.model

import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.domain.model.Answer

data class AnswerItemModel(
    val id: Long,
    val userName: String,
    val answer: String,
    val positive: Int,
    val negative: Int,
    val date: String
) : ItemModel

fun Answer.itemModel() = AnswerItemModel(
    id = id,
    userName = userName,
    answer = answer,
    positive = positive,
    negative = negative,
    date = date
)
