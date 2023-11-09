package com.example.todoproject.presentation.view.recyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarspedia.R
import com.example.starwarspedia.data.models.PeopleModel

class GenericAdapter() : RecyclerView.Adapter<GenericViewHolder>() {


    private val peopleModel: MutableList<PeopleModel> = mutableListOf()
    private var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.for_items,
            parent,
            false
        )
        return GenericViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        val person = peopleModel[position]

        holder.setInfoNotes(person)
        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, person)
            }
        }
    }

    fun addItems(peopleToBeAdded: List<PeopleModel>){
        peopleModel.addAll(peopleToBeAdded)
        notifyItemRangeInserted(itemCount, peopleToBeAdded.size)
        Log.d("mitg", "Objects was added")
    }

    override fun getItemCount(): Int {
        return peopleModel.size
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int, model: PeopleModel)
    }
}
