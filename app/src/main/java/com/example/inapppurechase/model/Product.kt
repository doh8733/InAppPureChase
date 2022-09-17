package com.example.inapppurechase.model

class Product {
    val id : Int
    val name : String
    val image : String
    val price : Float
    val type : String

    constructor(id: Int, name: String, image: String, price: Float, type: String) {
        this.id = id
        this.name = name
        this.image = image
        this.price = price
        this.type = type
    }
}