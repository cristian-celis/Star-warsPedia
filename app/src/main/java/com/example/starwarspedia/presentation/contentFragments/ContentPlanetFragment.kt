package com.example.starwarspedia.presentation.contentFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarspedia.R
import com.example.starwarspedia.core.isLoaded
import com.example.starwarspedia.core.isLoading
import com.example.starwarspedia.databinding.FragmentContentPeopleBinding
import com.example.starwarspedia.databinding.FragmentContentPlanetBinding
import com.example.starwarspedia.databinding.ItemsInfoContentBinding
import com.example.starwarspedia.data.models.PlanetsModel
import com.example.starwarspedia.presentation.adapterRV.GenericAdapter
import com.example.starwarspedia.presentation.viewModel.FragmentsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContentPlanetFragment constructor(
    private val planetsModel: PlanetsModel
) : Fragment() {

    private lateinit var binding: FragmentContentPlanetBinding
    private lateinit var recyclerViewDescription: RecyclerView
    private lateinit var recyclerViewGeneral: RecyclerView
    private lateinit var recyclerViewFilms: RecyclerView

    private lateinit var genericAdapter: GenericAdapter<String>

    private val fragmentsViewModel: FragmentsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContentPlanetBinding.inflate(layoutInflater)

        filmsGetterObserver()
        setView()

        return binding.root
    }

    private fun filmsGetterObserver() {
        fragmentsViewModel.genericData.observe(viewLifecycleOwner) {
            binding.isLoaded()
            recyclerViewFilms = binding.films
            initRecyclerViewGeneral(recyclerViewFilms, it)
        }
        binding.isLoading()
        fragmentsViewModel.getFilms(planetsModel.films)
    }

    private fun setView() {
        binding.name.text = planetsModel.name

        recyclerViewDescription = binding.descriptionGeneralInfo
        initRecyclerViewGeneral(
            recyclerViewDescription, listOf(
                "rotation period", "orbital period", "diameter", "gravity", "terrain", "population"
            )
        )

        recyclerViewGeneral = binding.generalInfo
        initRecyclerViewGeneral(
            recyclerViewGeneral, listOf(
                planetsModel.rotationPeriod,
                planetsModel.orbitalPeriod,
                planetsModel.diameter,
                planetsModel.gravity,
                planetsModel.terrain,
                planetsModel.population

            )
        )
    }

    private fun setGenericAdapter() {
        genericAdapter =
            GenericAdapter(R.layout.items_info_content) { binding, infoList ->
                val itemsInfoContentBinding = ItemsInfoContentBinding.bind(binding)
                itemsInfoContentBinding.item.text = infoList
            }
    }

    private fun initRecyclerViewGeneral(recyclerView: RecyclerView, listData: List<String>) {
        setGenericAdapter()
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = genericAdapter
        genericAdapter.addItems(listData)
    }
}