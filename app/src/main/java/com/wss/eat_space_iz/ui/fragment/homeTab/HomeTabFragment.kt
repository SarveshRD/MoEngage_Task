package com.wss.eat_space_iz.ui.fragment.homeTab

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.wss.eat_space_iz.R
import com.wss.eat_space_iz.data.networkModels.moEngage.Article
import com.wss.eat_space_iz.data.networkModels.moEngage.MoEngageResponse
import com.wss.eat_space_iz.data.networkModels.tummoc.Item
import com.wss.eat_space_iz.data.networkModels.tummoc.ShoppingResponse
import com.wss.eat_space_iz.databinding.FragmentHomeTabBinding
import com.wss.eat_space_iz.ui.adapter.profileTab.CategoriesAdapter
import com.wss.eat_space_iz.ui.adapter.profileTab.HistoryAdapter
import com.wss.eat_space_iz.ui.adapter.profileTab.PopularNearYouAdapter
import com.wss.eat_space_iz.ui.common.ApiRenderState
import com.wss.eat_space_iz.ui.fragment.base.BaseFrag
import com.wss.eat_space_iz.ui.viewModel.homeTab.HomeTabViewModel
import com.wss.eat_space_iz.utils.Drawables
import com.wss.eat_space_iz.utils.Layouts
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Locale


@AndroidEntryPoint
class HomeTabFragment :
    BaseFrag<FragmentHomeTabBinding, HomeTabViewModel>(Layouts.fragment_home_tab) {

    private lateinit var mPopularNearYouAdapter: PopularNearYouAdapter
    private lateinit var mPopularNearYouDataList: List<Article>

    private var mHistoryAdapter: HistoryAdapter? = null
    private var mHistoryDataList= ArrayList<Any>()

    private var mCategoriesAdapter: CategoriesAdapter? = null
    private var mCategoriesDataList= ArrayList<Item>()

    override val hasProgress: Boolean = false
    override fun getViewBinding() = FragmentHomeTabBinding.inflate(layoutInflater)
    override val vm: HomeTabViewModel by viewModels()

    override fun init() {
        setUpListener()
        moEngage()

    }

    private fun moEngage() {
     

        CoroutineScope(Dispatchers.IO).launch {
            var httpURLConnection: HttpURLConnection? = null
            try {

                val url = URL("https://candidate-test-data-moengage.s3.amazonaws.com/Android/news-api-feed/staticResponse.json")

                httpURLConnection = url.openConnection() as HttpURLConnection

                val code = httpURLConnection.responseCode

                if (code != 200) {
                    throw IOException("The error from the server is $code")
                }

                val bufferedReader = BufferedReader(
                    InputStreamReader(httpURLConnection.inputStream)
                )

                val jsonStringHolder: StringBuilder = StringBuilder()

                while (true) {
                    val readLine = bufferedReader.readLine() ?: break
                    jsonStringHolder.append(readLine)
                }

                val moEngageResponse = Gson().fromJson(jsonStringHolder.toString(), MoEngageResponse::class.java)


                withContext(Dispatchers.Main) {

                    setupArticlesRecyclerView(moEngageResponse.articles)

                
                }
            } catch (ioexception : IOException){
                Log.e(this.javaClass.name, ioexception.message.toString())
            } finally {
                httpURLConnection?.disconnect()
            }
        }


    }

    private fun setUpListener() {
        with(binding) {
            homeCategories.downArrow.setOnClickListener(this@HomeTabFragment)
        }
    }

    override fun renderState(apiRenderState: ApiRenderState) {
        when (apiRenderState) {
            is ApiRenderState.Loading -> {
                showProgress()
            }
            is ApiRenderState.ApiSuccess<*> -> {
                when (apiRenderState.result) {
                    is MoEngageResponse -> {
                        val model = apiRenderState.result
                      //  model.status.let {
                           // when (it) {
                              //  getString(Strings.api_success_status) -> {



                          //  setupArticlesRecyclerView(model.articles)

                             // }
                           //     else -> {}
                         //   }
                     //   }
                    }
                }
            }
            is ApiRenderState.ValidationError -> {
                apiRenderState.message?.let {
                    errorToast(getString(it))
                }
            }
            is ApiRenderState.ApiError<*> -> {
                errorToast(apiRenderState.error.toString())
            }
            else -> {}
        }
    }

    override fun onClick(v: View) {
        super.onClick(v)
        with(binding)
        {
            when (v) {

                homeCategories.downArrow -> {
                    // Sort the mPopularNearYouDataList by publishedAt date
                    mPopularNearYouDataList = mPopularNearYouDataList.sortedBy { article ->
                        // Convert the publishedAt string to a comparable date format
                        article.publishedAt?.let {
                            // You may need to adjust the date format pattern based on your requirements
                            val formattedDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
                                .parse(it)
                            formattedDate?.time
                        } ?: 0L // Default value if publishedAt is null
                    }

                    // Notify the adapter about the data change after sorting
                    mPopularNearYouAdapter.notifyDataSetChanged()
                }


                else -> {}
            }
        }
    }

    private fun setupArticlesRecyclerView(data: List<Article>) {

        mPopularNearYouDataList = data

        Log.d("PopularNearYouAdapter", "Data size: ${mPopularNearYouDataList.size}")

        if (mPopularNearYouDataList.isNotEmpty()) {
            mPopularNearYouAdapter = PopularNearYouAdapter(data)
            binding.homeCategories.rvPopularNearYou.adapter = mPopularNearYouAdapter
        }
        

    }

}
