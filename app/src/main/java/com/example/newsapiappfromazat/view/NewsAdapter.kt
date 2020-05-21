package com.example.newsapiappfromazat.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapiappfromazat.R
import com.example.newsapiappfromazat.model.entity.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rv_item.view.*

class NewsAdapter(private val listArticle: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val holder = NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        )
        return holder
    }

    override fun getItemCount(): Int {
        return listArticle.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.itemView.textView.text = listArticle[position].title
        Picasso.get().load(listArticle[position].urlToImage).into(holder.itemView.imageView)
        holder.itemView.setOnClickListener {
            val action = NewsFragmentDirections.actionNewsFragmentToDetailsFragment()
            action.articleId = position.toString()
            Navigation.findNavController(it).navigate(action)
        }
    }

}