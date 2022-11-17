package com.amar.sitambol.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.amar.sitambol.R
import com.amar.sitambol.data.database.PrefManager
import com.amar.sitambol.ui.all.MapFragment
import com.amar.sitambol.ui.home.HomeFragment
import com.amar.sitambol.ui.login.LoginActivity
import com.amar.sitambol.ui.profile.ProfileFragment
import com.amar.sitambol.util.MapsHelper
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var prefManager: PrefManager
    private lateinit var presenter: MainPresenter

    private val fragmentHome: Fragment = HomeFragment()
    private val fragmentMap: Fragment = MapFragment()
    private val fragmentProfile: Fragment = ProfileFragment()
    private val fm: FragmentManager = supportFragmentManager
    private var active: Fragment = fragmentHome

    private lateinit var menu: Menu
    private lateinit var menuItem: MenuItem
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefManager = PrefManager(this)
        presenter = MainPresenter(this)

        MapsHelper.permissionMap(this, this)
    }

    override fun onStart() {
        super.onStart()
        if (!prefManager.prefLogin) {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    @SuppressLint("SetTextI18n")
    override fun initActivity() {
        fm.beginTransaction().add(R.id.nav_host_fragment, fragmentHome).show(fragmentHome).commit()
        fm.beginTransaction().add(R.id.nav_host_fragment, fragmentMap)
            .hide(fragmentMap).commit()
        fm.beginTransaction().add(R.id.nav_host_fragment, fragmentProfile).hide(fragmentProfile)
            .commit()

        bottomNavigationView = findViewById(R.id.nav_view)
        menu = bottomNavigationView.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true
    }

    override fun initListener() {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    callFragment(0, fragmentHome)
                }
                R.id.navigation_notifications -> {
                    callFragment(1, fragmentMap)
                }
                R.id.navigation_profile -> {
                    callFragment(2, fragmentProfile)
                }
            }
            false
        }
    }

    override fun callFragment(int: Int, fragment: Fragment) {
        menuItem = menu.getItem(int)
        menuItem.isChecked = true
        fm.beginTransaction().hide(active).show(fragment).commit()
        active = fragment
    }
}