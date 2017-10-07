package com.krishna.kakadiya.pizzaapp

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
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

class PizzaActivity : AppCompatActivity() {
    @Bind(R.id.toolbar_activity_name)
    internal lateinit var activityName: TextView
   //val activityName: TextView by bindView(R.id.toolbar_activity_name)
    //@BindView(R.id.toolbar) lateinit var toolbar: Toolbar

    @Bind(R.id.toolbar_back)
    internal lateinit var toolbarBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza)
        ButterKnife.bind(this)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        toolbarBack!!.visibility = View.GONE
        activityName!!.text = applicationContext.getString(R.string.activity_krishna)

    }

    @OnClick(R.id.toolbar_logo)
    fun home() {
        val intent = Intent(this@PizzaActivity, PizzaActivity::class.java)
        startActivity(intent)
    }

    @OnClick(R.id.imageView)
    fun onlineOrder() {
        val intent = Intent(this@PizzaActivity, MenuActivity::class.java)
        startActivity(intent)
    }

    @OnClick(R.id.order_text)
    fun orderNow() {
        val intent = Intent(this@PizzaActivity, MenuActivity::class.java)
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
