package com.yuyu.riverchart.repository

import com.yuyu.riverchart.data.NStockData
import kotlinx.coroutines.CoroutineScope
import retrofit2.Callback

interface IRepository {

    suspend fun getNStockData(callback: (NStockData) -> Unit)
}