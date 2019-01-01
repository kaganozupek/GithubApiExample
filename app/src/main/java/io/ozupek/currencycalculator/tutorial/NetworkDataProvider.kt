package io.ozupek.currencycalculator.tutorial

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.ozupek.currencycalculator.network.responsemodel.RateResponse
import okhttp3.OkHttpClient
import okhttp3.Request


/**
 * Created by VNGRS on 1.01.2019.
 */
class NetworkDataProvider : DataProvider {


    override fun getCurrency() {

    }

    override fun getRatesFromInterfaceSynchronous(currency: String): RateResponse {
        return getRatesSync(currency)
    }


    fun getRatesSync(currency: String) : RateResponse{

        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://api.exchangeratesapi.io/latest?base=$currency")
            .get()
            .build()

        val response = client.newCall(request).execute()
        val jsonResponse = response.body()!!.string()
        val gson = GsonBuilder().create()
        val rateResponse = gson.fromJson(jsonResponse,RateResponse::class.java)
        return rateResponse

    }

    override fun getRatesFromInterfaceAsynchronous(currency: String, callback: RatesCallback) {

        val thread = Thread{
            val response = getRatesSync(currency)
            callback.onRatesReceived(response)
        }

        thread.start()
    }


}
