package com.yuyu.riverchart.river

import android.util.Log
import androidx.lifecycle.*
import com.yuyu.riverchart.data.NStockData
import com.yuyu.riverchart.data.RiverChart
import com.yuyu.riverchart.data.RiverDataItem
import com.yuyu.riverchart.data.TimesValue
import com.yuyu.riverchart.repository.IRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.StringBuilder
import java.sql.Time

class ChartViewModel(val repository: IRepository) : ViewModel() {

    private val _nStock = MutableLiveData<NStockData>()
    val nStock: LiveData<NStockData>
        get() = _nStock

    private val _timesValue0 = MutableLiveData<TimesValue>()
    val timesValue0: LiveData<TimesValue>
        get() = _timesValue0

    private val _timesValue1 = MutableLiveData<TimesValue>()
    val timesValue1: LiveData<TimesValue>
        get() = _timesValue1

    private val _timesValue2 = MutableLiveData<TimesValue>()
    val timesValue2: LiveData<TimesValue>
        get() = _timesValue2

    private val _timesValue3 = MutableLiveData<TimesValue>()
    val timesValue3: LiveData<TimesValue>
        get() = _timesValue3

    private val _timesValue4 = MutableLiveData<TimesValue>()
    val timesValue4: LiveData<TimesValue>
        get() = _timesValue4

    private val _timesValue5 = MutableLiveData<TimesValue>()
    val timesValue5: LiveData<TimesValue>
        get() = _timesValue5

    private val _price = MutableLiveData<String>()
    val price: LiveData<String>
        get() = _price

    private val _date = MutableLiveData<String>()
    val date: LiveData<String>
        get() = _date

    private var baseTimesPE = mutableListOf<String>()

    val riverData = Transformations.map(nStock) {

        val yearMonth = mutableListOf<String>()
        val basePE0 = mutableListOf<Float>()
        val basePE1 = mutableListOf<Float>()
        val basePE2 = mutableListOf<Float>()
        val basePE3 = mutableListOf<Float>()
        val basePE4 = mutableListOf<Float>()
        val basePE5 = mutableListOf<Float>()
        val monthPrice = mutableListOf<Float>()

        it.data?.get(0)?.riverData?.forEachIndexed { index, riverDataItem ->
            if (index <= 13) {
                riverDataItem.yearMon?.let { yearMonth.add(0, it) }
                riverDataItem.averagePrice?.let { monthPrice.add(0, it.toFloat()) }
                riverDataItem.basePricePE?.forEachIndexed { index, PE ->
                    when (index) {
                        0 -> basePE0.add(0, PE.toFloat())
                        1 -> basePE1.add(0, PE.toFloat())
                        2 -> basePE2.add(0, PE.toFloat())
                        3 -> basePE3.add(0, PE.toFloat())
                        4 -> basePE4.add(0, PE.toFloat())
                        5 -> basePE5.add(0, PE.toFloat())
                    }
                }
            }
        }

        RiverChart(yearMonth, basePE0, basePE1, basePE2, basePE3, basePE4, basePE5, monthPrice)
    }

    init {
        getNStockData()
    }

    private fun getNStockData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getNStockData {
                it.data?.get(0)?.basePE?.let {
                    it.forEach{ baseTimesPE.add(it) }
                }

                _nStock.value = it
            }
        }
    }

    fun getSelectData(index: Int) {
        val basePE0 = riverData.value?.basePE0?.get(index)
        val basePE1 = riverData.value?.basePE1?.get(index)
        val basePE2 = riverData.value?.basePE2?.get(index)
        val basePE3 = riverData.value?.basePE3?.get(index)
        val basePE4 = riverData.value?.basePE4?.get(index)
        val basePE5 = riverData.value?.basePE5?.get(index)

        baseTimesPE.let {

            _timesValue0.value = TimesValue(
                it[0],
                basePE0.toString()
            )

            _timesValue1.value = TimesValue(
                it[1],
                basePE1.toString()
            )

            _timesValue2.value = TimesValue(
                it[2],
                basePE2.toString()
            )

            _timesValue3.value = TimesValue(
                it[3],
                basePE3.toString()
            )

            _timesValue4.value = TimesValue(
                it[4],
                basePE4.toString()
            )

            _timesValue5.value = TimesValue(
                it[5],
                basePE5.toString()
            )
        }

        riverData.value?.let {
            val date = it.yearMonth.get(index).run {
                StringBuilder(this).insert(4, '/').toString()
            }
            _date.value = date

            val priceF = it.monthPrice.get(index)
            _price.value = "股價$priceF"

        }
    }
}