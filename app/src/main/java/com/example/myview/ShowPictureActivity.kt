package com.example.myview


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView


class ShowPictureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_picture)
        val imageView: ImageView = findViewById(R.id.Show_imageView)
        imageView.setImageResource(intent.getIntExtra("ImageImformation", R.drawable.blank))
    }
        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.toolbar, menu)
            return true
        }
        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.like -> {//点赞

                }
                R.id.collection -> {//收藏

                }
            }
            return true
        }

}

