package com.comp304.santiago_dongheun_comp304sec002_lab04.data

interface PlacesRepository {
    fun getPlacesByCategory(category: Category): List<Place>
    fun getPlace(index: Int): Place
    fun getPlaceIndex(place:Place):Int
}