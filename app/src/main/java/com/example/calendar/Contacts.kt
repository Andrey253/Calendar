package com.example.calendar

import android.app.Activity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.*
import androidx.core.content.PermissionChecker
import com.example.calendar.model.ContactModel
import java.util.jar.Manifest


class Contacts : AppCompatActivity() {

    companion object {


    }
    var contacts: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        contacts = findViewById<TextView>(R.id.tv)
        checkpermission()
    }
    fun checkpermission() {
       if ( PermissionChecker.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS) == PermissionChecker.PERMISSION_GRANTED)

        initcontacts()

        else
           println("mytag checkSelfPermission false")
        //requestContactPermission(this) реализую запрос позже, в данный момент вручную на эмуляторе устанавливаю разрешение
    }

    private fun initcontacts() {
        //TODO("Not yet implemented")
        var arrayContacts = arrayListOf<ContactModel>()
        var cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null)
        cursor?.let {
            while (it.moveToNext()){
                val name = it.getString(it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val numberphone = it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                val newModel = ContactModel()
                newModel.fullname = name
                newModel.phone = numberphone.replace(Regex("[\\s,-]"),"")
                arrayContacts.add(newModel)
                println("mytag initcontacts $arrayContacts")
            }
        }
        cursor?.close()
    }
}