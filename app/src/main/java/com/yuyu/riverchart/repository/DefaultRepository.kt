package com.yuyu.riverchart.repository

import android.util.Log
import com.yuyu.riverchart.data.NStockData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DefaultRepository(private val dataSource: IDataSource): IRepository {

    override suspend fun getNStockData(call: (NStockData) -> Unit) {
        dataSource.getNStockData().enqueue(object : Callback<NStockData> {

            override fun onResponse(call: Call<NStockData>, response: Response<NStockData>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        call(it)
                    }
                }
            }

            override fun onFailure(call: Call<NStockData>, t: Throwable) {
                Log.v("DefaultRepository", "retrofit onFailure")
            }
        })
    }
}