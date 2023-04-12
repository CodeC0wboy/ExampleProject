package com.dnc.buendeal.core.core.ui.model

import com.dnc.buendeal.core.core.data.response.Gender
import com.dnc.buendeal.core.core.domain.model.User

data class UserItemModel(
    val firstName: String,
    val lastName: String,
    val displayName: String,
    val gender: Gender?,
    val birthDate: String,
    val email: String,
    val phone: String
)

fun User.itemModel(): UserItemModel = UserItemModel(
    firstName = firstName,
    lastName = lastName,
    displayName = displayName,
    gender = gender,
    birthDate = birthDate,
    email = email,
    phone = phone
)
