package com.example.newsapiappfromazat.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.newsapiappfromazat.R
import com.example.newsapiappfromazat.model.network.InternetCheck
import com.example.newsapiappfromazat.viewModel.NewsViewModel
import kotlinx.android.synthetic.main.news_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class NewsFragment : Fragment() {

    private var isLoading = false
    private var listSize = 10
    private val viewModel: NewsViewModel by viewModel()
    private var layoutManager:LinearLayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        layoutManager = LinearLayoutManager(context)
        recycler_view.layoutManager = layoutManager
        recycler_view.setHasFixedSize(true)
        recycler_view.isNestedScrollingEnabled = false
        swipeRefreshLayout.isEnabled = false

        InternetCheck{internet ->
            if(!internet){
                Toast.makeText(context,"Нет подключения к интернету!",Toast.LENGTH_SHORT).show()
                if(swipeRefreshLayout.isRefreshing){
                    swipeRefreshLayout.isRefreshing = false
                }
            }else{
                loadMore()
                swipeRefreshLayout.isEnabled = true
                swipeRefreshLayout.setOnRefreshListener {
                    swipeRefreshLayout.isRefreshing = true
                    listSize = 10
                    loadMore()
                }
                recycler_view.setOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        if (!isLoading) {

                            val visibleItemCount = layoutManager!!.childCount
                            val pastVisibleItem = layoutManager!!.findFirstCompletelyVisibleItemPosition()
                            val total = recycler_view.adapter!!.itemCount
                            
                            if ((visibleItemCount+pastVisibleItem)>=total) {
                                if (listSize<71){
                                    listSize += 10
                                    loadMore()
                                }
                            }
                        }
                        super.onScrolled(recyclerView, dx, dy)
                    }
                })
            }
        }

        viewModel.getDataFromArticle().observe(viewLifecycleOwner, Observer {
            recycler_view.adapter = NewsAdapter(it)
            recycler_view.adapter?.notifyDataSetChanged()
            swipeRefreshLayout.isRefreshing = false
        })

    }

    private fun loadMore() {
        isLoading = true

        recycler_view.adapter?.notifyItemInserted(listSize - 1)
        var scrollPosition = listSize
        recycler_view.adapter?.notifyItemRemoved(scrollPosition)

        viewModel.putDataToArticle(listSize)

        isLoading = false

    }

}
