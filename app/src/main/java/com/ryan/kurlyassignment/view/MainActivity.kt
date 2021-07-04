package com.ryan.kurlyassignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ryan.kurlyassignment.R

class MainActivity : AppCompatActivity() {
    private lateinit var etSearch : EditText
    private lateinit var btnSearch : Button
    private lateinit var rvSearch : RecyclerView
    private lateinit var tvNoResult : TextView
    private lateinit var pbLoading : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etSearch = findViewById(R.id.etSearch)
        btnSearch = findViewById(R.id.btnSearch)
        rvSearch = findViewById(R.id.rvSearch)
        tvNoResult = findViewById(R.id.tvNoResult)
        pbLoading = findViewById(R.id.pbLoading)
    }
}