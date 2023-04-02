package com.example.cryptoapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cryptoapp.data.network.ApiFactory
import com.example.cryptoapp.utils.convertTimestampToTime
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

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
    val imageUrl: String? = null
)