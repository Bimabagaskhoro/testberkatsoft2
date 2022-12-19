package com.bimabagaskhoro.taskapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bimabagaskhoro.taskapp.R
import com.bimabagaskhoro.taskapp.databinding.ItemPesertaBinding
import com.bimabagaskhoro.taskapp.domain.model.ParticipantModel
import com.bumptech.glide.Glide

@SuppressLint("NotifyDataSetChanged")
class ParticipantAdapter: RecyclerView.Adapter<ParticipantAdapter.ViewHolder>()  {
    private var listData = ArrayList<ParticipantModel>()
    var onItemClicked: ((ParticipantModel) -> Unit)? = null

    fun setData(newListData: List<ParticipantModel>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder (LayoutInflater.from(parent.context).inflate(R.layout.item_peserta, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount() = listData.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemPesertaBinding.bind(itemView)
        fun bind(data: ParticipantModel) {
            binding.apply {
                tvTittleUser.text = data.name
                itemView.setOnClickListener { onItemClicked?.invoke(data) }
            }

        }
    }

}