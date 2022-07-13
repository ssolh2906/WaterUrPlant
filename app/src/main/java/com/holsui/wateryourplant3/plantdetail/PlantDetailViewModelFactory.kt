package com.holsui.wateryourplant3.plantdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PlantDetailViewModelFactory(

) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlantDetailViewModel::class.java)) {
            return PlantDetailViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}