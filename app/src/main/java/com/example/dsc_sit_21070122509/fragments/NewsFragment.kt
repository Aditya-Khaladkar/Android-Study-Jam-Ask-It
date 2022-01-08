package com.example.dsc_sit_21070122509.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.dsc_sit_21070122509.R
import com.example.dsc_sit_21070122509.adapters.NewsAdapter
import com.example.dsc_sit_21070122509.model.NewsModel
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_news.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val newsFragView: View = inflater.inflate(R.layout.fragment_news, container, false)

        val news_recyclerView = newsFragView.findViewById<RecyclerView>(R.id.news_recyclerView)
        news_recyclerView.layoutManager = LinearLayoutManager(context)
        news_recyclerView.setHasFixedSize(true)
        news_recyclerView.layoutManager = LinearLayoutManager(context)

        // on start news
        val queue = Volley.newRequestQueue(context)
        val url = "https://inshortsapi.vercel.app/news?category=all"

        // default
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val newsJsonArray = response.getJSONArray("data")
                val newsArray = ArrayList<NewsModel>()
                for (i in 0 until newsJsonArray.length()) {
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val newsModel = NewsModel(
                        newsJsonObject.getString("content"),
                        newsJsonObject.getString("imageUrl")
                    )
                    newsArray.add(newsModel)
                }
                adapter = NewsAdapter(newsArray)
                news_recyclerView.adapter = adapter
            },
            {
                Toast.makeText(context, "getRespond() : false", Toast.LENGTH_LONG).show()
            })

        queue.add(jsonObjectRequest)

        newsFragView.findViewById<Chip>(R.id.chip_business).setOnClickListener {
            getNewsCategory(chip_business.text.toString()) }

        newsFragView.findViewById<Chip>(R.id.chip_all).setOnClickListener {
            getNewsCategory(chip_all.text.toString()) }

        newsFragView.findViewById<Chip>(R.id.chip_sports).setOnClickListener {
            getNewsCategory(chip_sports.text.toString()) }

        newsFragView.findViewById<Chip>(R.id.chip_politics).setOnClickListener {
            getNewsCategory(chip_politics.text.toString()) }

        newsFragView.findViewById<Chip>(R.id.chip_startup).setOnClickListener {
            getNewsCategory(chip_startup.text.toString()) }

        newsFragView.findViewById<Chip>(R.id.chip_technology).setOnClickListener {
            getNewsCategory(chip_technology.text.toString()) }

        return newsFragView
    }

    fun getNewsCategory(newsCat: String) {
        val queue = Volley.newRequestQueue(context)
        val url = "https://inshortsapi.vercel.app/news?category=$newsCat"

        // default
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val newsJsonArray = response.getJSONArray("data")
                val newsArray = ArrayList<NewsModel>()
                for (i in 0 until newsJsonArray.length()) {
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val newsModel = NewsModel(
                        newsJsonObject.getString("content"),
                        newsJsonObject.getString("imageUrl")
                    )
                    newsArray.add(newsModel)
                }
                adapter = NewsAdapter(newsArray)
                news_recyclerView.adapter = adapter
            },
            {
                Toast.makeText(context, "getRespond() : false", Toast.LENGTH_LONG).show()
            })

        queue.add(jsonObjectRequest)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NewsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}