package com.example.mvpdemo.base.network

import com.example.mvpdemo.base.config.Constant
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class CustomApiClient {
    companion object {
        private var retrofit: Retrofit? = null

        fun getClient(): Retrofit? {
            val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
            httpClient.addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request: Request = chain.request().newBuilder()
                        .addHeader("x-rapidapi-host", "covid-193.p.rapidapi.com")
                        .addHeader(
                            "x-rapidapi-key",
                            "40e23c48e2mshd3b72f46c4a90dcp19c615jsn67ca6698d173"
                        )
                        .build()
                    return chain.proceed(request)
                }
            })
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            }
            return retrofit
        }
    }
}