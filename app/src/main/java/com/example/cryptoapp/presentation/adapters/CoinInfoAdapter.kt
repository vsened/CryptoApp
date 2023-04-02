package com.example.cryptoapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.ItemCoinInfoBinding
import com.example.cryptoapp.domain.CoinInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter(
    private val context: Context
) : ListAdapter<CoinInfo, CoinInfoViewHolder>(CoinInfoDiffCallback) {

    var onCoinItemClickListener: OnCoinItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val binding = ItemCoinInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CoinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            with(item) {
                tvSymbols.text = String.format(
                    root.context.getString(R.string.card_label),
                    fromSymbol,
                    toSymbol
                )
                tvPrice.text = price.toString()
                tvTimeOfLastUpdate.text = String.format(
                    root.context.getString(R.string.last_update_label),
                    lastUpdate.toString()
                )
                Picasso.get().load(imageUrl).into(ivLogoCoin)
                root.setOnClickListener {
                    onCoinItemClickListener?.onCoinItemClick(this)
                }
            }
        }
    }

    interface OnCoinItemClickListener {
        fun onCoinItemClick(coinInfo: CoinInfo)
    }
}