package com.zamelczyk.fourthwall.ui.picslist

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zamelczyk.fourthwall.R

class PicsListFragment : Fragment() {

    companion object {
        fun newInstance() = PicsListFragment()
    }

    private lateinit var viewModel: PicsListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.pics_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PicsListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
