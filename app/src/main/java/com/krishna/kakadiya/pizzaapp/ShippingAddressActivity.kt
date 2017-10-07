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
import android.widget.EditText
import android.widget.TextView

import butterknife.Bind
import butterknife.ButterKnife
import butterknife.OnClick

/**
 * @author Krishna Kakadiya
 * *
 * @date 10-7-2017
 * *
 * @file ShippingAddressActivity.java
 */

class ShippingAddressActivity : AppCompatActivity() {

    @Bind(R.id.name)
    internal lateinit var name: EditText

    @Bind(R.id.address)
    internal lateinit var address: EditText

    @Bind(R.id.contact_number)
    internal lateinit var contactNumber: EditText

    @Bind(R.id.credit_card)
    internal lateinit var creditCard: EditText

    @Bind(R.id.toolbar_activity_name)
    internal lateinit var activityName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shipping_address)
        ButterKnife.bind(this)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        activityName!!.text = applicationContext.getString(R.string.activity_address)
    }

    @OnClick(R.id.toolbar_back)
    fun back() {
        finish()
    }

    @OnClick(R.id.toolbar_logo)
    fun home() {
        finishAffinity()
        val intent = Intent(this@ShippingAddressActivity, PizzaActivity::class.java)
        startActivity(intent)
    }


    val isValidate: Boolean
        get() {
            var isValid = true

            if (name!!.text.toString().matches("".toRegex())) {
                isValid = false
            }
            if (address!!.text.toString().matches("".toRegex())) {
                isValid = false
            }
            if (contactNumber!!.text.toString().matches("".toRegex())) {
                isValid = false
            }
            if (creditCard!!.text.toString().matches("".toRegex())) {
                isValid = false
            }
            return isValid
        }
    private val isValidateMobile: Boolean
        get() {
            var isValid = true

            if (contactNumber!!.text.toString().length != 10) {
                isValid = false
            }
            return isValid
        }
    private val isValidateCreditCard: Boolean
        get() {
            var isValid = true

            if (creditCard!!.text.toString().length != 14) {
                isValid = false
            }
            return isValid
        }

    @OnClick(R.id.btn_finalize_order)
    fun finalizeOrder() {
        if (isValidate) {

            if (isValidateMobile) {
                if (isValidateCreditCard) {
                    val intent = Intent(this@ShippingAddressActivity, ThankYouActivity::class.java)
                    intent.putExtra(Constant.KEY_NAME, name!!.text.toString())
                    intent.putExtra(Constant.KEY_ADDRESS, address!!.text.toString())
                    intent.putExtra(Constant.KEY_CONTACT_NUMBER, contactNumber!!.text.toString())
                    startActivity(intent)
                } else {
                    val alertDialog = AlertDialog.Builder(this).create()
                    alertDialog.setCanceledOnTouchOutside(false)
                    alertDialog.setMessage(this.getString(R.string.validate_credit_card))

                    alertDialog.setButton(this.getString(R.string.ok)) { dialog, which -> dialog.cancel() }
                    alertDialog.show()
                }

            } else {
                val alertDialog = AlertDialog.Builder(this).create()
                alertDialog.setCanceledOnTouchOutside(false)
                alertDialog.setMessage(this.getString(R.string.validate_mobile))

                alertDialog.setButton(this.getString(R.string.ok)) { dialog, which -> dialog.cancel() }
                alertDialog.show()
            }
        } else {
            val alertDialog = AlertDialog.Builder(this).create()
            alertDialog.setCanceledOnTouchOutside(false)
            alertDialog.setMessage(this.getString(R.string.validate_order))

            alertDialog.setButton(this.getString(R.string.ok)) { dialog, which -> dialog.cancel() }
            alertDialog.show()
        }
    }

    @OnClick(R.id.btn_cancel_order)
    fun cancelOrder() {
        finishAffinity()
        val intent = Intent(this@ShippingAddressActivity, PizzaActivity::class.java)
        startActivity(intent)
    }

    public override fun onPause() {
        super.onPause()
        name!!.setText("")
        address!!.setText("")
        contactNumber!!.setText("")
        creditCard!!.setText("")
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
