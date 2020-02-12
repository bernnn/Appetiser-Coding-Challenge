package com.minipoject.appetisercodingchallenge.module.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.minipoject.appetisercodingchallenge.R
import com.minipoject.appetisercodingchallenge.databinding.ActivityMainBinding
import com.minipoject.appetisercodingchallenge.util.AppConfigPreference
import com.minipoject.appetisercodingchallenge.util.Coroutines
import com.minipoject.appetisercodingchallenge.util.MainViewModelFactory
import com.minipoject.appetisercodingchallenge.util.hasNetwork
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory : MainViewModelFactory by instance()

    private lateinit var adapter: MainAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )

        val rvSearch = binding.rvSearch
        rvSearch.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        adapter = MainAdapter()
        rvSearch.adapter = adapter

        if(!hasNetwork()!!){
            tvSavedDate.visibility = View.VISIBLE
            tvSavedDate.text = AppConfigPreference.sharedPreference.getString("pref_date"," ")
        }else{
            tvSavedDate.visibility = View.GONE
        }

        Coroutines.main {
            viewModel.setList.await().observe(this, Observer {
                if(it.size > 0){
                    progressBar.visibility = View.GONE
                    adapter.updateList(it)
                }else{
                    progressBar.visibility = View.VISIBLE
                }


            })
        }



    }
}
