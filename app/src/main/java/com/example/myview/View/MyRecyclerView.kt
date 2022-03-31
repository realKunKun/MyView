package com.example.myview.View

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout.VERTICAL
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myview.R
import com.example.myview.ShowPictureActivity

class MyRecyclerView(view: View,resouce:Int,myArray:ArrayList<Int>) {
    private  var mRecyclerview: RecyclerView
    private  var myselfAdapter: MyselfAdapter
    private  var root: View
    private  var array:ArrayList<Int>
    private var size:Int = 0
    init {
       array=myArray
       root=view
       mRecyclerview=view.findViewById(resouce)
        manager()
        myselfAdapter=MyselfAdapter(root.context,array)
        mRecyclerview.adapter=myselfAdapter
    }
    @SuppressLint("NotifyDataSetChanged")
    fun refresh(myArray:ArrayList<Int>){
        array=myArray
        manager()
        myselfAdapter.notifyDataSetChanged()
    }
    @SuppressLint("WrongConstant")
    fun manager(){
        size = when(array.size){
            1 -> 1
            2 -> 2
            4 -> 2
            else -> 3
        }
        mRecyclerview.layoutManager=GridLayoutManager(root.context,size, VERTICAL,false)
    }
    inner class MyselfAdapter(val context: Context,val imageList: ArrayList<Int>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        private val ONE_ITEM = 1
        private val TWO_ITEM= 2
        private val THIRD_ITEM = 3

        inner class ViewHolder1(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val image1: ImageView = itemView.findViewById(R.id.imageViewOne)
        }
        inner class ViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val image2: ImageView = itemView.findViewById(R.id.imageViewTwo)
        }
        inner class ViewHolder3(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val image3: ImageView = itemView.findViewById(R.id.imageViewThird)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val inflater= LayoutInflater.from(parent.context)
            val holder: RecyclerView.ViewHolder
            when(viewType){
                ONE_ITEM -> holder=ViewHolder1(inflater.inflate(R.layout.first_item,parent,false))

                TWO_ITEM -> holder=ViewHolder2(inflater.inflate(R.layout.second_item,parent,false))
                else ->holder=ViewHolder3(inflater.inflate(R.layout.third_item,parent,false))
            }
            return holder
        }
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (holder is ViewHolder1) {
                holder.image1.setImageResource(imageList[position])
                holder.image1.setOnClickListener {
                    val intent = Intent(root.context, ShowPictureActivity::class.java)
                    intent.putExtra("ImageImformation", imageList[position])
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    root.context.startActivity(intent)
                }
            }

            if (holder is ViewHolder2) {
                holder.image2.setImageResource(imageList[position])
                holder.image2.setOnClickListener {
                    val intent = Intent(root.context, ShowPictureActivity::class.java)
                    intent.putExtra("ImageImformation", imageList[position])
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    root.context.startActivity(intent)
                }
            }

            if (holder is ViewHolder3)  {
                holder.image3.setImageResource(imageList[position])
                holder.image3.setOnClickListener {
                    val intent = Intent(root.context, ShowPictureActivity::class.java)
                    intent.putExtra("ImageImformation", imageList[position])
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    root.context.startActivity(intent)
                }
            }
        }
        override fun getItemCount()=imageList.size

        override fun getItemViewType(position: Int): Int =
            when(imageList.size){
                1 -> ONE_ITEM

                2 -> TWO_ITEM

                4 -> TWO_ITEM

                else -> THIRD_ITEM
            }
    }

}