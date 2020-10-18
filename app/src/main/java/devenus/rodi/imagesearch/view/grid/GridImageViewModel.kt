package devenus.rodi.imagesearch.view.grid

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import devenus.rodi.imagesearch.base.BaseViewModel
import devenus.rodi.imagesearch.data.SearchImageRepository
import devenus.rodi.imagesearch.network.response.ImageInfo
import devenus.rodi.imagesearch.utils.EventLiveData
import devenus.rodi.imagesearch.utils.MutableEventLiveData
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class GridImageViewModel @ViewModelInject constructor(
    private val searchImageRepository: SearchImageRepository
) : BaseViewModel() {

    private val _noResult = MutableLiveData<Boolean>(true)
    val noResult: LiveData<Boolean> = _noResult

    private val _imgUrlList = MutableLiveData<PagingData<ImageInfo>>()
    val imgUrlList: LiveData<PagingData<ImageInfo>> = _imgUrlList

    private val _hideKeyBoard = MutableEventLiveData<Unit>()
    val hideKeyBoard: EventLiveData<Unit> = _hideKeyBoard

    val keyWord = MutableLiveData<String>()
    var debounceJob: Job? = null

    fun searchImage() {
        debounceJob?.cancel()
        _imgUrlList.value = PagingData.empty()

        if (keyWord.value.isNullOrBlank()) {
            _noResult.value = true
        } else {
            debounceJob = viewModelScope.launch {
                searchImageRepository.getImageInfo(keyWord.value!!)
                    .onStart {
                        delay(1000)
                        _loading.value = true
                    }
                    .collectLatest { pagingData ->
                        _noResult.value = false
                        _imgUrlList.value = pagingData
                        _hideKeyBoard.event = Unit
                        _loading.value = false
                    }
            }
        }
    }

    fun setNoResult() {
        _noResult.value = true
    }
}
