package com.holsui.wateryourplant3.plantlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PlantListViewModelFactory(

) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(PlantListViewModel::class.java)) {
        return PlantListViewModel() as T
        }
    throw IllegalArgumentException("Unknown ViewModel class")
    }
}