package com.dnc.buendeal.core.core.domain.model

import com.dnc.buendeal.core.core.data.response.SignUpResponse

data class SignUp(
    val id: Long,
    val name: String,
    val email: String,
    val emailVerifiedAt: Any? = null,
    val currentTeamID: Long,
    val createdAt: String,
    val updatedAt: String,
    val profilePhotoURL: String
)

fun SignUpResponse.domain() = SignUp(
    id = id,
    name = name,
    email = email,
    emailVerifiedAt = emailVerifiedAt,
    currentTeamID = currentTeamID,
    createdAt = createdAt,
    updatedAt = updatedAt,
    profilePhotoURL = profilePhotoURL
)
