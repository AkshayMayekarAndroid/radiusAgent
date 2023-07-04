package com.example.radiusapp.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.radiusapp.MyApplication
import com.example.radiusapp.R
import com.example.radiusapp.model.data.Exclusion
import com.example.radiusapp.model.data.Facilities
import com.example.radiusapp.model.data.Facility
import com.example.radiusapp.model.data.Option

class FacilitiesChildAdapter() : RecyclerView.Adapter<FacilitiesChildAdapter.AssetViewHolder>() {
    lateinit var options: List<Option>
     lateinit var facilityId : String
     lateinit var exclusions: List<List<Exclusion>>
     lateinit var updateListener: UpdateListener

    constructor(facilityId: String ,options: List<Option>,exclusions: List<List<Exclusion>>,updateListener: UpdateListener) : this() {
        this.facilityId = facilityId
        this.options = options
        this.exclusions = exclusions
        this.updateListener = updateListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_child_item, parent, false)
        return AssetViewHolder(v)
    }

    override fun onBindViewHolder(@NonNull holder: AssetViewHolder, position: Int) {
        holder.updateAsset(options[position],position)
    }

    override fun getItemCount(): Int {
        return options.size
    }


    inner class AssetViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var nameTextView: TextView
        private var ivImage: ImageView
        private var view: View
        private var cvItem: CardView

        init {
            view = v
            nameTextView = v.findViewById(R.id.tv_name)
            ivImage = v.findViewById(R.id.ivImage)
            cvItem = v.findViewById(R.id.cvItem)


        }

        fun updateAsset(options: Option, position: Int) {
            nameTextView.text = options.name
            if (options.id.toInt() == 1) {
                ivImage.setImageResource(R.drawable.ic_action_apartment)
            } else if (options.id.toInt() == 2) {
                ivImage.setImageResource(R.drawable.ic_action_condo)
            } else if (options.id.toInt() == 3) {
                ivImage.setImageResource(R.drawable.ic_action_boat)
            } else if (options.id.toInt() == 4) {
                ivImage.setImageResource(R.drawable.ic_action_land)
            } else if (options.id.toInt() == 6) {
                ivImage.setImageResource(R.drawable.ic_action_rooms)
            } else if (options.id.toInt() == 7) {
                ivImage.setImageResource(R.drawable.ic_action_noroom)
            } else if (options.id.toInt() == 10) {
                ivImage.setImageResource(R.drawable.ic_action_swimming)
            } else if (options.id.toInt() == 11) {
                ivImage.setImageResource(R.drawable.ic_action_garden)
            } else if (options.id.toInt() == 12) {
                ivImage.setImageResource(R.drawable.ic_action_garage)
            }
            if(facilityId == "1") {
                if (MyApplication.seletedId == options.id.toInt()) {
                    cvItem.setCardBackgroundColor(Color.parseColor("#B9A3713C"))
                } else {
                    cvItem.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                }
                cvItem.setOnClickListener {
                    MyApplication.seletedId = options.id.toInt()
                    cvItem.setCardBackgroundColor(Color.parseColor("#B9A3713C"))
                    updateListener.onDataUpdate()
                    notifyDataSetChanged()
                }
            }else{
               var exc =  exclusions[0]

                for (item in exc){
                    if(item.facility_id == MyApplication.seletedId.toString() && item.options_id == options.id){
                        cvItem.visibility = View.GONE

                    }else{
                        cvItem.visibility = View.VISIBLE
                    }
                }
            }
        }

    }

}