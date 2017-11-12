package com.example.shido.architecturepaging

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Shido on 12/11/2017.
 */
object KitsuRestApi {
    private val kitsuApi: KitsuSpecApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://kitsu.io/api/edge/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        kitsuApi = retrofit.create(KitsuSpecApi::class.java)
    }

    fun getKitsu(filter: String, offset: Int, limit: Int): Call<KitsuResponse> {
        return kitsuApi.filterKitsu(filter, limit, offset)
    }
}

interface KitsuSpecApi {
    @GET("anime")
    fun filterKitsu(
            @Query("filter[text]") filter: String,
            @Query("page[limit]") limit: Int,
            @Query("page[offset]") offset: Int): Call<KitsuResponse>
}