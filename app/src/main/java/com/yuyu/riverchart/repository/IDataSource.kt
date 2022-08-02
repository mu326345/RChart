package com.yuyu.riverchart.repository

import com.yuyu.riverchart.data.NStockData
import retrofit2.Call

interface IDataSource {

    suspend fun getNStockData(): Call<NStockData>
}