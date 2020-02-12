package com.minipoject.appetisercodingchallenge.module.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import coil.api.load
import com.minipoject.appetisercodingchallenge.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        tvTrackName.text = intent.getStringExtra("trackname")
        tvGenre.text = intent.getStringExtra("genre")
        tvPrice.text = intent.getStringExtra("price")
        tvDescription.text = intent.getStringExtra("description")
        ivPic.load(intent.getStringExtra("imageUrl"))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}