package com.dnc.buendeal.core.core.domain.model

data class UserSignIn(
    val createdAt: String,
    val currentTeamId: Int,
    val email: String,
    val emailVerifiedAt: String,
    val id: Int,
    val name: String,
    val profilePhotoUrl: String,
    val updatedAt: String
)
