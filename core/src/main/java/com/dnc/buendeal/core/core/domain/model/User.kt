package com.dnc.buendeal.core.core.domain.model

import com.dnc.buendeal.core.core.data.response.Gender
import com.dnc.buendeal.core.core.data.response.UserResponse

data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val displayName: String,
    val gender: Gender?,
    val birthDate: String,
    val email: String,
    val phone: String
)

fun UserResponse.domain(): User = User(
    id = id ?: 0,
    firstName = firstName ?: "",
    lastName = lastName ?: "",
    displayName = displayName ?: "",
    gender = gender,
    birthDate = birthDate ?: "",
    email = email ?: "",
    phone = phone ?: ""
)
