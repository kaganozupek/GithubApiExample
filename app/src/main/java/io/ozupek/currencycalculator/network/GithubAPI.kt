package io.ozupek.currencycalculator.network

import io.ozupek.currencycalculator.network.responsemodel.RepositoryResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by VNGRS on 1.01.2019.
 */

interface GithubAPI {

    @GET("/search/repositories")
    fun searchRepositories(@Query("q") query: String, @Query("per_page") perPage: Int, @Query("page") page: Int): Observable<RepositoryResponse>
}