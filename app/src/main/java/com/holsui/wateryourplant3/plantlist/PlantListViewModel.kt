package com.holsui.wateryourplant3.plantlist

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.holsui.wateryourplant3.database.FiredatabaseAccessInterface
import com.holsui.wateryourplant3.database.Plant
import com.holsui.wateryourplant3.database.Repo

class PlantListViewModel : ViewModel(){
    // Observe repo data > Change mutableData
    private val repo = Repo()
    var plantlistLiveData = fetchData()
    // Declare Live data
//    private var liveDataPlantList = MutableLiveData<MutableList<Plant?>>()

    fun fetchData(): LiveData<MutableList<Plant>> {
        val mutableData = MutableLiveData<MutableList<Plant>>()
        repo.getData().observeForever{
            mutableData.value = it
        }
        return mutableData
    }


    fun onNewPlantClicked(view: View) {
        view.findNavController().navigate(PlantListFragmentDirections.actionPlantListFragmentToNewPlantFragment())
        //view.findNavController().navigate(PlantListFragmentDirections.actionPlantListFragmentToNewPlantFragment(newId!!))
    }

    fun onNewPlantClickedDone(view: View) {
        plantlistLiveData = fetchData()
    }


}