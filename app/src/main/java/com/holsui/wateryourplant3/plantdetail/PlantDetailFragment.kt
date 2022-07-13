package com.holsui.wateryourplant3.plantdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.holsui.wateryourplant3.R
import com.holsui.wateryourplant3.databinding.FragmentPlantDetailBinding


class PlantDetailFragment : Fragment() {
    val args: PlantDetailFragmentArgs by navArgs()
    lateinit var plantDetailViewModel : PlantDetailViewModel
    lateinit var binding : FragmentPlantDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_plant_detail, container,false)

        val viewModelFactory = PlantDetailViewModelFactory()
        plantDetailViewModel =
            ViewModelProvider(this, viewModelFactory).get(PlantDetailViewModel::class.java)
        binding.plantDetailViewModel = plantDetailViewModel

        val plantId = args.plantId.toString()
        plantDetailViewModel.loadPlantDetails(plantId)
        binding.plantIdTextView.text = plantId

        plantDetailViewModel.plantWeightHistoryLiveData.observe(viewLifecycleOwner, Observer {

        })




        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLiveData("weight")


    }

    private fun observeLiveData(label : String) {
        plantDetailViewModel.plantWeightHistoryLiveData.observe(
            viewLifecycleOwner,
            Observer { mutableList ->
                plantDetailViewModel.updateChart(mutableList,binding.lineChartWeight,label)
            }
        )

    }
}

