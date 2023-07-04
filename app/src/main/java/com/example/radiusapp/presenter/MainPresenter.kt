package com.example.radiusapp.presenter

import android.annotation.SuppressLint
import com.example.radiusapp.MyApplication
import com.example.radiusapp.model.data.Facilities
import com.example.radiusapp.model.repository.localRepository.LocalFacilities
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import javax.inject.Inject


class MainPresenter @Inject constructor(
    private val view: MainContract.View,
    private val model: MainContract.Model
) : MainContract.Presenter {


    override fun onViewCreated() {
        TODO("Not yet implemented")
    }


    override fun loadData() {


        val result = model.getDataFromLocalStorage()

        val formatter = SimpleDateFormat("MM-dd-yyyy")
        val datenow = formatter.format(Date()).toString()

        if (result.isEmpty().not() && result[0].date == datenow) {
            val testModel = Gson().fromJson(result[0].localFacilities, Facilities::class.java)
            view.setDataToAdapter(testModel)
        }else{
            model.getDataFromServer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Response<Facilities>> {
                    override fun onSubscribe(d: Disposable) {
                        view.showProgress()
                    }

                    override fun onError(e: Throwable) {
                        view.hideProgress()
                    }

                    override fun onComplete() {
                        view.hideProgress()
                    }

                    @SuppressLint("SimpleDateFormat")
                    override fun onNext(facilities: Response<Facilities>) {


                        val jsonString = Gson().toJson(facilities.body())

                        MyApplication.realm.writeBlocking {
                            val frogs: RealmResults<LocalFacilities> =
                                this.query<LocalFacilities>().find()
                            // call delete on the results of a query to delete those objects permanently
                            delete(frogs)
                        }

                        MyApplication.realm.writeBlocking {
                            copyToRealm(LocalFacilities().apply {
                                id = 0
                                localFacilities = jsonString.toString()
                                isUpdated = true
                                val formatter = SimpleDateFormat("MM-dd-yyyy")
                                val datenow = formatter.format(Date()).toString()
                                date = datenow
                            })
                        }
                        if (facilities.isSuccessful) {
                            view.setDataToAdapter(facilities.body())
                        }


                    }
                })
        }

    }

    override fun onButtonClick() {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        TODO("Not yet implemented")
    }
}

