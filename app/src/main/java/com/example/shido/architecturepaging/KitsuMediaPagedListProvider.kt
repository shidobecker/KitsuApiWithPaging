package com.example.shido.architecturepaging

import android.arch.paging.LivePagedListProvider

/**
 * Created by Shido on 12/11/2017.
 */
object KitsuMediaPagedListProvider {
    private val dataSource = object: KitsuLimitOffsetNetworkDataSource<KitsuItem>(KitsuRestApi) {

        override fun convertToItems(items: KitsuResponse, size: Int): List<KitsuItem> {
            return List(size, { index ->
                items.data.elementAtOrElse(index, { KitsuItem(0, null, null) })
            })
        }
    }

    fun allKitsu(): LivePagedListProvider<Int, KitsuItem> {
        return object : LivePagedListProvider<Int, KitsuItem>() {
            override fun createDataSource(): KitsuLimitOffsetNetworkDataSource<KitsuItem> = dataSource
        }
    }

    fun setQueryFilter(queryFilter: String) {
        dataSource.queryFilter = queryFilter
    }
}