package com.yuyu.riverchart.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yuyu.riverchart.repository.IRepository
import com.yuyu.riverchart.repository.RemoDataSource
import com.yuyu.riverchart.river.ChartViewModel

class ViewModelFactory(val repository: IRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ChartViewModel::class.java)) {
            return ChartViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown class")
    }
}