package com.zamelczyk.fourthwall.ui.picslist

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.zamelczyk.fourthwall.R
import com.zamelczyk.fourthwall.ui.picslist.adapter.PicsListAdapter
import kotlinx.android.synthetic.main.pics_list_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PicsListFragment : Fragment() {

    private val viewModel: PicsListViewModel by viewModel()
    private val adapter by lazy { PicsListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.pics_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = createGridLayoutManager()
        recyclerView.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.pagedList.observe(this, Observer(adapter::submitList))
        viewModel.getState().observe(this, Observer(::onLoadingChanged))
    }

    private fun onLoadingChanged(isLoading: Boolean) {
        if (viewModel.isListEmpty()) {
            adapter.setLoading(isLoading)
        }

        progressBar.visibility = if (viewModel.isListEmpty() && isLoading) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun createGridLayoutManager(): GridLayoutManager {
        val spanCount = getSpanCount()
        return GridLayoutManager(context, spanCount).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (adapter.getItemViewType(position)) {
                        PicsListAdapter.LOADER_VIEW_TYPE -> spanCount
                        PicsListAdapter.PIC_VIEW_TYPE -> 1
                        else -> -1
                    }
                }
            };
        }
    }

    private fun getSpanCount(): Int {
        return if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            2
        } else {
            4
        }
    }

    companion object {
        fun newInstance() = PicsListFragment()
    }

}
