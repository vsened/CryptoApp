package com.example.cryptoapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.R
import com.example.cryptoapp.data.network.ApiFactory
import com.example.cryptoapp.data.network.model.CoinInfoDto
import com.example.cryptoapp.domain.CoinInfo
import com.example.cryptoapp.utils.convertTimestampToTime
import com.squareup.picasso.Picasso

class CoinInfoAdapter : RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {
    var items: List<CoinInfo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onCoinItemClickListener: OnCoinItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_coin_info,
            parent,
            false
        )
        return CoinInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val item = items[position]
        with(holder) {
            with(item) {
                tvSymbols.text = String.format(
                    itemView.context.getString(R.string.card_label),
                    fromSymbol,
                    toSymbol
                )
                tvPrice.text = price.toString()
                tvTimeOfLastUpdate.text = String.format(
                    itemView.context.getString(R.string.last_update_label),
                    convertTimestampToTime(lastUpdate?.toLong())
                )
                Picasso.get().load(ApiFactory.BASE_iMAGE_URL + imageUrl).into(ivLogoCoin)
                itemView.setOnClickListener {
                    onCoinItemClickListener?.onCoinItemClick(this)
                }
            }
        }
    }

    override fun getItemCount() = items.size

    inner class CoinInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivLogoCoin: ImageView = itemView.findViewById(R.id.ivLogoCoin)
        val tvSymbols: TextView = itemView.findViewById(R.id.tvSymbols)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        val tvTimeOfLastUpdate: TextView = itemView.findViewById(R.id.tvTimeOfLastUpdate)
    }

    interface OnCoinItemClickListener {
        fun onCoinItemClick(coinInfo: CoinInfo)
    }
}