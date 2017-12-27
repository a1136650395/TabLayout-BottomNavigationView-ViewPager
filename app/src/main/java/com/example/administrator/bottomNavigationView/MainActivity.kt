package com.example.administrator.bottomNavigationView

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var menuItem: MenuItem? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewPagerAndBottomNavigationView()
    }
    /**
     * 初始化viewpager和BottomNavigationView,并联动，关闭MenuItem滑动动画
    * */
    @SuppressLint("RestrictedApi")
    private fun initViewPagerAndBottomNavigationView() {
        //viewpager适配器
        val fragments = arrayOf(BlankFragment(), BlankFragment2(), BlankFragment3(), BlankFragment4())
        viewPager.adapter = MyFragmentPagerAdapter(fragments, supportFragmentManager)
        //MenuItem只能有一个是选中状态
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                menuItem?.isChecked = false
                menuItem = bottomNavigationView.menu.getItem(position)
                menuItem!!.isChecked = true
            }

        })
        //MenuItem关闭滑动动画
        val bottomNavigationMenuView = bottomNavigationView.getChildAt(0) as BottomNavigationMenuView
        val shiftModeField = bottomNavigationMenuView.javaClass.getDeclaredField("mShiftingMode")
        shiftModeField.isAccessible = true
        shiftModeField.setBoolean(bottomNavigationMenuView, false)
        shiftModeField.isAccessible = false
        (0..3).map { bottomNavigationMenuView.getChildAt(it) as BottomNavigationItemView }
                .forEach {
                    it.setShiftingMode(false)
                    it.setChecked(it.itemData.isChecked)
                }
        //底部按钮切换viewpager
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_alarm -> viewPager.currentItem = 0
                R.id.menu_favorite -> viewPager.currentItem = 1
                R.id.menu_gallery -> viewPager.currentItem = 2
                R.id.menu_mine -> viewPager.currentItem = 3
            }
            false
        }
    }
}
