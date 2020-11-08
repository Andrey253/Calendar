package com.example.calendar

import android.os.Bundle
import android.provider.ContactsContract
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.PermissionChecker
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calendar.model.ContactModel
import kotlinx.android.synthetic.main.contacts_layout.*
import androidx.recyclerview.widget.RecyclerView.LayoutManager


class Contacts : AppCompatActivity() {

    companion object {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contacts_layout)

        recyclerViewContact.layoutManager = LinearLayoutManager(this)

        checkpermission()
    }
    private fun checkpermission() {
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
                val username = it.getString(it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val numberphone = it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                val newModel = ContactModel()
                newModel.username = username
                newModel.phone = numberphone.replace(Regex("[\\s,-]"),"")
                arrayContacts.add(newModel)
            }
        }
        cursor?.close()
        val myAdapter = Adapter(arrayContacts, object : Adapter.Callback {
            override fun onItemClicked(item: ContactModel) {
                //TODO Сюда придёт элемент, по которому кликнули. Можно дальше с ним работать
            }
        })
        recyclerViewContact.adapter = myAdapter
    }
}