package com.example.administrator.bottomNavigationView

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.text.SpannableString
import android.text.style.ImageSpan


class MyFragmentPagerAdapter(private val fragments: Array<Fragment>, fragmentManager: FragmentManager, private val context: Context) : FragmentPagerAdapter(fragmentManager) {
    private val imageIds: IntArray = intArrayOf(R.drawable.alarm, R.drawable.collect, R.drawable.gallery, R.drawable.mine)
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
//        纯字tab
//        return context.resources.getStringArray(R.array.tab)[position]
//        纯图tab
        val drawable = ContextCompat.getDrawable(context, imageIds[position])
        drawable?.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicWidth)
        val imageSpan = ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM)
        val spannableString = SpannableString(" ")
        spannableString.setSpan(imageSpan, 0, 1, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        return spannableString
    }
}