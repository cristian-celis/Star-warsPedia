package com.example.starwarspedia.presentation.adapterRV

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class GenericAdapter<T : Any>(
    private val layoutId: Int,
    private val onBind: (View, T) -> Unit
) : RecyclerView.Adapter<GenericAdapter<T>.GenericViewHolder>() {

    private val data: MutableList<T> = mutableListOf()
    private var onClickListener: OnClickListener<T>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return GenericViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        val item = data[position]
        onBind(holder.itemView, item)
        holder.itemView.setOnClickListener {
            onClickListener?.onClick(position, item)
        }
    }

    fun addItems(items: List<T>) {
        data.addAll(items)
        notifyItemRangeInserted(itemCount - 1, itemCount + 9)
    }

    fun removeAllItems(){
        data.clear()
        notifyItemRangeRemoved(0, itemCount)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setOnClickListener(onClickListener: OnClickListener<T>) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener<T> {
        fun onClick(position: Int, item: T)
    }

    inner class GenericViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}