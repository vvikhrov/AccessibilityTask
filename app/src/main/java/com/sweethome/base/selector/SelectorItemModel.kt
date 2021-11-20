package com.sweethome.base.selector

data class SelectorItemModel(
    val id: String,
    val imageId: Int,
    val title: String,
    val subtitle: String = "",
    var checked: Boolean = false
)