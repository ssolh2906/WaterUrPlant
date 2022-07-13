package com.holsui.wateryourplant3.database

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class Repo {
    companion object {
        val database = Firebase.database
        val plantRef = database.getReference("Plant")
        val plantHistoryRef = database.getReference("PlantHistory")
    }

    fun getData(): LiveData<MutableList<Plant>> {
        val mutableData = MutableLiveData<MutableList<Plant>>()

        plantRef.addValueEventListener(object : ValueEventListener {
          val listData: MutableList<Plant> = mutableListOf<Plant>()
            override fun onDataChange(snapshot: DataSnapshot) {
                listData.clear()
                if (snapshot.exists()) {
                    for (plantSnapshot in snapshot.children) {
                        val getData = plantSnapshot.getValue<Plant>()
                        listData.add(getData!!)
                    }
                    mutableData.value = listData

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, "getData cancelled")
            }
        })

        return mutableData
    }

    fun addNewPlant(newPlant:Plant): String? {
        val pointer = plantRef.child("\"properties\"").push()
        val newId = pointer.key
        pointer.child("id").setValue(newId)
        pointer.child("name").setValue(newPlant.name)
        plantHistoryRef.child(newId!!).child("id").setValue(newId)

        return newId
    }

    fun getPlantbyId(plantId : String): MutableLiveData<Plant> {
        lateinit var plantLiveData : MutableLiveData<Plant>

        val plantRef = database.getReference("Plants").child("properties").child(plantId)
        plantRef.addValueEventListener(object : ValueEventListener{
            val plantData = Plant()
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    Log.d("Repo", "snapshot")
                    plantLiveData.value = plantData
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, "getPlantById cancelled")
            }
        })

        return plantLiveData
    }


    fun getAvailableDevices(): LiveData<MutableList<String>> {
        val mutableDevicesList = MutableLiveData<MutableList<String>>()
        val availableDevicesRef = database.getReference("\"Available Devices\"")
        availableDevicesRef.addValueEventListener(object : ValueEventListener {
            val listDevices = mutableListOf<String>()
            override fun onDataChange(snapshot: DataSnapshot) {
                listDevices.clear()
                if (snapshot.exists()){
                    for (deviceSnapshot in snapshot.children) {
                        // Parse JSON
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return mutableDevicesList

    }


    fun readAverageFromPlantHistory(plantId: String, label: String) : MutableLiveData<MutableList<MutableList<Double>>>{
        var resultLiveData = MutableLiveData<MutableList<MutableList<Double>>>()
        var resultData = mutableListOf<MutableList<Double>>()
        var countDate = 0;

        plantHistoryRef.child(plantId).addValueEventListener(object :ValueEventListener {

            var dailyData = mutableListOf<Double>()
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (date in snapshot.child(label).children) {
                        for (time in date.children) {
                                if (!time.child("average").exists()) {
                                    // add average
                                    val avg: Double = getAverage(time)
                                    time.child("average").ref.setValue(avg)
                                    //    .updateChildren(mutableMapOf("average" to avg) as Map<String, Any>)
                                    dailyData.add(avg)
                                } else {
                                    dailyData.add(time.child("average").value as Double)
                                }
                        }
                        countDate += 1

                        resultData.add(deepCopy(dailyData))
                        dailyData.clear()
                    }

                }
                resultLiveData.value = resultData
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return resultLiveData

    }

    private fun deepCopy(dailyData: MutableList<Double>): MutableList<Double> {
        var result = mutableListOf<Double>()
        for (item in dailyData) {
            result.add(item)
        }
        return result
    }

    private fun getAverage(time: DataSnapshot?): Double {
        var sum : Double = 0.0
        var count = 0
        for (value in time!!.children)  {
            sum += value.value as Double
            count += 1
        }
        return (sum / count)
    }


}


