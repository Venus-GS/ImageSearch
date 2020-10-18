package devenus.rodi.imagesearch.view.detail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import devenus.rodi.imagesearch.base.BaseViewModel

class ImageDetailViewModel @ViewModelInject constructor(
    @Assisted savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val _imageUrl = savedStateHandle.getLiveData<String>("imageUrl")
    val imageUrl: LiveData<String> = _imageUrl

    private val _displaySiteName = savedStateHandle.getLiveData<String>("displaySiteName")
    val displaySiteName: LiveData<String> = _displaySiteName

    private val _dateTime = savedStateHandle.getLiveData<String>("dateTime")
    val dateTime: LiveData<String> = _dateTime
}
