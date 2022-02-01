package com.example.bluetoothcontrol.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bluetoothcontrol.R
import com.example.bluetoothcontrol.data.BTDevice
import com.example.bluetoothcontrol.databinding.ListItemBinding

class ListAdapterBT(private val listener: Listener): ListAdapter<BTDevice, ListAdapterBT.ItemHolder>(ItemComporator()) {
    class ItemHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ListItemBinding.bind(view)

        fun setData (item: BTDevice, listener: Listener) = with(binding){
            btName.text = item.name
            btMAC.text = item.mac
            itemView.setOnClickListener(){
                listener.onClick(item)
            }

        }

        companion object{
            fun create(parent: ViewGroup): ItemHolder {
                return ItemHolder(LayoutInflater.
                from(parent.context).
                inflate(R.layout.list_item,parent,false))
            }
        }
    }
    class ItemComporator: DiffUtil.ItemCallback<BTDevice>(){
        override fun areItemsTheSame(oldItem: BTDevice, newItem: BTDevice): Boolean {
            return oldItem.mac == newItem.mac
        }

        override fun areContentsTheSame(oldItem: BTDevice, newItem: BTDevice): Boolean {
            return oldItem.mac == newItem.mac && oldItem.name == newItem.name
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(getItem(position), listener)
    }

    interface Listener{
        fun onClick(item: BTDevice){
        }
    }
}