package com.example.lesson_4_rudomanov

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

const val TYPE_DETAIL = 0
const val TYPE_BASE = 1

class ItemsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = mutableListOf<ListItem>()

    lateinit var onBaseClick: (BaseInfoItem) -> Unit
    lateinit var detailListener: DetailListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_DETAIL -> DetailViewHolder(parent, detailListener)
            TYPE_BASE -> BaseViewHolder(parent, onBaseClick)
            else -> error("ViewType not supported")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DetailViewHolder -> holder.bind((items[position] as ListItem.DetailInfoItemSeal).detail)
            is BaseViewHolder -> if (items[position] is ListItem.BaseInfoItemSeal) {
                holder.bind((items[position] as ListItem.BaseInfoItemSeal).base)
            } else {
                holder.bind((items[position] as ListItem.DetailInfoItemSeal).detail)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position] is ListItem.DetailInfoItemSeal && position + 1 < items.size && position % 2 == 0 && items[position + 1] is ListItem.BaseInfoItemSeal) {
            TYPE_BASE
        } else if (items[position] is ListItem.DetailInfoItemSeal) {
            TYPE_DETAIL
        } else {
            TYPE_BASE
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(items: List<ListItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}