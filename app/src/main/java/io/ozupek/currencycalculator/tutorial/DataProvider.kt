package io.ozupek.currencycalculator.tutorial

import io.ozupek.currencycalculator.network.responsemodel.RateResponse

/**
 * Created by VNGRS on 1.01.2019.
 */
interface DataProvider {

    fun getCurrency()
    fun getRatesFromInterfaceSynchronous(currency: String): RateResponse
    fun getRatesFromInterfaceAsynchronous(currency: String,callback : RatesCallback)
}
