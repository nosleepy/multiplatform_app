package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Contact(
    var id: Long,
    var name: String,
    var number: String,
    var email: String,
    var address: String
)

val contactStorage = mutableListOf<Contact>(
    Contact(1, "zhangsan", "111111", "zhangsan@gs.com", "hangzhou"),
    Contact(2, "lisi", "222222", "lisi@gs.com", "changsha"),
    Contact(3, "wangwu", "333333", "wangwu@gs.com", "chongqing"),
)

var flag = 3L