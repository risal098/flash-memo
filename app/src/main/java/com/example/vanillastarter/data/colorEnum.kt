package com.example.vanillastarter.data

import com.example.vanillastarter.R

fun colorDict(colorName:String) :Int{
val colorMap = hashMapOf(
    "purple" to R.color.purple_200,
    "black" to R.color.black,
    "backgorundOne" to R.color.backgorundOne,
    "purple_500" to R.color.purple_500,
    "purple_700" to R.color.purple_700,
    "teal_200" to R.color.teal_200,
    "teal_700" to R.color.teal_700,
    "white" to R.color.white,
    "greyTheme" to R.color.greyTheme,
    "darkBlue" to R.color.darkBlue,
    "teal" to R.color.teal, // Note:  This "teal" overrides the "teal" key if you had it mapping to R.color.teal_200 or _700 previously.  Consider renaming to avoid confusion.
    "lightPink" to R.color.lightPink,
    "pink" to R.color.pink,
    "green" to R.color.green,
    "lightTeal" to R.color.lightTeal, // Similar to "teal" above, consider renaming.
    "purple" to R.color.purple,  // This overrides the initial "purple" mapping! Be careful with duplicates. Consider different keys like "purplePalette"
    "blue" to R.color.blue,
    "orange" to R.color.orange,
    "mint" to R.color.mint,
    "lightGrey" to R.color.lightGrey,
)


return colorMap[colorName] ?: R.color.white
}
