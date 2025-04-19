package com.comp304.santiago_dongheun_comp304sec002_lab04.data

data class Place(
    val name:String,
    val latitude:Double,
    val longitude:Double,
    val address:String,
    val category:Category,
    val image:Int
)

enum class Category{
    Historic,
    Park,
    Museum,
    Shopping
}
