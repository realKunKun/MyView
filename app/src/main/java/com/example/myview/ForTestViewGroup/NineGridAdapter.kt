package com.example.myview.ForTestViewGroup

import android.content.Context
import android.view.View

abstract class NineGridAdapter(
     protected var context: Context,
     protected var list:MutableList<String>) {
    abstract fun getCount(): Int
         abstract fun getUrl(positopn: Int): String
         abstract fun getItemId(position: Int): Long
         abstract fun getView(i: Int): View
     }
