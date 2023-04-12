package com.dnc.buendeal.core.core.data.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("firstName") val firstName: String?,
    @SerializedName("lastName") val lastName: String?,
    @SerializedName("displayName") val displayName: String?,
    @SerializedName("gender") val gender: Gender?,
    @SerializedName("birthDate") val birthDate: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("phone") val phone: String?
)
