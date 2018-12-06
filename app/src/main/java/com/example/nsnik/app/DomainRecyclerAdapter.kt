package com.example.nsnik.app

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class DomainRecyclerAdapter(
    private val layoutInflater: LayoutInflater,
    var items: List<DomainModel>
) : RecyclerView.Adapter<DomainRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, index: Int): ViewHolder {
        val view = layoutInflater.inflate(R.layout.domain_recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(viewHolder: ViewHolder, index: Int) {
        val domainModel = items[index]
        viewHolder.textView.text = domainModel.domainName
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var textView: TextView

        init {
            textView = itemView.findViewById(R.id.textView)
        }

    }

}