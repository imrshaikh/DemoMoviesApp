package com.example.moviesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.FragmentItemListBinding
import com.example.moviesapp.viewmodel.ItemListViewModel
import kotlinx.coroutines.launch

@ExperimentalPagingApi
class ItemListFragment : Fragment() {

    private var _binding: FragmentItemListBinding? = null

    lateinit var viewModel: ItemListViewModel
    lateinit var adapter: MovieListAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application)
        ).get(ItemListViewModel::class.java)

        adapter = MovieListAdapter()

        val recyclerView: RecyclerView = binding.itemList
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = adapter

        viewModel.livePagingData.observe(this, {
            viewModel.viewModelScope.launch {
                adapter.submitData(it)
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}