package com.smarthub.baseapplication.ui.alert.model

internal class Message {
    var message: String? = null
    var sender: User? = null
    var createdAt: Long = 0
}

internal class User {
    var nickname: String? = null
    var profileUrl: String? = null
}
