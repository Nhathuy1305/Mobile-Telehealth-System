package com.main.mobiletelehealthsystem.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData


open class BaseViewModel(application: Application):
    AndroidViewModel(application) {
        val progressLiveData: MutableLiveData<Boolean> by lazy { MutableLiveData() }
    }