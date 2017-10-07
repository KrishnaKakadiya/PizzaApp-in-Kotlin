package com.krishna.kakadiya.pizzaapp

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView

import butterknife.Bind
import butterknife.ButterKnife
import butterknife.OnClick

/**
 * @author eInfochips
 * *
 * @date 27-11-2015
 * *
 * @file Constant.java
 */
class MenuActivity : AppCompatActivity() {

    @Bind(R.id.pizza_list_item)
    internal lateinit var pizzaListItem: ListView

    @Bind(R.id.toolbar_activity_name)
    internal lateinit var activityName: TextView

    internal var imageId = arrayOf(R.drawable.pro1, R.drawable.pro2, R.drawable.pro3, R.drawable.pro4, R.drawable.pro5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        ButterKnife.bind(this)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        activityName!!.text = applicationContext.getString(R.string.activity_menu)

        val res = resources
        val pizzaName = res.getStringArray(R.array.pizza_name)
        val pizzaDescription = res.getStringArray(R.array.pizza_description)
        val pizzaRate = res.getStringArray(R.array.pizza_rate)

        pizzaListItem!!.adapter = null
        pizzaListItem!!.adapter = PizzaListAdapter(this, pizzaName, pizzaDescription, pizzaRate, imageId)
        pizzaListItem!!.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val intent = Intent(this@MenuActivity, PizzaDetailActivity::class.java)
            startActivity(intent)
        }
    }

    @OnClick(R.id.toolbar_back)
    fun back() {
        finish()
    }

    @OnClick(R.id.toolbar_logo)
    fun home() {
        finishAffinity()
        val intent = Intent(this@MenuActivity, PizzaActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.miHelp -> {
                help()
                return true
            }
            R.id.miPizza -> {
                openPizzaWebsite()
                return true
            }
            R.id.miYourName -> {
                mySelf()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun mySelf() {
        val alertDialog = AlertDialog.Builder(this).create()
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.setMessage(this.getString(R.string.my_self))

        alertDialog.setButton(this.getString(R.string.ok)) { dialog, which -> dialog.cancel() }
        alertDialog.show()
    }

    private fun openPizzaWebsite() {
        val url = "https://www.dominos.ca"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }

    private fun help() {
        val url = "http://www.google.com"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }
}
