package com.example.boundservice.Model

import java.io.Serializable

class Song(var title: String, var single: String, var image: Int, var resource: Int) :
    Serializable {
    override fun toString(): String {
        return "Title: $title, Single: $single"
    }
}