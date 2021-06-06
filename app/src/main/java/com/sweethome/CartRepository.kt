package com.sweethome

class CartRepository {
    val cartItems: HashMap<String, Int> = hashMapOf()

    fun addItem(id: String) {
        cartItems[id] = (cartItems[id] ?: 0).plus(1)
    }

    fun removeItem(id: String) {
        cartItems.remove(id)
    }

    fun itemsCount(): Int {
        return cartItems.values.sum()
    }
}