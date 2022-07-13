package com.holsui.wateryourplant3.database

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Plant(
    val id: String? = null,
    var name: String? = null,
    var commandStack : List<String>? = null
        )
{
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.

}