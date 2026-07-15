package com.example.chemistryapp

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewPager = ViewPager2(this).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
        setContentView(viewPager)
        val pages = (1..193).map { String.format("page-%03d.webp", it) }
        viewPager.adapter = BookAdapter(pages)
    }
}

class BookAdapter(private val pages: List<String>) : RecyclerView.Adapter<BookAdapter.PageViewHolder>() {
    class PageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view as ImageView
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val imageView = ImageView(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            scaleType = ImageView.ScaleType.FIT_CENTER
        }
        return PageViewHolder(imageView)
    }
    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        val assetPath = "chemistry_pages/${pages[position]}"
        Glide.with(holder.imageView.context)
            .load("file:///android_asset/$assetPath")
            .into(holder.imageView)
    }
    override fun getItemCount(): Int = pages.size
}
