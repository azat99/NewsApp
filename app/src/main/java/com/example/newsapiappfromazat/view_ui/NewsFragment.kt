package com.example.newsapiappfromazat.view_ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.newsapiappfromazat.R
import com.example.newsapiappfromazat.viewModel.NewsViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class NewsFragment : Fragment() {

    private val viewModel: NewsViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.news_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.putDataToArticle()
        Log.i("azazazaza",viewModel.getDataFromSource().toString())
    }

}
