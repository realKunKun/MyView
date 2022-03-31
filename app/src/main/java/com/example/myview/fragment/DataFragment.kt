package com.example.myview.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.myview.R
import com.example.myview.View.BarChartView
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match


/**
 * A simple [Fragment] subclass.
 * Use the [DataFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DataFragment : Fragment() {
    private lateinit var myView: BarChartView
    private lateinit var root:View
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        root=view
        //图标显示器
        var datas = arrayListOf<Int>(40, 76, 90, 50, 187)
        var xList = arrayListOf<String>("1月份", "2月份", "3月份", "4月份", "5月份")
        //根据数据的最大值生成上下对应的Y轴坐标范围
        initBarChart(datas,xList)
        val editText1:EditText=view.findViewById(R.id.data_edit1)
        val editText2:EditText=view.findViewById(R.id.data_edit2)
        val button:Button=view.findViewById(R.id.data_button)
        button.setOnClickListener{
            val user_information=editText1.text.toString().split(" ")
            val user_data=editText2.text.toString().split(" ")
            val data=ArrayList<Int>()
            val x=ArrayList<String>()
            for (i in user_data){
                data.add(i.toInt())
            }
            for (i in user_information){
                x.add(i)
            }
        initBarChart(data,x)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data, container, false)
    }

    fun initBarChart(data:ArrayList<Int>,x:ArrayList<String>){
        var y=ArrayList<Int>()
        var maxYAxis: Int? = Collections.max(data)
        if (maxYAxis!! % 2 == 0) {
            maxYAxis = maxYAxis + 2
        } else {
            maxYAxis = maxYAxis + 1
        }
        var keduSpace = (maxYAxis / data.size) + 1
        for (i in 0..data.size) {
            y.add(0 + keduSpace * i)
        }
        myView=root.findViewById(R.id.TheFirstView)
        myView.updateValueData(data, x, y)
    }

}