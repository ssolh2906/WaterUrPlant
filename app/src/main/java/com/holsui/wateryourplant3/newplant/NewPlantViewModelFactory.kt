package com.holsui.wateryourplant3.newplant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NewPlantViewModelFactory() : ViewModelProvider.Factory {
    @Suppress("uncheck_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewPlantViewModel::class.java)) {
            return NewPlantViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}