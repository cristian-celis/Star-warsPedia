package com.example.starwarspedia.presentation.fragments

import com.example.starwarspedia.presentation.adapterRV.GenericAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarspedia.R
import com.example.starwarspedia.core.AdapterType
import com.example.starwarspedia.core.error
import com.example.starwarspedia.core.isLoaded
import com.example.starwarspedia.core.isLoading
import com.example.starwarspedia.core.reloadPage
import com.example.starwarspedia.data.models.GenericModel
import com.example.starwarspedia.databinding.ForItemsBinding
import com.example.starwarspedia.databinding.FragmentPeopleBinding
import com.example.starwarspedia.presentation.contentFragments.ContentPeopleFragment
import com.example.starwarspedia.presentation.contentFragments.ContentPlanetFragment
import com.example.starwarspedia.presentation.contentFragments.ContentSpeciesFragment
import com.example.starwarspedia.presentation.viewModel.FragmentsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentPeopleBinding
    private lateinit var recyclerView: RecyclerView

    private var isLoading = false

    private lateinit var currentFragment: AdapterType

    private lateinit var genericAdapter: GenericAdapter<GenericModel>

    private val fragmentsViewModel: FragmentsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPeopleBinding.inflate(layoutInflater)

        setAdapter()
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        itemsListener()

        setObserverPattern()
        setInfiniteScroll()
        startNewView(AdapterType.PEOPLE)

        initNavigationView()
        initReloadListener()

        return binding.root
    }

    private fun setAdapter() {
        genericAdapter = GenericAdapter(R.layout.for_items) { binding, genericModel ->
            if (genericModel.firstData == null) genericModel.firstData = "Unknown"
            if (genericModel.secondData == null) genericModel.secondData = "Unknown"
            setViewHolderData(binding, genericModel.name, genericModel.firstData!!)
        }
    }

    private fun startNewView(newCurrentFragment: AdapterType) {
        genericAdapter.removeAllItems()
        recyclerView.adapter = genericAdapter
        currentFragment = newCurrentFragment
        getAllList()
    }

    private fun setViewHolderData(binding: View, text1: String, text2: String) {
        val itemBinding = ForItemsBinding.bind(binding)
        itemBinding.name.text = text1
        itemBinding.planet.text = text2
    }

    private fun setLoading(isSomethingLoading: Boolean) {
        isLoading = isSomethingLoading
        if (isSomethingLoading) binding.isLoading() else binding.isLoaded()
    }

    private fun setObserverPattern() {
        fragmentsViewModel.genericList.observe(viewLifecycleOwner) {
            if (it.isEmpty()) error() else {
                if (currentFragment == it[0].modelType) {
                    genericAdapter.addItems(it)
                    setLoading(false)
                }
            }
        }
    }

    private fun error() {
        binding.error()
        Toast.makeText(requireContext(), "There was an error", Toast.LENGTH_SHORT).show()
    }

    private fun initReloadListener() {
        binding.reloadBtt.setOnClickListener {
            binding.reloadPage()
            startNewView(currentFragment)
        }
    }

    private fun transactionClickOnItem(fragment: Fragment) {
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.mainContainer, fragment)
            addToBackStack(null)
            commit()
        }
    }

    private fun itemsListener() {
        genericAdapter.setOnClickListener(object : GenericAdapter.OnClickListener<GenericModel> {
            override fun onClick(position: Int, item: GenericModel) {
                when (item.modelType) {
                    AdapterType.PEOPLE -> transactionClickOnItem(
                        ContentPeopleFragment(
                            fragmentsViewModel.getPerson(item.url)
                        )
                    )

                    AdapterType.SPECIES -> transactionClickOnItem(
                        ContentSpeciesFragment(
                            fragmentsViewModel.getSpecie(item.url)
                        )
                    )

                    AdapterType.PLANETS -> transactionClickOnItem(
                        ContentPlanetFragment(
                            fragmentsViewModel.getPlanet(item.url)
                        )
                    )
                }
            }
        })
    }

    private fun setInfiniteScroll() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && !isLoading) {
                    updateLists()
                }
            }
        })
    }

    private fun updateLists() {
        setLoading(true)
        when (currentFragment) {
            AdapterType.PEOPLE -> fragmentsViewModel.getPeopleList()
            AdapterType.PLANETS -> fragmentsViewModel.getPlanetsList()
            AdapterType.SPECIES -> fragmentsViewModel.getSpeciesList()
        }
    }

    private fun getAllList() {
        setLoading(true)
        when(currentFragment){
            AdapterType.PEOPLE -> fragmentsViewModel.searchPeopleList()
            AdapterType.PLANETS -> fragmentsViewModel.searchPlanetsList()
            AdapterType.SPECIES -> fragmentsViewModel.searchSpeciesList()
        }
    }
    private fun initNavigationView() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.peopleFragment -> {
                    if (currentFragment != AdapterType.PEOPLE) startNewView(AdapterType.PEOPLE)
                    true
                }

                R.id.speciesFragment -> {
                    if (currentFragment != AdapterType.SPECIES) startNewView(AdapterType.SPECIES)
                    true
                }

                R.id.planetsFragment -> {
                    if (currentFragment != AdapterType.PLANETS) startNewView(AdapterType.PLANETS)
                    true
                }

                else -> false
            }
        }
    }
}