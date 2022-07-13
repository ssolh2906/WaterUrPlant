package com.holsui.wateryourplant3.plantdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.holsui.wateryourplant3.database.Repo

class PlantDetailViewModel : ViewModel() {
    val repo = Repo()
    // live data
    var data : LineData? = null
    lateinit var plantWeightHistoryLiveData : LiveData<MutableList<MutableList<Double>>>
    lateinit var plantMoistureHistoryLiveData : LiveData<MutableList<MutableList<Double>>>

    lateinit var weightLineDataSet : LineDataSet
    lateinit var weightDataSets : ArrayList<LineDataSet>

    fun loadPlantDetails(plantId : String) {
        plantWeightHistoryLiveData= repo.readAverageFromPlantHistory(plantId, "weight")
        //plantMoistureHistoryLiveData= repo.readAverageFromPlantHistory(plantId, "moisture")
    }

    fun updateChart(mutableList: MutableList<MutableList<Double>>, chart: LineChart ,label: String) {
        data = getLineData(mutableList, label)
        chart.data = data
        chart.invalidate()
    }

    private fun getLineData(mutableList: MutableList<MutableList<Double>>, label: String) : LineData {
        var allValueInOrder = mutableListOf<Double>()
        var indexVals : ArrayList<Entry> = ArrayList()
        var x = 0f
        for (dailyList in mutableList) {
            for (hourAverage in dailyList) {
                allValueInOrder.add(hourAverage)
                indexVals.add(Entry(x, hourAverage.toFloat()))
                x += 1
            }
        }

        weightLineDataSet = LineDataSet(indexVals, label)

        weightLineDataSet.setDrawCircles(false)

        weightDataSets  = ArrayList()
        weightDataSets.add(weightLineDataSet)

        data = LineData(weightDataSets as List<ILineDataSet>?)

        return data as LineData
    }
}