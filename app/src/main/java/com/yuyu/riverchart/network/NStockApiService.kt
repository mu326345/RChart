package com.yuyu.riverchart.network


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.yuyu.riverchart.data.NStockData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.*


private const val BASE_URL = "https://api.nstock.tw"

private val client = OkHttpClient.Builder()
    .addInterceptor(
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
       )
    .build()

private val moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(client)
    .build()

interface NStockApiService {
    @GET("/v2/per-river/interview?stock_id=2330")
    fun getNStock(): Call<NStockData>
}

object NStockApi {
    val service: NStockApiService by lazy { retrofit.create(NStockApiService::class.java) }
}