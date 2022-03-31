package com.example.myview.ForTestViewGroup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myview.R

class NineGridActivity : AppCompatActivity() {
    private lateinit var nineGridlayout: NineGridlayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ninegridtest)
        nineGridlayout=findViewById(R.id.nine)
        val multiList= mutableListOf("https://qlogo4.store.qq.com/qzone/3238127737/3238127737/100?1610538198","https://qlogo4.store.qq.com/qzone/3238127737/3238127737/100?1610538198")
        val nineGridAdapter= NineGridsAdapter(this,multiList)
        nineGridlayout.setAdapter(nineGridAdapter)
    }
}