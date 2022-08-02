package com.yuyu.riverchart.data

data class RiverChart(
    val yearMonth: List<String>,
    val basePE0: MutableList<Float>,
    val basePE1: MutableList<Float>,
    val basePE2: MutableList<Float>,
    val basePE3: MutableList<Float>,
    val basePE4: MutableList<Float>,
    val basePE5: MutableList<Float>,
    val monthPrice: MutableList<Float>
)
