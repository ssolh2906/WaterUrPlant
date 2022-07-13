package com.holsui.wateryourplant3.newplant

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.holsui.wateryourplant3.database.Plant
import com.holsui.wateryourplant3.database.Repo
import com.holsui.wateryourplant3.plantlist.PlantListViewModel

class NewPlantViewModel : ViewModel() {
    private val repo = Repo()

    fun onSubmitButtonClicked(view : View, plant : Plant) {
        repo.addNewPlant(plant)
        view.findNavController().navigate(NewPlantFragmentDirections.actionNewPlantFragmentToPlantListFragment())
    }

    fun onButtonLinkDeviceClicked(view: View) {
        repo.getAvailableDevices()
    }
}