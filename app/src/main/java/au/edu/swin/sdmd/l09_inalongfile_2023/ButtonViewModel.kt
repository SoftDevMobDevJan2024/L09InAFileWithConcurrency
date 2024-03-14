package au.edu.swin.sdmd.l09_inalongfile_2023

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ButtonViewModel: ViewModel() {
    val buttonState: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

}