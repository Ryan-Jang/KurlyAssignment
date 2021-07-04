package com.ryan.kurlyassignment.model

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.IOException

open class HttpAgent {
    companion object {
        var baseUrl : String = "https://api.github.com/search/"
        private var httpClient : OkHttpClient.Builder? = null
        private var retrofitClient : Retrofit? = null
        private var requestService : SearchService? = null
    }

    private fun getOkHttpClient(): OkHttpClient {
        if (httpClient == null) {
            httpClient = OkHttpClient.Builder()
            httpClient?.addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    val requestBuilder : Request.Builder = chain.request().newBuilder()
                        .header("Content-Type", "application/json")
                    val request: Request = requestBuilder.build()
                    return chain.proceed(request)
                }
            })

            httpClient?.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }

        return httpClient!!.build()
    }

    private fun getRetrofitInstance() : Retrofit {
        if (retrofitClient == null)
            retrofitClient = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build()
        return retrofitClient!!
    }

    fun getRequestServiceInstance() : SearchService {
        if (requestService == null)
            requestService = getRetrofitInstance().create(SearchService::class.java)
        return requestService!!
    }

    interface SearchService {
        @GET("repositories")
        fun searchRepo(@Query("q") query: String, @Query("page") page: Int = 1, @Query("per_page") perPage : Int = 50) : Call<SearchResponse>
    }
}