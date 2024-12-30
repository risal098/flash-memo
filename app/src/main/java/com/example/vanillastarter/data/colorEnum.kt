package com.example.vanillastarter.data

import com.example.vanillastarter.R

fun colorDict(colorName:String) :Int{
val colorMap = hashMapOf(
    "purple" to R.color.purple_200,
    "black" to R.color.black,
    "backgorundOne" to R.color.backgorundOne
)


return colorMap[colorName] ?: R.color.white
}
