package com.example.lesson_11_rudomanov

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import java.util.Calendar
import java.util.GregorianCalendar
import kotlin.random.Random

class VisitingView : View {
    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init(context, attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {
        init(context, attributeSet)
    }

    private var colorRow: Int = 0
    private var colorDate: Int = 0
    private var colorNumber: Int = 0

    private fun init(context: Context, attributeSet: AttributeSet? = null) {
        val attrs = context.theme.obtainStyledAttributes(
            attributeSet,
            R.styleable.VisitingView,
            0,
            0
        )
        try {
            val colorRow = attrs.getColor(
                R.styleable.VisitingView_rowColor,
                ContextCompat.getColor(context, R.color.yellow)
            )
            val colorDate = attrs.getColor(
                R.styleable.VisitingView_dateColor,
                ContextCompat.getColor(context, R.color.gray)
            )
            val colorNumber = attrs.getColor(
                R.styleable.VisitingView_numberColor,
                ContextCompat.getColor(context, R.color.yellow)
            )
            this.colorRow = colorRow
            this.colorDate = colorDate
            this.colorNumber = colorNumber
        } finally {
            attrs.recycle()
        }
    }

    private val rowStrokeWidth by lazy { resources.getDimension(R.dimen.row_stroke_width) }
    private val textSizeRes by lazy { resources.getDimension(R.dimen.text_size) }
    private val textPadding by lazy { resources.getDimension(R.dimen.text_padding) }

    private var widthBetweenRow = 0f
    private var calendar = Calendar.getInstance()
    private var month = calendar[Calendar.MONTH] + 1
    private var day = calendar[Calendar.DAY_OF_MONTH]
    private var year = calendar[Calendar.YEAR]
    private var countRows: Int = 1
    private var maxHeight = height.toFloat() - textPadding * 2.5f
    private var numbers = mutableListOf<Int>()
    private var numbersPercent = mutableListOf<Float>()
    private var date = mutableListOf<String>()
    private var coefficientWave = mutableListOf<Float>()
    private val paintLine by lazy {
        Paint().apply {
            color = colorRow
            strokeWidth = rowStrokeWidth
            strokeCap = Paint.Cap.ROUND
        }
    }
    private val paintText by lazy {
        Paint().apply {
            color = colorDate
            textSize = textSizeRes
        }
    }
    private val paintNumber by lazy {
        Paint().apply {
            color = colorNumber
            textSize = textSizeRes
        }
    }

    private var heightLine: Float = 0f
    private var startLineY = height.toFloat() - textPadding

    init {
        setOnClickListener {
            startAnimation()
        }
        setBackgroundColor(resources.getColor(R.color.dark_gray))
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        widthBetweenRow = (width / (countRows + 1)).toFloat()
        maxHeight = height.toFloat() - textPadding * 2.5f
        startLineY = height.toFloat() - textPadding
        repeat(countRows) {
            numbersPercent.add(
                numbers[it].toFloat() / numbers.max()
                    .toFloat()
            )
            coefficientWave.add((100 / (it.toFloat() + 1f)))
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val startTextY = height.toFloat()
        var startLineX = widthBetweenRow
        repeat(countRows) {
            val numberWidth = textSizeRes * numbers[it].toString().length
            val textWidth = textSizeRes * date[it].length
            canvas.drawText(
                numbers[it].toString(),
                startLineX - (numberWidth / 4),
                maxOf(
                    startLineY - ((numbersPercent[it] * heightLine) * coefficientWave[it]) - (textPadding / 2),
                    startLineY - ((numbersPercent[it] * maxHeight)) - (textPadding / 2)
                ),
                paintNumber
            )
            canvas.drawLine(
                startLineX,
                startLineY,
                startLineX,
                maxOf(
                    startLineY - ((numbersPercent[it] * heightLine) * coefficientWave[it]),
                    startLineY - ((numbersPercent[it] * maxHeight))
                ),
                paintLine
            )
            canvas.drawText(
                date[it],
                startLineX - (textWidth / 4),
                startTextY - (textPadding / 4),
                paintText
            )
            startLineX += widthBetweenRow
        }
    }

    fun startAnimation() {
        val valueAnimator = ValueAnimator.ofFloat(0f, maxHeight).apply {
            duration = 5000
            addUpdateListener {
                val animatedHeight = it.animatedValue as Float
                heightLine = animatedHeight
                invalidate()
            }
        }
        valueAnimator?.start()
    }

    fun setData(data: Int) {
        if (data < 1 || data > 9) {
            throw Exception("Value is out of bounds(1..9)")
        }
        countRows = data
        widthBetweenRow = (width / (countRows + 1)).toFloat()
        maxHeight = height.toFloat() - textPadding * 2.5f
        startLineY = height.toFloat() - textPadding
        var count = 0
        var day = day
        var month = month
        var calendar: Calendar = GregorianCalendar(year, month - 1, day)
        repeat(countRows) {
            numbers.add(Random.nextInt(100))
            if (day - count < 1) {
                if (month - 1 > 0) {
                    calendar = GregorianCalendar(year, month - 1, 1)
                    day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
                    month -= 1
                    count = 0
                } else {
                    calendar = GregorianCalendar(year - 1, 11, 1)
                    day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
                    month = 12
                    count = 0
                }
            }
            date.add("${day - count}.${month}")
            count++
        }
        date.reverse()
        repeat(countRows) {
            numbersPercent.add(
                numbers[it].toFloat() / numbers.max()
                    .toFloat()
            )
            coefficientWave.add((100 / (it.toFloat() + 1f)))
        }
    }
}