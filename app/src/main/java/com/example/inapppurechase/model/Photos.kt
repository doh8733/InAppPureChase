package com.example.inapppurechase.model

import androidx.annotation.DrawableRes

class Photos {
    @DrawableRes val reSourceId : Int
    constructor(reSourceId: Int) {
        this.reSourceId = reSourceId
    }
}