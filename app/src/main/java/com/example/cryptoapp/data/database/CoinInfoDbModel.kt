package com.example.cryptoapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("full_price_list")
data class CoinInfoDbModel(
    @PrimaryKey
    val fromSymbol: String,
    val toSymbol: String? = null,
    val price: Double? = null,
    val lastUpdate: Int? = null,
    val highDay: Double? = null,
    val lowDay: Double? = null,
    val lastMarket: String? = null,
    val imageUrl: String
)