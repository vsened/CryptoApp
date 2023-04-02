package com.example.cryptoapp.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.ActivityCoinDetailBinding
import com.example.cryptoapp.domain.CoinInfo
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    private val binding by lazy {
        ActivityCoinDetailBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL) ?: EMPTY_SYMBOL
        viewModel.getDetailInfo(fromSymbol).observe(this) {
            setValues(it)
        }
    }

    private fun setValues(coinInfo: CoinInfo) {
        with(binding) {
            tvCoinFromSymToSym.text = String.format(
                getString(R.string.card_label),
                coinInfo.fromSymbol,
                coinInfo.toSymbol
            )
            tvPriceValue.text = coinInfo.price.toString()
            tvMinByDayValue.text = coinInfo.lowDay.toString()
            tvMaxByDayValue.text = coinInfo.highDay.toString()
            tvLastDealSource.text = coinInfo.lastMarket
            tvTimeOfUpdate.text = coinInfo.lastUpdate.toString()
            Picasso.get().load(coinInfo.imageUrl).into(ivLogoCoinDetail)
        }

    }

    companion object {

        private const val EXTRA_FROM_SYMBOL = "from_symbol"
        private const val EMPTY_SYMBOL = ""
        const val BASE_iMAGE_URL = "https:/cryptocompare.com"

        fun newIntent(context: Context, fSym: String): Intent {
            return with(Intent(context, CoinDetailActivity::class.java)) {
                putExtra(EXTRA_FROM_SYMBOL, fSym)
            }
        }
    }
}