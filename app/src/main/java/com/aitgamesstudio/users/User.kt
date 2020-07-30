package com.aitgamesstudio.users

import kotlinx.serialization.Serializable

@Serializable
class User(
    var name: String,
    var last_name: String,
    var photo: String,
    var age: Int,
    var phone: String,
    var email: String,
    var skype: String,
    var photo_med : String
)