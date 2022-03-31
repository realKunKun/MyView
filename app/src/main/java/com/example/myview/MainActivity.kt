package com.example.myview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager

import com.example.myview.fragment.ConterFragment
import com.example.myview.fragment.DataFragment
import com.example.myview.fragment.PictureFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    private lateinit var navigationItemView: BottomNavigationView
    private var mFragments=ArrayList<Fragment>()
    private lateinit var viewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationItemView=findViewById(R.id.buttonNav)
        navigationItemView.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.item_data -> {
                        viewPager.currentItem = 0
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.item_count -> {
                        viewPager.currentItem = 1
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.item_picture -> {
                        viewPager.currentItem = 2
                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            })
        viewPager=findViewById(R.id.viewpager)
        mFragments.add(DataFragment())
        mFragments.add(ConterFragment())
        mFragments.add(PictureFragment())
        val adapter = object :
            FragmentStatePagerAdapter(
                supportFragmentManager,
                BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
            ) {
            override fun getItem(position: Int): Fragment {
                return mFragments[position]
            }

            override fun getCount(): Int {
                return mFragments.size
            }
        }
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        navigationItemView.selectedItemId=R.id.item_data
                    }
                    1 -> {
                        navigationItemView.selectedItemId = R.id.item_count
                    }
                    2 -> {
                        navigationItemView.selectedItemId = R.id.item_picture
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

    }
}