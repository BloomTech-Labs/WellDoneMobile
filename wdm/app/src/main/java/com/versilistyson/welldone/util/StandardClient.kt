package com.versilistyson.welldone.util

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.versilistyson.MyApplication
import com.versilistyson.welldone.data.local.SharedPreference
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class StandardClient<T>(application: MyApplication, baseUrl: String) {

    private val okHttpClient = OkHttpClient.Builder()
    private val retrofit = Retrofit.Builder()
    private var r: Retrofit
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    init {
        val client = okHttpClient.apply{
            val token = application.retrieveToken()

            token?.let {
                this.addInterceptor(object : Interceptor {
                    override fun intercept(chain: Interceptor.Chain): Response {
                        val newRequest: Request = chain.request().newBuilder()
                            .addHeader("Authorization", "$token")
                            .build()
                        return chain.proceed(newRequest)
                    }
                })
            }

            this.retryOnConnectionFailure(false)
            this.readTimeout(30, TimeUnit.SECONDS)
            this.connectTimeout(30, TimeUnit.SECONDS)
        }.build()

        r = retrofit.apply {
            this.baseUrl(baseUrl)
            this.client(client)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
        }.build()
    }

    fun create(service: Class<T>): T {
        return r.create(service)
    }
}