package com.example.calendar.model

import android.graphics.Bitmap

data class ContactModel(

        var username: String = "",
        var phone: String = "",
        var photoUrl: Bitmap? = null
)