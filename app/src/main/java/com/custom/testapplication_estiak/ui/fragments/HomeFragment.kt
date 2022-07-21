package com.custom.testapplication_estiak.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.custom.testapplication_estiak.R
import com.custom.testapplication_estiak.adapter.RepositoriesAdapter
import com.custom.testapplication_estiak.api.MyApi
import com.custom.testapplication_estiak.api.RetrofitClient
import com.custom.testapplication_estiak.databinding.FragmentHomeBinding
import com.custom.testapplication_estiak.models.Item
import com.custom.testapplication_estiak.models.Repositorie
import com.custom.testapplication_estiak.utils.SessionManager
import com.custom.testapplication_estiak.utils.TestApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers.io

class HomeFragment : Fragment(), RepositoriesAdapter.ItemClicked {

    private val TAG = "HomeFragment"
    private var binding: FragmentHomeBinding? = null
    private var mView: View? = null

    private var mContext: Context? = null

    private var myApi: MyApi? = null
    private var compositeDisposable = CompositeDisposable()

    private var sessionManager: SessionManager? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        if (mView == null) {
            binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
            mView = binding!!.root
        }

        sessionManager = SessionManager(mContext!!)

        val retrofit = RetrofitClient.instance
        myApi = retrofit!!.create(MyApi::class.java)

        binding!!.repositoriesRv.setHasFixedSize(true)
        binding!!.repositoriesRv.layoutManager = LinearLayoutManager(context)

        if (TestApplication.hasNetwork()) {
            fetchData()
        } else {
            Toast.makeText(mContext, "You are offline", Toast.LENGTH_LONG).show()
            //display from cache
            displayData(sessionManager!!.repositories)
        }

        return mView
    }

    fun fetchData() {
        compositeDisposable.add(
            myApi!!.getRepositories("android")
                .subscribeOn(io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { repositories ->
                    run {
                        displayData(repositories)
                        sessionManager!!.clearRepositories()
                        sessionManager!!.setSavedRepositories(repositories)
                    }
                }
        )
    }

    private fun displayData(repositories: Repositorie?) {
        val adapter = repositories!!.items?.let { RepositoriesAdapter(mContext!!, it, this) }
        binding!!.repositoriesRv.adapter = adapter
        binding!!.progressCircular.visibility = View.GONE
    }

    override fun onStop() {
        compositeDisposable.clear()
        super.onStop()
    }

    override fun onItemClicked(position: Int, item: Item) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
            item.name.toString(),
            item.description.toString(),
            item.owner!!.avatarUrl.toString(),
            item.createdAt.toString()
        )
        findNavController().navigate(action)
    }

}