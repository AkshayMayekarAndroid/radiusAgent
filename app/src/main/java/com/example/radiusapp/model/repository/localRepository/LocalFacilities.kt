package com.example.radiusapp.model.repository.localRepository

import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class LocalFacilities : RealmObject {
    var id : Int = 0
    var localFacilities : String = ""
    var isUpdated : Boolean = false
    var date : String = ""
}