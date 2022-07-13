package com.holsui.wateryourplant3.database

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

private var database = Firebase.database.reference

interface FiredatabaseAccessInterface {

    fun writeNewPlant(plant: Plant) {
        database.child("plants").child(plant.id.toString()).setValue(plant)
    }
}