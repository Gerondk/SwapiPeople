package com.gkp.people.data.paging

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gkp.people.data.remote.PeopleApi
import com.gkp.people.data.toPeople
import com.gkp.people.domain.model.People
import java.io.IOException
import java.util.concurrent.CancellationException
import javax.inject.Inject


class PeoplePagingSource @Inject constructor(
    private val peopleApi: PeopleApi,
) : PagingSource<Int, People>() {

    override fun getRefreshKey(state: PagingState<Int, People>): Int? {
        return state.anchorPosition
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, People> {
        val currentPage = params.key ?: 1
        return try {
            val networkResponse = peopleApi.getPeoples(currentPage)
            val peopleList = networkResponse.results.map { it.toPeople() }
            LoadResult.Page(
                data = peopleList,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (peopleList.isEmpty()) null else currentPage + 1
            )

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch ( e: Exception) {
            if (e is CancellationException)
                throw  e
            LoadResult.Error(e)
        }
    }
}