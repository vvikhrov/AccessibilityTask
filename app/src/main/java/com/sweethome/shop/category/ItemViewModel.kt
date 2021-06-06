package com.sweethome.shop.category

data class ItemViewModel(
    val id: String,
    val image: String,
    val collection: String,
    val manufacturer: String,
    val price: String,
    val discount: Boolean = false
)