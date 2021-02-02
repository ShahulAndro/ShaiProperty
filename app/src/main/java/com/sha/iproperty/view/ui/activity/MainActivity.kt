package com.sha.iproperty.view.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sha.iproperty.R
import com.sha.iproperty.view.ui.fragment.HomeFragment
import com.sha.iproperty.view.ui.fragment.MeFragment
import com.sha.iproperty.view.ui.fragment.SavedFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBottomNavigationView()
    }

    private fun initBottomNavigationView() {
        val homeFragment = HomeFragment()
        val savedFragment = SavedFragment()
        val meFragment = MeFragment()

        setCurrentFragment(homeFragment)

        findViewById<BottomNavigationView>(R.id.bottomNavigationView).setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home ->setCurrentFragment(homeFragment)
                R.id.saved ->setCurrentFragment(savedFragment)
                R.id.me ->setCurrentFragment(meFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment : Fragment)=
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment,fragment)
                commit()
            }
}