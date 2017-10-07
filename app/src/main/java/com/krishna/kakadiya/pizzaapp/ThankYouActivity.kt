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
import android.widget.TextView

import butterknife.Bind
import butterknife.ButterKnife
import butterknife.OnClick

/**
 * @author Krishna Kakadiya
 * *
 * @date 7-10-2017
 * *
 * @file ThankYouActivity.java
 */


class ThankYouActivity : AppCompatActivity() {

    @Bind(R.id.name)
    internal lateinit var txtName: TextView

    @Bind(R.id.address)
    internal lateinit var txtAddress: TextView

    @Bind(R.id.contact_number)
    internal lateinit var txtContactNumber: TextView

    @Bind(R.id.toolbar_activity_name)
    internal lateinit var activityName: TextView

    internal var name: String = ""
    internal var address: String = ""
    internal var contactNumber: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thankyou)
        ButterKnife.bind(this)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        activityName!!.text = applicationContext.getString(R.string.activity_order)

        val intent = intent
        name = intent.getStringExtra(Constant.KEY_NAME)
        address = intent.getStringExtra(Constant.KEY_ADDRESS)
        contactNumber = intent.getStringExtra(Constant.KEY_CONTACT_NUMBER)

        txtName!!.text = name
        txtAddress!!.text = address
        txtContactNumber!!.text = contactNumber
    }

    @OnClick(R.id.toolbar_back)
    fun back() {
        finish()
    }

    @OnClick(R.id.toolbar_logo)
    fun home() {
        finishAffinity()
        val intent = Intent(this@ThankYouActivity, PizzaActivity::class.java)
        startActivity(intent)
    }

    @OnClick(R.id.btn_thank_you)
    fun orderPlaced() {
        finishAffinity()
        val intent = Intent(this@ThankYouActivity, PizzaActivity::class.java)
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
