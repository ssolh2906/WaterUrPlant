package com.holsui.wateryourplant3.plantlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.holsui.wateryourplant3.R
import com.holsui.wateryourplant3.database.Plant
import com.holsui.wateryourplant3.databinding.ListItemBinding

class ListAdapter(private val plantList : MutableList<Plant>)
    : RecyclerView.Adapter<ListAdapter.PlantItemHolder>(){
//    plantList = mutableListOf<Plant>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.PlantItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        val binding = DataBindingUtil.inflate<ListItemBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.list_item,
                    parent, false)
        return PlantItemHolder(binding)
    }

    override fun onBindViewHolder(holder: ListAdapter.PlantItemHolder, position: Int) {
        val currPlant = plantList[position]

        holder.nameTextFiled.text = currPlant.name

        holder.listItemImage.setOnClickListener(
            Navigation.createNavigateOnClickListener(PlantListFragmentDirections.actionPlantListFragmentToPlantDetailFragment(currPlant.id!!))
        )
        holder.nameTextFiled.setOnClickListener(
            Navigation.createNavigateOnClickListener(PlantListFragmentDirections.actionPlantListFragmentToPlantDetailFragment(currPlant.id!!))
        )
    }

    override fun getItemCount() :Int{
        return plantList.size
    }

    inner class PlantItemHolder(private val binding: ListItemBinding)
        :RecyclerView.ViewHolder(binding.root) {

        var nameTextFiled = binding.textViewPlantName
        var listItemLinear = binding.listItemLinear
        var listItemImage = binding.listItemImage
    }


}

class PlantDiffCallback :DiffUtil.ItemCallback<Plant>(){
    override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem.id == newItem.id


    }

    override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem==newItem
    }
}

class PlantListener(val clickListener :(plantid:String) -> Unit) {
    fun onClick(plant: Plant) = clickListener(plant.id!!)
}
