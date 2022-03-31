package com.example.myview.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myview.R
import com.example.myview.View.MyRecyclerView
import com.google.android.material.chip.ChipGroup


// TODO: Rename parameter arguments, choose names that match

/**
 * A simple [Fragment] subclass.
 * Use the [PictureFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PictureFragment : Fragment() {
    private lateinit var chipGroup: ChipGroup
    private lateinit var root:View
    private var array=ArrayList<Int>()
    private var choose=1
    private var size=1
    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        root=view
        chipGroup=view.findViewById(R.id.chip_group)
        initpicture()
        var myRecyclerView=MyRecyclerView(root,R.id.RecyclerView,array)
        chipGroup.setOnCheckedChangeListener { _, selectId->
            run {
                when (selectId) {
                    R.id.chip1 -> choose = 1
                    R.id.chip2 -> choose = 2
                    R.id.chip3 -> choose = 3
                    R.id.chip4 -> choose = 4
                    else -> choose=5
                }
                initpicture()
                myRecyclerView.refresh(array)
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root=inflater.inflate(R.layout.fragment_picture, container, false)
        return root
    }
    fun initpicture(){
        when(choose){
            1 -> array= arrayListOf(R.drawable.fzu)
            2 -> array= arrayListOf(R.drawable.lazy,R.drawable.playball)
            3 -> array= arrayListOf(R.drawable.fzu,R.drawable.watermelon,R.drawable.fzu)
            4 -> array= arrayListOf(R.drawable.playball,R.drawable.lazy,R.drawable.watermelon,R.drawable.fzu)
            5 -> array= arrayListOf(R.drawable.lazy,R.drawable.lazy,R.drawable.lazy,R.drawable.playball,R.drawable.playball,R.drawable.playball,R.drawable.watermelon,R.drawable.watermelon,R.drawable.watermelon)
        }
    }
    override fun onResume() {
        super.onResume()
    }
    override fun onPause() {
        super.onPause()
    }

}