package io.ozupek.currencycalculator.tutorial

import io.ozupek.currencycalculator.network.responsemodel.RateResponse

/**
 * Created by VNGRS on 1.01.2019.
 */
class RetrofitDatProvider : DataProvider{
    override fun getCurrency() {
    }

    override fun getRatesFromInterfaceSynchronous(currency: String): RateResponse {
        return RateResponse(null,null)
    }

    override fun getRatesFromInterfaceAsynchronous(currency: String, callback: RatesCallback) {

    }

}
