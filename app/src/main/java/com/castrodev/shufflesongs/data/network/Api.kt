package com.castrodev.shufflesongs.data.network

import com.castrodev.shufflesongs.data.network.response.Result
import com.castrodev.shufflesongs.data.network.response.Song
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

fun fetchMusics(
    api: MusicsApi,
    onSuccess: (songs: List<Song>) -> Unit,
    onError: (error: String) -> Unit
) {

    GlobalScope.launch(Dispatchers.Default) {
        listOf("909253", "1171421960", "358714030", "1419227", "264111789").forEach { authorId ->
            val request = api.fetchMusics(authorId)
            val response = request.await()
            if (response.isSuccessful) {
                onSuccess(response.body()?.results ?: emptyList())
            } else {
                onError(response.errorBody()?.string() ?: "Unknown error")
            }
        }
    }
}

interface MusicsApi {

    @GET("lookup")
    fun fetchMusics(@Query("id") id: String): Deferred<Response<Result>>

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
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(MusicsApi::class.java)
        }
    }
}