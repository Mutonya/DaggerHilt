package com.example.daggerhilt.network

import retrofit2.http.GET

interface Retrofit {

   @GET("blogs")
   suspend fun getblogs():List<BlogEntry>
}