package com.example.newsapiappfromazat.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.example.newsapiappfromazat.R
import com.example.newsapiappfromazat.viewModel.DetailsViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.details_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by viewModel()
    private var position:String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            position =DetailsFragmentArgs.fromBundle(it).articleId
        }

        viewModel.getDetailsInfo().observe(viewLifecycleOwner, Observer {
            tv_Title.text = it[position!!.toInt()].title
            tv_author.text = it[position!!.toInt()].author
            Picasso.get().load(it[position!!.toInt()].urlToImage).into(iv_image)
            tv_content.text = it[position!!.toInt()].content
        })

    }

}
