package com.krishna.kakadiya.pizzaapp

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

/**
 * @author Krishna Kakadiya
 * *
 * @date 10-7-2017
 * *
 * @file PizzaListAdapter.java
 */

class PizzaListAdapter(context: Activity, private val mPizzaName: Array<String>, private val mPizzaDescription: Array<String>, private val mPizzaRate: Array<String>, private val mImageId: Array<Int>) : ArrayAdapter<String>(context, R.layout.activity_custom_pizza_list, mPizzaName) {
    private val mLayoutInflater: LayoutInflater

    init {
        this.mLayoutInflater = LayoutInflater.from(context)
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var view = view
        var holder: ViewHolder? = null
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.activity_custom_pizza_list, parent, false)
            holder = ViewHolder()
            holder.mName = view!!.findViewById(R.id.pizza_name) as TextView
            holder.mDescription = view.findViewById(R.id.pizza_description) as TextView
            holder.mRate = view.findViewById(R.id.pizza_Rate) as TextView
            holder.mImageView = view.findViewById(R.id.imageView) as ImageView
            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }
        holder.mName!!.text = mPizzaName[position]
        holder.mDescription!!.text = mPizzaDescription[position]
        holder.mRate!!.text = mPizzaRate[position]
        holder.mImageView!!.setBackgroundResource(mImageId[position])

        return view
    }

    internal class ViewHolder {
        internal var mName: TextView? = null
        internal var mDescription: TextView? = null
        internal var mRate: TextView? = null
        internal var mImageView: ImageView? = null
    }
}
