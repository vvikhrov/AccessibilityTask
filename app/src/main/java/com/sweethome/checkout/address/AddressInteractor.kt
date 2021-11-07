package com.sweethome.checkout.address

import com.sweethome.R
import com.sweethome.SweetHomeApplication
import java.util.*

class AddressInteractor {
    companion object {
        val HOME = AddressViewModel(
            UUID.randomUUID().toString(),
            R.drawable.address_icon,
            "Ангарская улица, 26к4",
            "Москва, Россия, 125412"
        )

        val WORK = AddressViewModel(
            UUID.randomUUID().toString(),
            R.drawable.address_icon,
            SweetHomeApplication.getInstance().getString(R.string.chistiprudnui),
            "Москва, Россия, 101000"
        )
    }

    val addresses = arrayListOf(HOME, WORK)

    var selectedAddress: String? = HOME.id

    fun updateSelectedAddress(selectedId: String) {
        selectedAddress = selectedId
    }
}