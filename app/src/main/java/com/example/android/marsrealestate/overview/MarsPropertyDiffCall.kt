package com.example.android.marsrealestate.overview

import androidx.recyclerview.widget.DiffUtil
import com.example.android.marsrealestate.network.MarsProperty

class MarsPropertyDiffCall: DiffUtil.ItemCallback<MarsProperty>(){

    override fun areItemsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
        return oldItem.id == newItem.id
    }
}