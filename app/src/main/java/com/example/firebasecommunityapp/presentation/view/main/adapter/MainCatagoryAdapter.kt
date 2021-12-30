package com.example.firebasecommunityapp.presentation.view.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasecommunityapp.R
import com.example.firebasecommunityapp.databinding.ActivityMainBinding
import com.example.firebasecommunityapp.databinding.MainCatagoryItemBinding
import kotlinx.coroutines.NonDisposableHandle.parent


class MainCatagoryAdapter(category: ArrayList<String>) : RecyclerView.Adapter<MainCatagoryAdapter.PagerViewHolder>() {

    var category = category

    inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.main_catagory_item, parent, false)) {

        val itemCatagoryText = itemView.findViewById<TextView>(R.id.catagory_text)

            fun onClick(){
                //
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        return PagerViewHolder(parent)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.onClick()
        Log.d("dsaffds", category.toString())
        holder.itemCatagoryText.setText(category[position])
    }

    override fun getItemCount(): Int {
        return category.size
    }


}