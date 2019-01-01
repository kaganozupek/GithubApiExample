package io.ozupek.currencycalculator.tutorial

import io.ozupek.currencycalculator.network.responsemodel.RateResponse

/**
 * Created by VNGRS on 1.01.2019.
 */

interface RatesCallback{
    fun onRatesReceived(rates : RateResponse)
}