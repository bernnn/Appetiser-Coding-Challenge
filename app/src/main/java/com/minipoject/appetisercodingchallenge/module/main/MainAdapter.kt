package com.minipoject.appetisercodingchallenge.module.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.minipoject.appetisercodingchallenge.R
import com.minipoject.appetisercodingchallenge.data.response.ResultData
import com.minipoject.appetisercodingchallenge.databinding.ItemListSearchBinding
import com.minipoject.appetisercodingchallenge.module.detail.DetailActivity

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    var list : ArrayList<ResultData>? = null

    fun updateList(postList : ArrayList<ResultData>?){
        list = postList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemListSearchBinding : ItemListSearchBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_list_search, parent,false)

        return ViewHolder(itemListSearchBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val search = list?.get(position)
        holder.itemBinding.resultdata = search

        holder.itemBinding.llHolder.setOnClickListener {
            val intent = Intent(holder.itemView.context,DetailActivity::class.java)
            intent.putExtra("trackname", search?.trackName)
            intent.putExtra("genre", search?.primaryGenreName)
            intent.putExtra("price", "Price: "+search?.trackPrice+" "+search?.currency)
            intent.putExtra("description", search?.longDescription)
            intent.putExtra("imageUrl", search?.artworkUrl100)
            holder.itemView.context.startActivity(intent)
        }

        holder.itemBinding.ivPic.load(search?.artworkUrl100){
            crossfade(true)
            placeholder(R.mipmap.ic_launcher)
        }
    }

    override fun getItemCount(): Int = list?.size ?: 0

    inner class ViewHolder(itemListSearchBinding: ItemListSearchBinding) : RecyclerView.ViewHolder(itemListSearchBinding.root) {
        val itemBinding = itemListSearchBinding

    }
}