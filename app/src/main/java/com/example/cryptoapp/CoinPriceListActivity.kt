package com.example.cryptoapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.adapters.CoinInfoAdapter
import com.example.cryptoapp.pojo.CoinPriceInfo

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    private lateinit var rvCoinPriceInfo: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)
        rvCoinPriceInfo = findViewById(R.id.rvCoinPriceList)
        val adapter = CoinInfoAdapter()
        rvCoinPriceInfo.adapter = adapter
        adapter.onCoinItemClickListener = object: CoinInfoAdapter.OnCoinItemClickListener {
            override fun onCoinItemClick(coinPriceInfo: CoinPriceInfo) {
                Log.d("CoinPriceListActivity", "Click on $coinPriceInfo")
            }
        }
        viewModel = ViewModelProviders.of(this)[CoinViewModel::class.java]
        viewModel.priceList.observe(this) {
            adapter.items = it
        }
    }
}