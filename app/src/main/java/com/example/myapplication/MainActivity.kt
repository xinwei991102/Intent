package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonSend.setOnClickListener {
            sendMessage()
        }
        buttonCall.setOnClickListener {
            call()
        }
    }

    private fun call() {
        //Implicit intent
        val phoneNumber: String = "tel:0138155229"
        val intent = Intent(Intent.ACTION_DIAL)
        intent.setData(Uri.parse(phoneNumber))

        //check package manager for app to handle an intent
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun sendMessage() {
        //Explicit intent
        val intent = Intent(this, SecondActivity::class.java)
        val message = editTextMessage.text.toString()
        intent.putExtra(MSG, message)

        if (editTextLuckyNum.text.isNotEmpty()) {
            val luckyNumber =  editTextLuckyNum.text.toString().toInt()
            intent.putExtra(LUCKY, luckyNumber)
        }

        //startActivity(intent) //An intent without return value
        startActivityForResult(intent, REQUEST_CODE)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val reply = data?.getStringExtra(REPLY).toString()
                textViewReply.text = String.format("%s %s",
                    getString(R.string.reply),
                    reply)
            }
        }
    }

    companion object {
        const val MSG = "com.example.myapplication.MSG"
        const val LUCKY = "com.example.myapplication.LUCKY"
        const val REPLY = "com.example.myapplication.REPLY"
        const val REQUEST_CODE = 1
    }
}
