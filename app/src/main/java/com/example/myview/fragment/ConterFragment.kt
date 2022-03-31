package com.example.myview.fragment

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.myview.R
import com.example.myview.View.StepView


class ConterFragment : Fragment() {
    private lateinit var stepView: StepView
    private lateinit var editText: EditText
    private lateinit var animator: ValueAnimator
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //计步器和动画特效（完成度较高）
        stepView= view.findViewById(R.id.step_View)
        stepView.setmMaxStep(10000)
        animator = ObjectAnimator.ofFloat(8000F)
        initAnimator()
        editText=view.findViewById(R.id.counterEdit)
        editText.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {  }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {  }
            override fun afterTextChanged(p0: Editable?) {
            val num:Int=p0.toString().toInt()
            if (num>=0){
                animator=ObjectAnimator.ofFloat(num.toFloat())
                initAnimator()
                }
            }
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_conter, container, false)
    }
    fun initAnimator(){
        animator.duration = 2000
        animator.addUpdateListener { p0 ->
            val currentSteps: Float = p0?.animatedValue as Float
            stepView.setmCurrentStep(currentSteps)
        }
        animator.start()
    }
}