package com.dnc.buendeal.core.core.data.response.auth

import com.dnc.buendeal.core.core.domain.model.UserSignIn
import com.google.gson.annotations.SerializedName

data class UserSignInDto(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("currentTeamId")
    val currentTeamId: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("emailVerifiedAt")
    val emailVerifiedAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("profilePhotoUrl")
    val profilePhotoUrl: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)

fun UserSignInDto.domain(): UserSignIn = UserSignIn(
    createdAt = createdAt,
    currentTeamId = currentTeamId, email = email,
    emailVerifiedAt = emailVerifiedAt,
    id = id,
    name = name,
    profilePhotoUrl = profilePhotoUrl,
    updatedAt = updatedAt
)
