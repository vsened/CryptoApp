package com.example.cryptoapp.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.ActivityCoinDetailBinding
import com.example.cryptoapp.databinding.FragmentCoinDetailBinding
import com.example.cryptoapp.domain.CoinInfo
import com.squareup.picasso.Picasso

class CoinDetailFragment : Fragment() {

    private lateinit var viewModel: CoinViewModel
    private var _binding: FragmentCoinDetailBinding? = null
    private val binding: FragmentCoinDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentCoinDetailBinding is null")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        val fromSymbol = getSymbol()
        viewModel.getDetailInfo(fromSymbol).observe(viewLifecycleOwner) {
            setValues(it)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCoinDetailBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
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

    private fun getSymbol(): String {
        return requireArguments().getString(EXTRA_FROM_SYMBOL, "")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        private const val EXTRA_FROM_SYMBOL = "from_symbol"
        private const val EMPTY_SYMBOL = ""
        const val BASE_iMAGE_URL = "https:/cryptocompare.com"

        fun newInstance(fSym: String): Fragment {
            return CoinDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_FROM_SYMBOL, fSym)
                }
            }
        }
    }
}