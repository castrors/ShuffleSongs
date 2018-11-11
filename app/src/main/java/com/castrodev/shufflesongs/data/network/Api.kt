package com.castrodev.shufflesongs.data.network

import android.util.Log
import com.castrodev.shufflesongs.data.network.response.Result
import com.castrodev.shufflesongs.data.network.response.Song
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val TAG = "MusicsApi"

fun fetchMusics(
    api: MusicsApi,
    onSuccess: (songs: List<Song>) -> Unit,
    onError: (error: String) -> Unit) {

    api.fetchMusics("909253").enqueue(
        object : Callback<Result> {
            override fun onFailure(call: Call<Result>?, t: Throwable) {
                Log.d(TAG, "Fail to get data")
                onError(t.message ?: "unknown error")
            }

            override fun onResponse(call: Call<Result>?, response: Response<Result>) {
                Log.d(TAG, "Got a response $response")
                when {
                    response.isSuccessful -> onSuccess(response.body()?.results  ?: emptyList())
                    else -> onError(response.errorBody()?.string() ?: "Unknown error")
                }

            }
        }
    )
}

interface MusicsApi {

    @GET("lookup")
    fun fetchMusics(@Query("id") id: String): Call<Result>

    companion object {
        private const val BASE_URL = "https://us-central1-tw-exercicio-mobile.cloudfunctions.net"

        fun create(): MusicsApi {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MusicsApi::class.java)
        }
    }
}