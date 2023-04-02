package com.example.cryptoapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.R
import com.example.cryptoapp.presentation.adapters.CoinInfoAdapter
import com.example.cryptoapp.data.network.model.CoinInfoDto
import com.example.cryptoapp.domain.CoinInfo

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
            override fun onCoinItemClick(coinInfo: CoinInfo) {
                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinInfo.fromSymbol
                )
                startActivity(intent)
            }
        }
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.coinInfoList.observe(this) {
            adapter.items = it
        }
    }
}