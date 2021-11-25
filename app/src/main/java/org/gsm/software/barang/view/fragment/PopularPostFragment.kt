package org.gsm.software.barang.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.gsm.software.barang.R
import org.gsm.software.barang.viewmodel.PopularPostViewModel

class PopularPostFragment : Fragment() {

    companion object {
        fun newInstance() = PopularPostFragment()
    }

    private lateinit var viewModel: PopularPostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.popular_post_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PopularPostViewModel::class.java)
        // TODO: Use the ViewModel
    }

}