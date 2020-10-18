package devenus.rodi.imagesearch.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    protected val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
}
