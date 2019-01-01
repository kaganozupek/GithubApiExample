package io.ozupek.currencycalculator.network.responsemodel

/**
 * Created by VNGRS on 31.12.2018.
 */

data class RateResponse(

    var base : String?,
    var rates : HashMap<String,Double>?
)