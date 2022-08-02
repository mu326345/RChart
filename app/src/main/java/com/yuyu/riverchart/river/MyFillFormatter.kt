package com.yuyu.riverchart.river

import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet


class MyFillFormatter (private val boundaryDataSet: ILineDataSet? = null) :
    IFillFormatter {
    override fun getFillLinePosition(dataSet: ILineDataSet, dataProvider: LineDataProvider): Float {
        return 0F
    }

    //Define a new method which is used in the LineChartRenderer
    fun getFillLineBoundary(): List<Entry>? {
        return if (boundaryDataSet != null) {
            (boundaryDataSet as LineDataSet).values
        } else null
    }
}