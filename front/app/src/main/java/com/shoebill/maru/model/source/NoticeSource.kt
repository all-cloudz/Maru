package com.shoebill.maru.model.source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shoebill.maru.model.data.notice.Notice
import com.shoebill.maru.model.repository.NoticeRepository

class NoticeSource(private val noticeRepository: NoticeRepository) : PagingSource<Int, Notice>() {
    override fun getRefreshKey(state: PagingState<Int, Notice>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Notice> {
        return try {
            Log.d("NOTICE", "load: notice list loaded")
            val page = params.key ?: 0
            val noticeResponse = noticeRepository.getNotices(page)
            Log.d("NOTICE", "${noticeResponse.size}")
            LoadResult.Page(
                data = noticeResponse,
                prevKey = if (page == 0) null else page - 1,
                nextKey = page.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


}