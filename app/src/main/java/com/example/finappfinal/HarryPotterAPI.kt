package com.example.finappfinal

import retrofit2.http.GET
import retrofit2.http.Path

interface HarryPotterAPI {
    @GET("character/{id}")
    suspend fun getSpecificChar(@Path("id") id: String): List<Character>

    @GET("characters")
    suspend fun getAllChar(): List<Character>

    @GET("characters/staff")
    suspend fun getAllStaff(): List<Character>

    @GET("characters/house/{house}")
    suspend fun getStudentsByHouse(@Path("house") house: String): List<Character>
}