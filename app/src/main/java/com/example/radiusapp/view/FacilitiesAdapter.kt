package com.example.radiusapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.radiusapp.R
import com.example.radiusapp.model.data.Facilities
import com.example.radiusapp.model.data.Facility

class FacilitiesAdapter() : RecyclerView.Adapter<FacilitiesAdapter.AssetViewHolder>() {
        lateinit var facilities : Facilities

        constructor( facilities: Facilities) : this() {
            this.facilities = facilities
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetViewHolder {
            val v: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_item, parent, false)
            return AssetViewHolder(v)
        }

        override fun onBindViewHolder(@NonNull holder: AssetViewHolder, position: Int) {
            holder.updateAsset(facilities.facilities[position])
        }

        override fun getItemCount(): Int {
            return facilities.facilities.size
        }



        inner class AssetViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            private val nameTextView: TextView
            private val recyclerView: RecyclerView
            private val view: View
            init {
                view = v
                nameTextView = v.findViewById(R.id.tv_name)
                recyclerView = v.findViewById(R.id.rv_options)
            }

            fun updateAsset(facility: Facility) {
                nameTextView.text = facility.name
                val manager: RecyclerView.LayoutManager = LinearLayoutManager(view.context,LinearLayoutManager.HORIZONTAL,false)
                recyclerView.layoutManager = manager
                val facilitiesChildAdpater = FacilitiesChildAdapter(facility.facility_id,facility.options,facilities.exclusions, object : UpdateListener{
                    override fun onDataUpdate() {
                        notifyDataSetChanged()
                    }

                })
                recyclerView.adapter= facilitiesChildAdpater
            }

        }
}

