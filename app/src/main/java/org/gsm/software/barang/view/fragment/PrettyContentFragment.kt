package org.gsm.software.barang.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.gsm.software.barang.R
import org.gsm.software.barang.viewmodel.PrettyContentViewModel

class PrettyContentFragment : Fragment() {

    companion object {
        fun newInstance() = PrettyContentFragment()
    }

    private lateinit var viewModel: PrettyContentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pretty_content_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PrettyContentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}