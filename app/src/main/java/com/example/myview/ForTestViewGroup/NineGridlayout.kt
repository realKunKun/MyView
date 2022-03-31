package com.example.myview.ForTestViewGroup

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView

class NineGridlayout(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : ViewGroup(context, attrs, defStyleAttr) {
    private val TAG = "NineGridlayout"
    private var adapter: NineGridAdapter? = null
    private var onItemClickListerner: AdapterView.OnItemClickListener? = null
    //默认图片间隔
    private val ITEM_GAP = 3
    private val DEFAULT_WIDTH = 140
    //图片之间的间隔
    var gap: Int = 0
    private var columns: Int = 0
    private var rows: Int = 0
    private var totalWidth: Int = 0
    internal var singleWidth = 0
    internal var singleHeight = 0
    private var defaultWidth: Int = 0
    private var defaultHeight: Int = 0
    private var oldCount: Int = 0
    private var isFirstLayout = true
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    init {
        gap = dip2px(context, ITEM_GAP.toFloat())
        defaultHeight = dip2px(context, DEFAULT_WIDTH.toFloat())
        defaultWidth = defaultHeight
    }
    fun setAdapter(adapter: NineGridAdapter?) { this.adapter = adapter
        if (adapter == null) { return } //初始化布局形状
        generateChildrenLayout(adapter.getCount())
        removeAllViews()
        for (i in 0..adapter.getCount() - 1) {
            val itemView = adapter.getView(i)
            addView(itemView, generateDefaultLayoutParams())
        }
        oldCount = adapter.getCount() }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int)
    {
        Log.e(TAG, "onMeasure")
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val sizeWidth = MeasureSpec.getSize(widthMeasureSpec)
        val sizeHeight = MeasureSpec.getSize(heightMeasureSpec)
        totalWidth = sizeWidth - paddingLeft - paddingRight
        if (adapter != null && adapter!!.getCount() > 0)
        { val measureHeight: Int //计算单个图片的大小
            singleWidth = (totalWidth - gap * (columns - 1)) / columns
            singleHeight = singleWidth
            measureChildren(MeasureSpec.makeMeasureSpec(singleWidth, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(singleHeight, MeasureSpec.EXACTLY))
            measureHeight = singleHeight * rows + gap * (rows - 1) + paddingTop + paddingBottom

            setMeasuredDimension(sizeWidth, measureHeight) }

    }
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int)
    { if (!isFirstLayout) layoutChildrenView()
        isFirstLayout = false }
    private fun layoutChildrenView() { Log.e(TAG, "layoutChildrenView")
        if (adapter == null || adapter!!.getCount() == 0) { return }
        val childrenCount = adapter!!.getCount()
        for (i in 0..childrenCount - 1)
        { val position = findPosition(i)
            val left = (singleWidth + gap) * position[1] + paddingLeft
            val top = (singleHeight + gap) * position[0] + paddingTop
            val right = left + singleWidth
            val bottom = top + singleHeight
            val childrenView = getChildAt(i) as ImageView
            if (childrenCount == 1) {
            //只有一张图片
            childrenView.scaleType = ImageView.ScaleType.FIT_CENTER }
        else { childrenView.scaleType = ImageView.ScaleType.CENTER_CROP }
            val itemPosition = i
            childrenView.setOnClickListener{
                if (onItemClickListerner != null) {
                   // onItemClickListerner!!.onClick(itemPosition)
                } }
            childrenView.layout(left, top, right, bottom) } }

    private fun findPosition(childNum: Int): IntArray {
        val position = IntArray(2)
        for (i in 0..rows - 1)
        { for (j in 0..columns - 1)
        { if (i * columns + j == childNum) { position[0] = i//行
            position[1] = j//列
            break } } }
        return position }
    /**
     * 根据图片个数确定行列数量
     * 对应关系如下
     * num row column
     * 1 1 1
     * 2 1 2
     * 3-4 2 2
     * 5-6 2 3
     * 7-9 3 3
     * @param length */
    private fun generateChildrenLayout(length: Int)
    { if (length == 1) {
        rows = 1
        columns = 1 }
    else if (length <= 4) {
        rows = 2
        columns = 2 }
    else if (length <= 6) {
        rows = 2
        columns = 3 }
    else {
        rows = 3
        columns = 3 }
        singleWidth = (totalWidth - gap * (columns - 1)) / columns
        singleHeight = singleWidth }
    fun setOnItemClickListener(onItemClickListerner: AdapterView.OnItemClickListener)
    { this.onItemClickListerner = onItemClickListerner } companion object {
        /** * dp to px */
        fun dip2px(context: Context, dpValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (dpValue * scale + 0.5f).toInt() } }
}