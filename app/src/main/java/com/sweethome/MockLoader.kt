package com.sweethome

import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sweethome.item.FullItemViewModel
import com.sweethome.shop.catalog.CategoryItem
import com.sweethome.shop.catalog.CategoryViewModel
import java.io.InputStreamReader
import java.io.Reader

class MockLoader(val gson: Gson, val assetManager: AssetManager) {
    fun loadCatalog(): ArrayList<FullItemViewModel> {
        val inputStream = assetManager.open("products.json")
        val reader: Reader = InputStreamReader(inputStream)

        return gson.fromJson(
            reader,
            object: TypeToken<ArrayList<FullItemViewModel>>() {}.type
        )
    }

    fun loadCategories(): List<CategoryItem> {
        val inputStream = assetManager.open("categories.json")
        val reader: Reader = InputStreamReader(inputStream)

        return gson.fromJson(
            reader,
            object: TypeToken<List<CategoryItem>>() {}.type
        )
    }


}
