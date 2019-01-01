package io.ozupek.currencycalculator.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * Created by VNGRS on 31.12.2018.
 */
class NetworkManager
{
    val BASE_URL = "https://api.exchangeratesapi.io"
    val GITHUB_BASE_URL = "https://api.github.com"
    lateinit var api : ExchangeRatesApi
    lateinit var githubAPI: GithubAPI

    companion object {
        var instance = NetworkManager()
    }

    fun start() {
        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        var githubRetrofit = Retrofit.Builder()
            .baseUrl(GITHUB_BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        api = retrofit.create(ExchangeRatesApi::class.java)
        githubAPI = githubRetrofit.create(GithubAPI::class.java)


    }
}
