package com.yuyu.riverchart

import android.app.Application
import com.yuyu.riverchart.repository.DefaultRepository
import com.yuyu.riverchart.repository.RemoDataSource

class Application: Application() {

//    val repository = DefaultRepository(RemoDataSource())
    lateinit var repository: DefaultRepository

    override fun onCreate() {
        super.onCreate()
        repository = DefaultRepository(RemoDataSource())
    }
}