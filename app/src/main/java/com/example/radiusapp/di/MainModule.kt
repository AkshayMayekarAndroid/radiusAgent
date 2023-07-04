package com.example.radiusapp.di

import com.example.radiusapp.model.MainModel
import com.example.radiusapp.presenter.MainContract
import com.example.radiusapp.presenter.MainPresenter
import com.example.radiusapp.view.MainActivity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class MainModule {

    @Binds
    abstract fun bindActivity(activity : MainActivity) : MainContract.View
    @Binds
    abstract fun bindPresenter(impl: MainPresenter): MainContract.Presenter

    @Binds
    abstract fun bindMainModel(impl: MainModel): MainContract.Model

}

