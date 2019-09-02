package com.example.customrecycler.ui.splashscreen

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.customrecycler.model.SplashScreenResponse
import com.example.customrecycler.repository.LoginRepository
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SplashScreenViewmodel @Inject constructor(private val repository: LoginRepository) :
    ViewModel() {

    private var mCompositeDisposable = CompositeDisposable()

    private val _isData = MutableLiveData<List<SplashScreenResponse>>()
    val isData: LiveData<List<SplashScreenResponse>>
        get() = _isData

    fun fetchAllData() {
        mCompositeDisposable += repository.fetchALlData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { data -> getDataSucess(data) },
                {error -> getDataFailed (error)})
    }

    private fun getDataFailed(error: Throwable) {
        print("$error")
    }

    private fun getDataSucess(data: List<SplashScreenResponse>?) {
        _isData.value = data
    }

    override fun onCleared() {
        mCompositeDisposable.dispose()
        super.onCleared()
    }
}

private operator fun CompositeDisposable.plusAssign(subscribe: Disposable) {
    add(subscribe)
}

