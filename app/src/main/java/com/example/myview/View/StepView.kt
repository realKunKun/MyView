package com.example.myview.View

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.myview.R

class StepView(context: Context,attrs: AttributeSet?,defStyleAttr: Int): View(context,attrs,defStyleAttr) {
    private var moutColor= Color.YELLOW
    private var mInnerColor=Color.BLUE
    private var mBorderWidth=0
    private var mCurrentStep:Float = 0.0f
    private var mStepTextColor=Color.BLUE
    private var mStepTextSize=0
    private var MaxStep:Int = 0
    private var mOuterpaint: Paint
    private var mInnerPaint: Paint
    private var mTextPaint:Paint
    constructor(context: Context) : this(context,null)

    constructor(context: Context, attributeSet: AttributeSet?) : this(context,attributeSet,0)

    //constructor(context: Context,attributeSet: AttributeSet,defStyleAttr:Int) : super(context,attributeSet,defStyleAttr)
    init{
        //分析需求
        //自定义属性
        //OnMeasure()
        //获得自定义属性
        val array=context.obtainStyledAttributes(attrs, R.styleable.StepView)
        moutColor=array.getColor(R.styleable.StepView_outer_color,moutColor)
        mInnerColor=array.getColor(R.styleable.StepView_inner_color,mInnerColor)
        mBorderWidth= array.getDimension(R.styleable.StepView_borderWith, 0F).toInt()
        mStepTextColor=array.getColor(R.styleable.StepView_step_text_color,mStepTextColor)
        mStepTextSize=array.getDimensionPixelSize(R.styleable.StepView_step_text_size,mStepTextSize)
        array.recycle()
        //绘制 外圆弧，内圆弧，文字
        mOuterpaint= Paint()
        mOuterpaint.isAntiAlias=true
        mOuterpaint.color=moutColor
        mOuterpaint.strokeWidth= mBorderWidth.toFloat()
        mOuterpaint.style=Paint.Style.STROKE
        mOuterpaint.strokeCap=Paint.Cap.ROUND

        mInnerPaint= Paint()
        mInnerPaint.isAntiAlias=true
        mInnerPaint.color=mInnerColor
        mInnerPaint.strokeWidth= mBorderWidth.toFloat()
        mInnerPaint.style=Paint.Style.STROKE
        mInnerPaint.strokeCap=Paint.Cap.ROUND

        mTextPaint= Paint()
        mInnerPaint.isAntiAlias=true
        mInnerPaint.color=mStepTextColor
        mTextPaint.textSize= mStepTextSize.toFloat()
        //设置动画效果
    }
    @Override
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width=MeasureSpec.getSize(widthMeasureSpec)
        val height=MeasureSpec.getSize(heightMeasureSpec)
        if (width > height)
        setMeasuredDimension(height,height)
        else setMeasuredDimension(width,width)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //内圆弧
        val center=width/2
        val radius=(width- mBorderWidth)/2
        val rectF=RectF((mBorderWidth/2).toFloat(),
            (mBorderWidth/2).toFloat(), (center+radius).toFloat(), (center+radius).toFloat()
        )
        canvas?.drawArc(rectF, 135F, 270F,false,mOuterpaint)
        //外圆弧
        if (MaxStep==0) return
        val proportion=mCurrentStep/MaxStep
        canvas?.drawArc(rectF, 135F, 270F*proportion,false,mInnerPaint)
        //文字
        val mText="${mCurrentStep}"
        val rect= Rect()
        mTextPaint.getTextBounds(mText,0,mText.length,rect)
        val dx=width/2-rect.width()/2
        //val fontMetricsInt=mTextPaint.getFontMetricsInt()
        //var dy=fontMetricsInt.bottom-fontMetricsInt.top
        //var baseLine=height/2 + dy/2
        //选用代码，可以设置边线
        canvas?.drawText(mText, dx.toFloat(), (height/2+rect.height()/2).toFloat(),mTextPaint)

    }
    fun  setmCurrentStep(CurrentStep: Float){
        mCurrentStep=CurrentStep
        invalidate()
    }
    fun setmMaxStep(mMaxstep:Int){
        MaxStep=mMaxstep
    }
}