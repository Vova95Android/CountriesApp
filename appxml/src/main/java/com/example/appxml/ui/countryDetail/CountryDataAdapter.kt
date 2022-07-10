package com.example.appxml.ui.countryDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.appxml.R
import com.example.appxml.databinding.ItemCountryDataBinding
import com.example.domain.models.ContentData

class CountryDataAdapter :
    ListAdapter<Pair<ContentData, String>, CountryDataAdapter.CountryDataItemItemViewHolder>(
        CountryItemDiffCallback()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryDataItemItemViewHolder {
        return CountryDataItemItemViewHolder(
            ItemCountryDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CountryDataItemItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)
    }

    class CountryDataItemItemViewHolder(
        private val binding: ItemCountryDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: Pair<ContentData, String>) {
            with(binding) {
                title.text = root.context.getString(
                    when (item.first) {
                        ContentData.Continent -> R.string.text_continent
                        ContentData.Capital -> R.string.text_capital
                        ContentData.Code -> R.string.text_code
                        ContentData.Phone -> R.string.text_phone
                        ContentData.Currency -> R.string.text_currency
                        ContentData.Language -> R.string.text_language
                    }
                )
                body.text = item.second
            }
        }
    }
}

class CountryItemDiffCallback : DiffUtil.ItemCallback<Pair<ContentData, String>>() {

    override fun areItemsTheSame(
        oldItem: Pair<ContentData, String>,
        newItem: Pair<ContentData, String>
    ): Boolean {
        return oldItem.first == newItem.first
    }

    override fun areContentsTheSame(
        oldItem: Pair<ContentData, String>,
        newItem: Pair<ContentData, String>
    ): Boolean {
        return oldItem == newItem
    }
}