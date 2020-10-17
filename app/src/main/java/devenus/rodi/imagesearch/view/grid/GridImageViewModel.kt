package devenus.rodi.imagesearch.view.grid

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import devenus.rodi.imagesearch.base.BaseViewModel
import devenus.rodi.imagesearch.data.SearchImageRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber

class GridImageViewModel @ViewModelInject constructor(
    private val searchImageRepository: SearchImageRepository
) : BaseViewModel() {

    private val _noResult = MutableLiveData<Boolean>(true)
    val noResult: LiveData<Boolean> = _noResult

    val keyWord = MutableLiveData<String>()
    var debounceJob: Job? = null

    fun searchImage() {

        debounceJob?.cancel()

        if (keyWord.value.isNullOrBlank()) {
            _noResult.value = true
        } else {
            debounceJob = viewModelScope.launch {
                searchImageRepository.getImageInfo(keyWord.value!!)
                    .onStart {
                        delay(1000)
                        _loading.value = true
                    }
                    .catch { throwable ->
                        Timber.e(throwable)
                    }
                    .collect { imageInfoList ->
                        if (imageInfoList.isEmpty()) {
                            _noResult.value = true
                        }else {
                            _noResult.value = false
                        }
                        _loading.value = false
                    }
            }
        }
    }
}
