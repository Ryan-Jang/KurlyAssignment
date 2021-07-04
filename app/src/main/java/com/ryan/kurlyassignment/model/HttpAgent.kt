package com.ryan.kurlyassignment.model

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.lang.reflect.Proxy

open class HttpAgent {
    companion object {
        lateinit var baseUrl : String
        private lateinit var httpClient : OkHttpClient.Builder
        private lateinit var retrofitClient : Retrofit
        private lateinit var requestService : Proxy

        private fun getOkHttpClient(): OkHttpClient {
            httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    val requestBuilder : Request.Builder = chain.request().newBuilder()
                        .header("Content-Type", "application/json")
                    val request: Request = requestBuilder.build()
                    return chain.proceed(request)
                }
            })

            httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

            return httpClient.build()
        }

        private fun getRetrofitInstance() : Retrofit {
            retrofitClient = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build()
            return retrofitClient
        }

        fun <T> getRequestServiceInstance(t : Class<T>) : T {
            requestService = getRetrofitInstance().create(t::class.java) as Proxy
            return requestService as T
        }
    }
}