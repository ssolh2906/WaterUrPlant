package com.holsui.wateryourplant3.plantlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.holsui.wateryourplant3.R
import com.holsui.wateryourplant3.database.Plant
import com.holsui.wateryourplant3.databinding.FragmentPlantListBinding


class PlantListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // data binding
        val binding : FragmentPlantListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_plant_list, container, false )

        // data Source : Instance for Access Obj

        val viewModelFactory = PlantListViewModelFactory()

        val plantListViewModel =
            ViewModelProvider(this, viewModelFactory).get(PlantListViewModel::class.java)

        binding.plantListViewModel = plantListViewModel


        val observer : Observer<MutableList<Plant>> =
            Observer{ plantList ->
                binding.recyclerView.adapter = ListAdapter(plantList)
        }


        plantListViewModel.plantlistLiveData.observe(viewLifecycleOwner, observer)

        binding.lifecycleOwner = this

        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)

        binding.buttonNewPlant.setOnClickListener {
            view?.let { it1 -> plantListViewModel.onNewPlantClicked(it1) }

        }


        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // observe > updateUI

    }


}