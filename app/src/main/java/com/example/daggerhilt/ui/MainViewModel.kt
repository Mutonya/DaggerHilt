package com.example.daggerhilt.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daggerhilt.model.BLog
import com.example.daggerhilt.repository.MainRepository
import com.example.daggerhilt.utils.DataState
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val mainRepository: MainRepository,

):ViewModel()

{

    private val _dataState:MutableLiveData<DataState<List<BLog>>> = MutableLiveData()

    val dataState:LiveData<DataState<List<BLog>>> get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.GetBlogEvent ->{
                    mainRepository.getBlogs()
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }.launchIn(viewModelScope)
                }
                is MainStateEvent.None ->{

                }
            }
        }
    }
}

sealed class MainStateEvent{
    object GetBlogEvent:MainStateEvent()
    object None:MainStateEvent()
}