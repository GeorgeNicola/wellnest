package com.cst.wellnest.networking.client

import com.cst.wellnest.managers.SharedPrefsManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthTokenInterceptor : Interceptor {

    companion object {
        const val ARG_AUTHORIZATION = "Authorization"
        const val ARG_BEARER = "Bearer"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = SharedPrefsManager.getAuthToken()

        val request = chain.request().newBuilder()

        request.addHeader(ARG_AUTHORIZATION, "${ARG_BEARER} $token")

        return chain.proceed(request.build())
    }

}

object RetrofitClient {
    private val authInterceptor by lazy {
        AuthTokenInterceptor()
    }

    private val logging = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    private val authClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(logging)
        .build()

    private val nonAuthClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val authInstance = Retrofit.Builder()
        .baseUrl("https://reqres.in/")
        .client(authClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val nonAuthInstance = Retrofit.Builder()
        .baseUrl("https://reqres.in/")
        .client(nonAuthClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}