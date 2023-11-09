package com.example.todoproject.presentation.view.recyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarspedia.databinding.ForItemsBinding
import com.example.starwarspedia.data.models.PeopleModel

class GenericViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ForItemsBinding.bind(view)

    fun setInfoNotes(peopleModel: PeopleModel){
        binding.name.text = peopleModel.name
        binding.planet.text = peopleModel.homeworld
        binding.specie.text = ""
    }
}
