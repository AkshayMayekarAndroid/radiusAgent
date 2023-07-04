package com.example.radiusapp.view


import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.ContentLoadingProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.radiusapp.R
import com.example.radiusapp.model.data.Facilities
import com.example.radiusapp.presenter.MainContract
import com.example.radiusapp.presenter.MainPresenter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainContract.View  {


   // private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var mainPresenter: MainPresenter
    lateinit var recyclerview : RecyclerView
    lateinit var clpb : ProgressBar

    lateinit var  mProgressDialog : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       // binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        recyclerview = findViewById(R.id.rv_facilities)
        clpb = findViewById(R.id.clpb)

       // retrofit.getFacilities()

        mainPresenter.loadData()
    }

    override fun showProgress() {
        clpb.isIndeterminate = true
        clpb.visibility = View.VISIBLE
    }

    override fun hideProgress() {

        clpb.isIndeterminate = false
        clpb.visibility = View.GONE
    }

    override fun setDataToAdapter(body: Facilities?) {

        val manager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = manager
        var facilitiesAdpater = body?.let {
            FacilitiesAdapter(it)
        }
        recyclerview.adapter= facilitiesAdpater

    }

}