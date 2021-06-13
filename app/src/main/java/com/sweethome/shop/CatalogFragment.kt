package com.sweethome.shop

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sweethome.R
import com.sweethome.base.BaseFragment
import com.sweethome.base.MvpView
import com.sweethome.base.custom_view.CartButton
import com.sweethome.item.FullItemViewModel
import com.sweethome.shop.catalog.CatalogAdapter
import com.sweethome.shop.catalog.CategoryViewModel
import com.sweethome.shop.category.OnItemClickListener

class CatalogFragment : BaseFragment<CatalogPresenter, CatalogMvpView>() {

    private lateinit var catalog: RecyclerView
    private lateinit var cartButton: CartButton
    private val adapter: CatalogAdapter = CatalogAdapter()
    private val onItemClickListener: OnItemClickListener = object : OnItemClickListener {
        override fun onItemClick(model: FullItemViewModel) {
            presenter.onItemClick(model)
        }
    }

    init {
        mvpView = object : CatalogMvpView {
            override fun updateList(models: ArrayList<CategoryViewModel>) {
                adapter.setCatalogItems(models)
            }

            override fun updateItemsCount(itemsCount: Int) {
               cartButton.setItemCount(itemsCount)
            }
        }
    }

    override fun onViewInflated(view: View) {
        super.onViewInflated(view)
        catalog = view.findViewById(R.id.catalog)
        catalog.layoutManager = LinearLayoutManager(context)
        catalog.adapter = adapter
        adapter.setOnItemClickListener(onItemClickListener)
        cartButton = view.findViewById(R.id.cart_button)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cartButton.setOnClickListener {
            presenter.onCartClick()
        }
        presenter.attach(mvpView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detach()
    }

    override fun layoutId(): Int {
        return R.layout.shop_catalog_layout
    }

    override fun title(): String {
        return resources.getString(R.string.shop_catalog_title)
    }

    override fun showCart(): Boolean {
        return true
    }

    companion object {
        fun newInstance(): CatalogFragment {
            return CatalogFragment()
        }
    }
}

interface CatalogMvpView: MvpView {
    fun updateList(models: ArrayList<CategoryViewModel>)
    fun updateItemsCount(itemsCount: Int)
}
