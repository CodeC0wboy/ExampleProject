package com.dnc.buendeal.core.network

import com.dnc.buendeal.core.core.data.response.LogoutResponse
import com.dnc.buendeal.core.core.data.response.SignInResponse
import com.dnc.buendeal.core.core.data.response.SignUpResponse
import com.dnc.buendeal.core.core.data.response.auth.*
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiAuth {
    @POST("NDA")
    suspend fun performRegister(
        @Query("name") name: String?,
        @Query("email") email: String?,
        @Query("password") password: String?
    ): SignUpResponse

    @POST("NDA")
    suspend fun performLogin(
        @Query("email") email: String?,
        @Query("password") password: String?
    ): SignInResponse

    @POST("NDA")
    suspend fun forgotPassword(
        @Body request: ForgotPasswordRequest,
    ): ForgotPasswordResponse

    @POST("NDA")
    suspend fun resetPassword(
        @Body request: ResetPasswordRequest,
    ): ResetPasswordResponse

    @POST("NDA")
    suspend fun performLogout(): LogoutResponse

    @POST("NDA")
    suspend fun performSocialSignIn(
        @Path("provider") provider: String,
        @Body body: MultipartBody
    ): SocialsSignInResponse
}
