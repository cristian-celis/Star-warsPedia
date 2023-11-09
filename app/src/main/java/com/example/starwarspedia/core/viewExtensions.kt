package com.example.starwarspedia.core

import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.starwarspedia.databinding.FragmentContentPeopleBinding
import com.example.starwarspedia.databinding.FragmentContentPlanetBinding
import com.example.starwarspedia.databinding.FragmentContentSpeciesBinding
import com.example.starwarspedia.databinding.FragmentPeopleBinding

fun FragmentPeopleBinding.isLoading(){
    this.progressBar.visibility = View.VISIBLE
    this.reloadBtt.visibility = View.INVISIBLE
}

fun FragmentPeopleBinding.isLoaded(){
    this.progressBar.visibility = View.INVISIBLE
}

fun FragmentContentPeopleBinding.isLoading(){
    this.filmsLoading.visibility = View.VISIBLE
}
fun FragmentContentPeopleBinding.isLoaded(){
    this.filmsLoading.visibility = View.INVISIBLE
}

fun FragmentContentPlanetBinding.isLoading(){
    this.filmsLoading.visibility = View.VISIBLE
}
fun FragmentContentPlanetBinding.isLoaded(){
    this.filmsLoading.visibility = View.INVISIBLE
}

fun FragmentContentSpeciesBinding.isLoading(){
    this.filmsLoading.visibility = View.VISIBLE
}
fun FragmentContentSpeciesBinding.isLoaded(){
    this.filmsLoading.visibility = View.INVISIBLE
}

fun FragmentPeopleBinding.error() {
    this.isLoaded()
    this.reloadBtt.visibility = View.VISIBLE
}

fun FragmentPeopleBinding.reloadPage(){
    this.reloadBtt.visibility = View.INVISIBLE
}