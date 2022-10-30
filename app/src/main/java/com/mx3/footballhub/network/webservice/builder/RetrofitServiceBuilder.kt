package com.mx3.footballhub.network.webservice.builder

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mx3.footballhub.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

object RetrofitServiceBuilder {

    private const val FOOTBALL_DATA_BASE_URL = "http://api.football-data.org/v4/"

    fun <S> buildService(serviceType: Class<S>): S {
        val loggingInterceptor = HttpLoggingInterceptor { Timber.d(it) }
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val headersInterceptor = Interceptor { chain ->
            val builder = chain.request().newBuilder()
            builder.header("X-Auth-Token", BuildConfig.FOOTBALL_DATA_API_KEY)
            return@Interceptor chain.proceed(builder.build())
        }

        val sOkHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(headersInterceptor)
            .addInterceptor(loggingInterceptor)

        return Retrofit.Builder()
            .baseUrl(FOOTBALL_DATA_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(sOkHttpClientBuilder.build())
            .build()
            .create(serviceType)
    }
}