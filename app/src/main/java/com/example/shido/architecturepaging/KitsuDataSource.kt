package com.example.shido.architecturepaging

import android.arch.paging.DataSource
import android.arch.paging.TiledDataSource

/**
 * Created by Shido on 12/11/2017.
 */
abstract class KitsuLimitOffsetNetworkDataSource<T> protected constructor(
        val dataProvider: KitsuRestApi) : TiledDataSource<T>() {

    var queryFilter: String = ""

    override fun countItems(): Int = DataSource.COUNT_UNDEFINED

    protected abstract fun convertToItems(items: KitsuResponse, size: Int): List<T>

    override fun loadRange(startPosition: Int, loadCount: Int): List<T>? {
        val response = checkNotNull(dataProvider.getKitsu(queryFilter, startPosition, loadCount).execute().body())
        return convertToItems(response, response.data.size)
    }
}