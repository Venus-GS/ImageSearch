package devenus.rodi.imagesearch.view.grid

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import devenus.rodi.imagesearch.base.BaseViewModel
import devenus.rodi.imagesearch.data.SearchImageRepository

class GridImageViewModel @ViewModelInject constructor(
    private val searchImageRepository: SearchImageRepository
) : BaseViewModel() {

    private val _noResult = MutableLiveData<Boolean>(true)
    val noResult : LiveData<Boolean> = _noResult

    val keyWord = MutableLiveData<String>()


}
