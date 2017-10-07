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
import android.widget.AdapterView
import android.widget.CheckBox
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

import butterknife.Bind
import butterknife.ButterKnife
import butterknife.OnClick

/**
 * @author Krishna Kakadiya
 * *
 * @date 10-7-2017
 * *
 * @file PizzaDetailActivity.java
 */


class PizzaDetailActivity : AppCompatActivity() {

    @Bind(R.id.chk_1)
    internal lateinit var chk_1: CheckBox

    @Bind(R.id.chk_2)
    internal lateinit var chk_2: CheckBox

    @Bind(R.id.chk_3)
    internal lateinit var chk_3: CheckBox

    @Bind(R.id.chk_4)
    internal lateinit var chk_4: CheckBox

    @Bind(R.id.chk_5)
    internal lateinit var chk_5: CheckBox

    @Bind(R.id.chk_6)
    internal lateinit var chk_6: CheckBox

    @Bind(R.id.chk_7)
    internal lateinit var chk_7: CheckBox

    @Bind(R.id.chk_8)
    internal lateinit var chk_8: CheckBox

    @Bind(R.id.chk_9)
    internal lateinit var chk_9: CheckBox

    @Bind(R.id.chk_10)
    internal lateinit var chk_10: CheckBox

    @Bind(R.id.toolbar_activity_name)
    internal lateinit var activityName: TextView

    private var spinnerDough: Spinner? = null
    private var spinnerSize: Spinner? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza_detail)
        ButterKnife.bind(this)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        activityName!!.text = applicationContext.getString(R.string.activity_detail)
    }

    @OnClick(R.id.toolbar_back)
    fun back() {
        finish()
    }

    @OnClick(R.id.toolbar_logo)
    fun home() {
        finishAffinity()
        val intent = Intent(this@PizzaDetailActivity, PizzaActivity::class.java)
        // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        /*intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);*/
        startActivity(intent)
    }

    fun addListenerOnSpinnerItemSelection() {
        spinnerDough = findViewById(R.id.spinner_dough) as Spinner
        spinnerSize = findViewById(R.id.spinner_size) as Spinner

        spinnerDough!!.onItemSelectedListener = CustomOnItemSelectedListener()
        spinnerSize!!.onItemSelectedListener = CustomOnItemSelectedListener()
    }

    @OnClick(R.id.btn_checkout)
    fun checkOut() {
        val intent = Intent(this@PizzaDetailActivity, ShippingAddressActivity::class.java)
        startActivity(intent)
    }

    public override fun onPause() {
        super.onPause()

        if (chk_1!!.isChecked) {
            chk_1!!.isChecked = false
        }
        if (chk_2!!.isChecked) {
            chk_2!!.isChecked = false
        }
        if (chk_3!!.isChecked) {
            chk_3!!.isChecked = false
        }
        if (chk_4!!.isChecked) {
            chk_4!!.isChecked = false
        }
        if (chk_5!!.isChecked) {
            chk_5!!.isChecked = false
        }
        if (chk_6!!.isChecked) {
            chk_6!!.isChecked = false
        }
        if (chk_7!!.isChecked) {
            chk_7!!.isChecked = false
        }
        if (chk_8!!.isChecked) {
            chk_8!!.isChecked = false
        }
        if (chk_9!!.isChecked) {
            chk_9!!.isChecked = false
        }
        if (chk_10!!.isChecked) {
            chk_10!!.isChecked = false
        }

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

internal class CustomOnItemSelectedListener : AdapterView.OnItemSelectedListener {

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        Toast.makeText(parent.context,
                "You choose " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(arg0: AdapterView<*>) {
        // TODO Auto-generated method stub
    }

}
