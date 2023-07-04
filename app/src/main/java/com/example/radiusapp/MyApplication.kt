package com.example.radiusapp

import android.app.Application
import com.example.radiusapp.model.repository.localRepository.LocalFacilities
import dagger.hilt.android.HiltAndroidApp
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration


@HiltAndroidApp
open class MyApplication : Application(){

    companion object {
        lateinit var realm: Realm
        var seletedId: Int = 0
        var rooms: Int = 0
        var other: Int = 0
    }
    override fun onCreate() {
        super.onCreate()

        val config = RealmConfiguration.Builder(setOf(LocalFacilities::class))
            .build()
        realm = Realm.open(config)

    }


}