package com.example.radiusapp.model

import com.example.radiusapp.MyApplication
import com.example.radiusapp.model.data.Facilities
import com.example.radiusapp.model.network.apiService.ApiService
import com.example.radiusapp.model.repository.localRepository.LocalFacilities
import com.example.radiusapp.presenter.MainContract
import io.reactivex.Observable
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class MainModel @Inject constructor(
    private val apiService: ApiService,
    private val retrofit: Retrofit
) : MainContract.Model {
    override fun getDataFromServer(): Observable<Response<Facilities>> {


        val call = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("https://my-json-server.typicode.com/")
            .client(
                OkHttpClient
                .Builder()
                .build())
            .build().create(ApiService::class.java)

        var data =  call.getFacilities()

     //   println(data)




        return data
    }

    override fun getDataFromLocalStorage(): RealmResults<LocalFacilities> {
        return MyApplication.realm.query<LocalFacilities>().find()
    }
}