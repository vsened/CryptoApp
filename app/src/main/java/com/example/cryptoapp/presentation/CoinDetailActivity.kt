package com.example.cryptoapp.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.cryptoapp.R
import com.example.cryptoapp.data.network.model.CoinInfoDto
import com.example.cryptoapp.domain.CoinInfo
import com.example.cryptoapp.utils.convertTimestampToTime
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
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        initViews()
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL) ?: EMPTY_SYMBOL
        viewModel.getDetailInfo(fromSymbol).observe(this) {
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

    private fun setValues(coinInfo: CoinInfo) {
        tvCoinFromSymToSym.text = String.format(
            getString(R.string.card_label),
            coinInfo.fromSymbol,
            coinInfo.toSymbol
        )
        tvPriceValue.text = coinInfo.price.toString()
        tvMinByDayValue.text = coinInfo.lowDay.toString()
        tvMaxByDayValue.text = coinInfo.highDay.toString()
        tvLastDealSource.text = coinInfo.lastMarket
        tvTimeOfUpdate.text = convertTimestampToTime(coinInfo.lastUpdate?.toLong())
        Picasso.get().load(BASE_iMAGE_URL + coinInfo.imageUrl).into(ivLogoCoinDetail)

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