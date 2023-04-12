package com.dnc.buendeal.core.core.data.response

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("emailVerifiedAt")
    val emailVerifiedAt: Any? = null,
    @SerializedName("currentTeamId")
    val currentTeamID: Long,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("profilePhotoUrl")
    val profilePhotoURL: String
)
