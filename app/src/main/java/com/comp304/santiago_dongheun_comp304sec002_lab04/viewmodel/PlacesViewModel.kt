package com.comp304.santiago_dongheun_comp304sec002_lab04.viewmodel

import androidx.lifecycle.ViewModel
import com.comp304.santiago_dongheun_comp304sec002_lab04.data.Category
import com.comp304.santiago_dongheun_comp304sec002_lab04.data.Place
import com.comp304.santiago_dongheun_comp304sec002_lab04.data.PlacesRepository

class PlacesViewModel(
    private val placesRepository: PlacesRepository
) :ViewModel(){
    fun getPlacesByCategory(category: Category):List<Place> = placesRepository.getPlacesByCategory(category)
    fun getPlace(index: Int):Place = placesRepository.getPlace(index)
    fun getCategories():List<String> = Category.entries.map { it.name }
    fun getPlaceIndex(place: Place):Int = placesRepository.getPlaceIndex(place)
}