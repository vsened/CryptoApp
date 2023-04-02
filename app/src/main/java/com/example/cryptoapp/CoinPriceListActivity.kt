package com.example.cryptoapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)
        viewModel = ViewModelProviders.of(this)[CoinViewModel::class.java]
        viewModel.priceList.observe(this) {
            Log.d("CoinViewModel", "LiveData = ${it.toString()}")
        }
        viewModel.getDetailsFor("BTC").observe(this){
            Log.d("CoinViewModel", "Success in activity = ${it.toString()}")
        }
    }
}