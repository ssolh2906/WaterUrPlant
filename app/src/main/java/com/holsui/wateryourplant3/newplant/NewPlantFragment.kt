package com.holsui.wateryourplant3.newplant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.holsui.wateryourplant3.R
import com.holsui.wateryourplant3.database.Plant
import com.holsui.wateryourplant3.databinding.FragmentNewPlantBinding
import com.holsui.wateryourplant3.plantlist.PlantListViewModel

class NewPlantFragment :Fragment(){
    //val args: NewPlantFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentNewPlantBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_new_plant, container, false)

        val viewModelFactory = NewPlantViewModelFactory()
        val newPlantViewModel =
            ViewModelProvider(this, viewModelFactory).get(NewPlantViewModel::class.java)

        binding.buttonSubmit.setOnClickListener {
            var newPlant = Plant()
            newPlant.name = binding.plantNameEditTextView.text.toString()
            newPlantViewModel.onSubmitButtonClicked(it, newPlant)

        }

        binding.buttonLinkDevice.setOnClickListener {
            newPlantViewModel.onButtonLinkDeviceClicked(it)
        }


        return binding.root
    }

}