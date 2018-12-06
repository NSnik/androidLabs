package com.example.nsnik.app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Button
import android.widget.EditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


class DomainSearchActivity : AppCompatActivity() {
    lateinit var domainsList: RecyclerView
    lateinit var serchText: EditText
    lateinit var searchBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.search_domain_activity)
        domainsList = findViewById(R.id.domainsList)
        serchText = findViewById(R.id.searchDomainText)
        searchBtn = findViewById(R.id.searchDomainBtn)

        domainsList.layoutManager = LinearLayoutManager(this)
        val recyclerAdapter = DomainRecyclerAdapter(layoutInflater, listOf())
        domainsList.adapter = recyclerAdapter

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.domainsdb.info")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)

        searchBtn.setOnClickListener {
            val query = serchText.text.toString()
            service.search(query).enqueue(object : Callback<SearchResultModel> {
                override fun onFailure(call: Call<SearchResultModel>, t: Throwable) {
                    Log.d("L4", "onFailure HTTP")
                }

                override fun onResponse(call: Call<SearchResultModel>, response: Response<SearchResultModel>) {
                    if (response.isSuccessful && response.code() === 200) {
                        val searchResultModel = response.body()
                        val items = searchResultModel?.domains ?: listOf()
                        Log.d("L4", "size =  ${items.size}")
                        recyclerAdapter.items = items
                        recyclerAdapter.notifyDataSetChanged()
                    }
                }
            })
        }

    }
}