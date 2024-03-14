package au.edu.swin.sdmd.l09_inalongfile_2023

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * A ViewModel that captures the button state, as a LiveData.
 * LiveData is UI life-cycle aware.
 */
class ButtonViewModel: ViewModel() {
    val buttonState: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

}