package com.example.shido.architecturepaging

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList

/**
 * Created by Shido on 12/11/2017.
 */
class KitsuViewModel : ViewModel() {

    private var allKitsuLiveData: LiveData<PagedList<KitsuItem>>? = null

    val allKitsu: LiveData<PagedList<KitsuItem>>
    get() {
        if(allKitsuLiveData == null){
            allKitsuLiveData = KitsuMediaPagedListProvider.allKitsu().create(0,
                    PagedList.Config.Builder()
                            .setPageSize(PAGED_LIST_PAGE_SIZE)
                            .setInitialLoadSizeHint(PAGED_LIST_PAGE_SIZE)
                            .setEnablePlaceholders(PAGED_LIST_ENABLE_PLACEHOLDERS)
                            .build())
        }
        return allKitsuLiveData ?: throw AssertionError("Check your threads ...")
    }


    fun setQueryFilter(queryFilter: String) {
        KitsuMediaPagedListProvider.setQueryFilter(queryFilter)
        allKitsuLiveData = null // invalidate
    }


    companion object {
        private const val PAGED_LIST_PAGE_SIZE = 20
        private const val PAGED_LIST_ENABLE_PLACEHOLDERS = false
    }


}