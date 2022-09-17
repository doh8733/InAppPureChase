package com.example.inapppurechase.model

class TypeProduct {
      val id : Int
      val type : String
      val image : Int

    constructor(id: Int, type: String, image: Int) {
        this.id = id
        this.type = type
        this.image = image
    }
}