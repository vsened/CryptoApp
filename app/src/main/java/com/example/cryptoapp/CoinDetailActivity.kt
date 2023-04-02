package com.example.cryptoapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.example.cryptoapp.pojo.CoinPriceInfo
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    private lateinit var ivLogoCoinDetail: ImageView
    private lateinit var tvCoinFromSymToSym: TextView
    private lateinit var tvPriceValue: TextView
    private lateinit var tvMinByDayValue: TextView
    private lateinit var tvMaxByDayValue: TextView
    private lateinit var tvLastDealSource: TextView
    private lateinit var tvTimeOfUpdate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        viewModel = ViewModelProviders.of(this)[CoinViewModel::class.java]
        initViews()
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL) ?: ""
        viewModel.getDetailsFor(fromSymbol).observe(this) {
            setValues(it)
        }
    }

    private fun initViews() {
        ivLogoCoinDetail = findViewById(R.id.ivLogoCoinDetail)
        tvCoinFromSymToSym = findViewById(R.id.tvCoinFromSymToSym)
        tvPriceValue = findViewById(R.id.tvPriceValue)
        tvMinByDayValue = findViewById(R.id.tvMinByDayValue)
        tvMaxByDayValue = findViewById(R.id.tvMaxByDayValue)
        tvLastDealSource = findViewById(R.id.tvLastDealSource)
        tvTimeOfUpdate = findViewById(R.id.tvTimeOfUpdate)
    }

    private fun setValues(coinPriceInfo: CoinPriceInfo) {
        tvCoinFromSymToSym.text = String.format(
            getString(R.string.card_label),
            coinPriceInfo.fromSymbol,
            coinPriceInfo.toSymbol
        )
        tvPriceValue.text = coinPriceInfo.price.toString()
        tvMinByDayValue.text = coinPriceInfo.lowday.toString()
        tvMaxByDayValue.text = coinPriceInfo.highDay.toString()
        tvLastDealSource.text = coinPriceInfo.lastMarket
        tvTimeOfUpdate.text = coinPriceInfo.getFormattedTime()
        Picasso.get().load(coinPriceInfo.getFullImageUrl()).into(ivLogoCoinDetail)

    }

    companion object {

        private const val EXTRA_FROM_SYMBOL = "from_symbol"

        fun newIntent(context: Context, fSym: String): Intent {
            return with(Intent(context, CoinDetailActivity::class.java)) {
                putExtra(EXTRA_FROM_SYMBOL, fSym)
            }
        }
    }
}