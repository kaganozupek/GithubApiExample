package io.ozupek.currencycalculator.network;

import io.ozupek.currencycalculator.network.responsemodel.RateResponse;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by VNGRS on 31.12.2018.
 */
public interface ExchangeRatesApi {

    @GET("/latest")
    Observable<RateResponse> getExchangeRates(@Query("base") String base);

}
