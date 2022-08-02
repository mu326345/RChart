package com.yuyu.riverchart.repository

import com.yuyu.riverchart.data.NStockData
import com.yuyu.riverchart.network.NStockApi
import retrofit2.Call

class RemoDataSource: IDataSource {

    override suspend fun getNStockData(): Call<NStockData>{
        return NStockApi.service.getNStock()
    }
}