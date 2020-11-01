package com.example.calendar

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.calendar.fragments.AFragment


class Contacts : AppCompatActivity() {

    companion object {


    }
    var contacts: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        contacts = findViewById<TextView>(R.id.tv)

    }

}