package com.example.calendar.model

data class ContactModel(
        val id: String = "",
        var username: String = "",
        var bio: String = "",
        var fullname: String = "",
        var state: String = "",
        var phone: String = "",
        var photoUrl: String = "empty",

        var text: String = "",
        var type: String = "",
        var from: String = "",
        var timeStamp: Any = "",
        var fileUrl: String = "empty",

        var lastMessage:String = "",
        var choice:Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        return (other as ContactModel).id == id
    }
}