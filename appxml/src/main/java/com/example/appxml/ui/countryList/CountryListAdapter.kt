package com.example.appxml.ui.countryList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.appxml.R
import com.example.appxml.databinding.ItemCountryBinding
import com.example.domain.models.CountriesItem

class CountryListAdapter(
    private val onItemClicked: (CountriesItem) -> Unit
) : ListAdapter<CountriesItem, CountryListAdapter.CountryItemItemViewHolder>(CountryItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryItemItemViewHolder {
        return CountryItemItemViewHolder(
            ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CountryItemItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item, onItemClicked)
    }

    class CountryItemItemViewHolder(
        private val binding: ItemCountryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: CountriesItem, onItemClicked: (CountriesItem) -> Unit) {
            with(binding) {
                image.text = item.emoji
                name.text = item.name
                root.background = AppCompatResources.getDrawable(
                    root.context,
                    if (item.isSelected) R.drawable.background_outline_10_active
                    else R.drawable.background_outline_10
                )
                root.setOnClickListener { onItemClicked.invoke(item) }
            }
        }
    }
}

class CountryItemDiffCallback : DiffUtil.ItemCallback<CountriesItem>() {

    override fun areItemsTheSame(oldItem: CountriesItem, newItem: CountriesItem): Boolean {
        return oldItem.code == newItem.code
    }

    override fun areContentsTheSame(oldItem: CountriesItem, newItem: CountriesItem): Boolean {
        return oldItem == newItem
    }
}