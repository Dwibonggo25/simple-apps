package com.example.customrecycler.ui.splashscreen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customrecycler.R
import com.example.customrecycler.databinding.FragmentSplashScreenBinding
import com.example.customrecycler.model.SplashScreenResponse
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class SplashScreenFragment : Fragment() {

    private lateinit var viewModel : SplashScreenViewmodel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: FragmentSplashScreenBinding

    private lateinit var adapter: SplashScreenAdapter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashScreenViewmodel::class.java)
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_splash_screen, container, false)
        binding.apply {
            fragment = this@SplashScreenFragment
            vm = viewModel
            binding.setLifecycleOwner(activity)
            binding.executePendingBindings()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        viewModel.fetchAllData()

        viewModel.isData.observe(this, Observer {
            it?.let {
                refreshData(it)
            }
        })
    }

    private fun initRecyclerView() {
        adapter = SplashScreenAdapter (activity!!, viewModel)
        val layoutmanager = LinearLayoutManager (activity)
        binding.recyclerview.layoutManager = layoutmanager
        binding.recyclerview.adapter = adapter
    }

    fun refreshData(data: List<SplashScreenResponse>) {
        adapter.submitList(data)
    }
}
