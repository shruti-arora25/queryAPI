package com.example.jsonplaceholderapi

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonplaceholderapi.databinding.ListItemBinding
import posts

class RecyclerAdapter(private val list: ArrayList<posts>, context: Context) :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    private lateinit var bind: ListItemBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        bind = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(bind)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

                val currentItem=list[position]
                holder.apply {
                    userId.text= currentItem.userId.toString()
                    it.text=currentItem.id.toString()
                    title.text=currentItem.title
                    body.text=currentItem.body

                }





    }


    class MyViewHolder(binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        var userId = binding.userId
        var it = binding.it
        var title = binding.title
        var body = binding.body

    }


}
