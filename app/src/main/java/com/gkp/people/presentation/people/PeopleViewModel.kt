package com.gkp.people.presentation.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.gkp.people.domain.GetPeopleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    getPeopleUseCase: GetPeopleUseCase,
) : ViewModel() {
    val peoplePagingData = getPeopleUseCase().cachedIn(viewModelScope)
}