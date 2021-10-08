package com.mazaira.searchtv.usecases.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mazaira.searchtv.model.domain.LikeEntity
import com.mazaira.searchtv.provider.room.LikesProvider
import com.mazaira.searchtv.util.exceptions.LikeExceptions
import com.mazaira.searchtv.util.exceptions.TypeError
import com.orhanobut.logger.Logger
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class BaseViewModel:ViewModel() {

    //Published
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    var typeError: MutableLiveData<TypeError> = MutableLiveData()
        private set


    fun updateLike(isFavorite: Boolean, likeEntity: LikeEntity) {
        executeSilentAction {
            if (isFavorite)
                LikesProvider.addLike(likeEntity)
            else
                LikesProvider.deleteLike(likeEntity)
        }
    }

    fun executeAction(block: suspend() -> Unit ): Job {

        return viewModelScope.launch {
            loading.postValue(true)
            try {
                block()
            }catch (e: LikeExceptions){
                typeError.postValue(e.typeError)
            }finally {
                loading.postValue(false)
            }
        }
    }

    private fun executeSilentAction(block: suspend() -> Unit ): Job {
        return viewModelScope.launch {
            try {
                block()
            }catch (e: LikeExceptions){
                Logger.d(e.typeError)
            }
        }
    }
}