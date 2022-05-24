package com.splatool.ikastyle.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewViewModel : ViewModel() {
    // アタマギアメインのギアパワー

    // ギアセット名の入力値
    val loadoutName : MutableLiveData<String> = MutableLiveData()
}