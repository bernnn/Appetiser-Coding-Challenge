package com.minipoject.appetisercodingchallenge.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.minipoject.appetisercodingchallenge.module.main.MainRepo
import com.minipoject.appetisercodingchallenge.module.main.MainViewModel


class MainViewModelFactory (private val mainRepo: MainRepo) : ViewModelProvider.NewInstanceFactory(){


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  MainViewModel(mainRepo) as T
    }
}