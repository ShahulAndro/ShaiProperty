package com.sha.iproperty.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.tabs.TabLayout
import com.sha.iproperty.R
import com.sha.iproperty.view.adapter.NewsLifeCycleItemsAdapter
import com.sha.iproperty.viewmodel.NewsLifeCycleViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_lifestyle.*
import kotlinx.android.synthetic.main.view_news.*


class HomeFragment : Fragment() {

    private lateinit var buyFragment: BuyFragment
    private lateinit var rentFragment: RentFragment
    private lateinit var tabLayout: TabLayout

    private val viewModel: NewsLifeCycleViewModel by lazy {
        ViewModelProviders.of(this.requireActivity()).get(NewsLifeCycleViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        tabLayout = view.findViewById(R.id.tab_layout)
        setTabLayout()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getNews().observe(this, Observer {
            Picasso.get().load(it.first().thumbnailUrl).placeholder(R.drawable.ic_images_placeholder).into(newsHighlightImage)
            newsHighlightDescription.text = it.first().description
            it.drop(0)

            val adapter = NewsLifeCycleItemsAdapter()
            adapter.setNewsLifeCycleItems(it)
            newsRecyclerView.adapter = adapter
        })

        viewModel.getLifeStyles().observe(this, Observer {
            Picasso.get().load(it.first().thumbnailUrl).placeholder(R.drawable.ic_images_placeholder).into(lifeStyleHighlightImage)
            lifeStyleHighlightDescription.text = it.first().description
            it.drop(0)

            val adapter = NewsLifeCycleItemsAdapter()
            adapter.setNewsLifeCycleItems(it)
            lifeStyleRecyclerView.adapter = adapter
        })
    }

    private fun setTabLayout() {
        buyFragment = BuyFragment()
        rentFragment = RentFragment()

        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.buy)), true)
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.rent)), false)
        setCurrentTabFragment(0)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                setCurrentTabFragment(tabPosition = tab!!.position)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun setCurrentTabFragment(tabPosition: Int) {
        when (tabPosition) {
            0 -> replaceFragment(buyFragment)
            1 -> replaceFragment(rentFragment)
        }
    }

    private fun replaceFragment(fragment: Fragment?) {
        val fm: FragmentManager = requireActivity().supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()
        if (fragment != null) {
            ft.replace(R.id.frame_container, fragment)
        }
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        ft.commit()
    }
}
