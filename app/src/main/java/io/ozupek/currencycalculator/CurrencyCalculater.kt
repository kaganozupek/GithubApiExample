package io.ozupek.currencycalculator

import android.app.Application
import io.ozupek.currencycalculator.network.NetworkManager

/**
 * Created by VNGRS on 31.12.2018.
 */

class CurrencyCalculater : Application() {

    override fun onCreate() {
        super.onCreate()
        NetworkManager.instance.start()
    }

}
