package com.example.displayfirebase

class User {
    var name:String = ""
    var email:String = ""
    var age:String = ""
    var timeid:String = ""

    constructor(name: String, email: String, age: String, timeid: String) {
        this.name = name
        this.email = email
        this.age = age
        this.timeid = timeid
    }

    constructor()
}