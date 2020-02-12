package com.minipoject.appetisercodingchallenge.module.main

import androidx.lifecycle.ViewModel
import com.minipoject.appetisercodingchallenge.util.lazyDeferred

class MainViewModel(private val mainRepo: MainRepo) : ViewModel(){


    val setList by lazyDeferred { mainRepo.getList() }



}