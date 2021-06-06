package com.sweethome

import androidx.fragment.app.FragmentManager
import com.sweethome.base.BaseFragment
import com.sweethome.base.MockPresenter
import com.sweethome.base.MvpView
import com.sweethome.cart.CartFragment
import com.sweethome.cart.CartPresenter
import com.sweethome.checkout.CheckoutFragment
import com.sweethome.checkout.CheckoutPresenter
import com.sweethome.item.FullItemViewModel
import com.sweethome.item.ItemInfoFragment
import com.sweethome.item.ItemInfoPresenter
import com.sweethome.shop.CatalogFragment
import com.sweethome.shop.CatalogPresenter

class RootRouterImpl(
    private val fragmentManager: FragmentManager,
    private val cartRepository: CartRepository,
    private val mockLoader: MockLoader
) : RootRouter {
    override fun openDetails(model: FullItemViewModel) {
        val fragment = ItemInfoFragment.newInstance(model)
        openFragment(fragment)
        fragment.setMvpPresenter(ItemInfoPresenter(this, mockLoader, cartRepository))
    }

    override fun openShoppingCart() {
        val fragment = CartFragment.newInstance()
        openFragment(fragment)
        fragment.setMvpPresenter(CartPresenter(this, mockLoader, cartRepository))
    }

    override fun openCheckout(fullPrice: String) {
        val fragment = CheckoutFragment.newInstance(fullPrice)
        openFragment(fragment)
        fragment.setMvpPresenter(CheckoutPresenter(this, mockLoader, cartRepository))
    }

    override fun showCatalog() {
        val fragment = CatalogFragment.newInstance()
        openFragment(fragment)
        fragment.setMvpPresenter(CatalogPresenter(this, mockLoader, cartRepository))
    }

    private fun openFragment(fragment: BaseFragment<out MockPresenter<out MvpView>, out MvpView>) {
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment, fragment::class.simpleName)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed(): Boolean {
        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack()
            return true
        }
        return false
    }

}