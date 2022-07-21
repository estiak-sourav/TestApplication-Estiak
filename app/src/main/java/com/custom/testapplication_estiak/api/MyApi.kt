package com.custom.testapplication_estiak.api

import retrofit2.http.GET
import com.custom.testapplication_estiak.models.Repositorie
import io.reactivex.Observable
import retrofit2.http.Query

interface MyApi {

    /*get App Repositories*/
    @GET("search/repositories?q=stars:%3E1&order=desc")
    fun getRepositories(@Query("sort") sort: String?): Observable<Repositorie?>
}