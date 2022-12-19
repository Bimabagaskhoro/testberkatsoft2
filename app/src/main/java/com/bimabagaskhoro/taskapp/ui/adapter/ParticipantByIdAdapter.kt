package com.bimabagaskhoro.taskapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bimabagaskhoro.taskapp.R
import com.bimabagaskhoro.taskapp.databinding.ItemKegiatanBinding
import com.bimabagaskhoro.taskapp.domain.model.ParticipantModelById

class ParticipantByIdAdapter : RecyclerView.Adapter<ParticipantByIdAdapter.ViewHolder>() {
    private var listData = ArrayList<ParticipantModelById>()
    var onItemClicked: ((ParticipantModelById) -> Unit)? = null

    fun setData(newListData: List<ParticipantModelById>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        ViewHolder (LayoutInflater.from(parent.context).inflate(R.layout.item_kegiatan, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount() = listData.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemKegiatanBinding.bind(itemView)
        fun bind(data: ParticipantModelById) {
            binding.apply {
                tvTittleUser.text = data.name
                tvTimeStart.text = data.timeStart
                tvTimeEnd.text = data.timeEnd
                itemView.setOnClickListener { onItemClicked?.invoke(data) }
            }

        }
    }

}