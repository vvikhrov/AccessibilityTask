package com.sweethome.shop.catalog

import com.sweethome.item.FullItemViewModel

data class CategoryViewModel(
    val title: String,
    val items: ArrayList<FullItemViewModel>
)

data class CategoryItem(
    val id: String,
    val name: String
)