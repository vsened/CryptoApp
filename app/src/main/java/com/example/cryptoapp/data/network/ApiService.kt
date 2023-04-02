package com.example.cryptoapp.data.network

import com.example.cryptoapp.data.network.model.CoinInfoJsonContainerDto
import com.example.cryptoapp.data.network.model.CoinNamesListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top/totalvolfull")
    suspend fun getTopCoinsInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "892eaa93d6f25f4bb7eeac53e97e0f55f0e31b1dfb9a4b1c53de9f974f9d9b2b",
        @Query(QUERY_PARAM_LIMIT) limit: Int = 10,
        @Query(QUERY_PARAM_TO_SYMBOL) tsym: String = CURRENCY
    ): CoinNamesListDto

    @GET("pricemultifull")
    suspend fun getFullPriceList(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "892eaa93d6f25f4bb7eeac53e97e0f55f0e31b1dfb9a4b1c53de9f974f9d9b2b",
        @Query(QUERY_PARAM_FROM_SYMBOLS) fsyms: String,
        @Query(QUERY_PARAM_TO_SYMBOLS) tsyms: String = CURRENCY
    ): CoinInfoJsonContainerDto


    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"

        private const val CURRENCY = "USD"
    }
}