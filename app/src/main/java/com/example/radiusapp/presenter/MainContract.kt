package com.example.radiusapp.presenter

import com.example.radiusapp.model.data.Facilities
import com.example.radiusapp.model.repository.localRepository.LocalFacilities
import io.reactivex.Observable
import io.realm.kotlin.query.RealmResults
import retrofit2.Response

interface MainContract {
    interface View {

        fun showProgress()
        fun hideProgress()
        fun setDataToAdapter(body: Facilities?)

    }

    interface Model {

        fun getDataFromServer(): Observable<Response<Facilities>>
        fun getDataFromLocalStorage(): RealmResults<LocalFacilities>
    }

    interface Presenter {
        fun onViewCreated()
        fun loadData()
        fun onButtonClick()
        fun onDestroy()
    }

}