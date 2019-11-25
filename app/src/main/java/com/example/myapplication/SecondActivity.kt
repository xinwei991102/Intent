package com.example.myapplication

import android.app.Activity
import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val message = intent.getStringExtra(MainActivity.MSG)
        val luckyNumber = intent.getIntExtra(MainActivity.LUCKY, 0)
        textViewMessage.text = String.format("%s %s %d",
            getString(R.string.message),
            message,
            luckyNumber)


        buttonDone.setOnClickListener {

            if (editTextReply.text.isNotBlank()) {
                val reply = editTextReply.text.toString()
                //val intent = getIntent()  //return the MainActivity intent
                intent.putExtra(MainActivity.REPLY, reply)

                //inform the MainActivity that everything is ok
                setResult(Activity.RESULT_OK, intent)
            } else {
                setResult(Activity.RESULT_CANCELED, intent)
            }

            finish()

            //Implicit intent
            //val intent = Intent(Intent.ACTION_WEB_SEARCH)
            //intent.putExtra(SearchManager.QUERY, message)
            //startActivity(intent)
        }
    }
}
