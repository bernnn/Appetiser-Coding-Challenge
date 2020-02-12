package com.minipoject.appetisercodingchallenge.module.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.minipoject.appetisercodingchallenge.data.network.ApiService
import com.minipoject.appetisercodingchallenge.data.network.SafeApiRequest
import com.minipoject.appetisercodingchallenge.data.response.ResultData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepo(private val apiService: ApiService) :  SafeApiRequest(){

    private val resultData = MutableLiveData<List<ResultData>>()

    suspend fun getList() : LiveData<ArrayList<ResultData>>{
        return withContext(Dispatchers.IO){
            fetchList()
            resultData as LiveData<ArrayList<ResultData>>
        }
    }

    private suspend fun fetchList() {
        try {
            val response = apiRequest{ apiService.getList() }
                resultData.postValue(response.results)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

}