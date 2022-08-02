package com.yuyu.riverchart.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

/**
 *本淨比PBR, 本益比PE, 同業Peers, 中位數Mid, 評估evaluate, 季season
 */

@Parcelize
data class NStockData(
    val data: List<NStockDataItem>?
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class NStockDataItem(
    @Json(name = "同業本淨比中位數") val peersMidPBR: String?,
    @Json(name = "同業本益比中位數") val peersMidPE: String?,
    @Json(name = "平均本淨比") val averagePBR: String?,
    @Json(name = "平均本益比") val averagePE: String?,
    @Json(name = "本淨比基準") val basePBR: List<String>?,
    @Json(name = "本淨比股價評估") val evaluatePricePBR: String?,
    @Json(name = "本益成長比") val growthPE: String?,
    @Json(name = "本益比基準") val basePE: List<String>?,
    @Json(name = "本益比股價評估") val evaluatePricePE: String?,
    @Json(name = "河流圖資料") val riverData: List<RiverDataItem>?,
    @Json(name = "目前本淨比") val nowPBR: String?,
    @Json(name = "目前本益比") val nowPE: String?,
    @Json(name = "股票代號") val stockNum: String?,
    @Json(name = "股票名稱") val stockName: String?
) : Parcelable

@Parcelize
data class RiverDataItem(
    @Json(name = "平均本淨比")val averagePBR: String?,
    @Json(name = "平均本益比")val averagePE: String?,
    @Json(name = "年月")val yearMon: String?,
    @Json(name = "月平均收盤價")val averagePrice: String?,
    @Json(name = "月近一季本淨比")val monSeasonPBR: String?,
    @Json(name = "月近四季本益比")val fourSeasonPE: String?,
    @Json(name = "本淨比股價基準")val basePricePBR: List<String>?,
    @Json(name = "本益比股價基準")val basePricePE: List<String>?,
    @Json(name = "近3年年複合成長")val threeYearGrowth: String?,
    @Json(name = "近一季BPS")val nearSeasonBPS: String?,
    @Json(name = "近四季EPS")val nearFourSeasonEPS: String?
) : Parcelable