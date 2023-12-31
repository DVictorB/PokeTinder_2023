package com.victor.balboa.poketinder_2023.ui.view

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import androidx.fragment.app.viewModels
import com.victor.balboa.poketinder_2023.databinding.FragmentInfoBinding
import com.victor.balboa.poketinder_2023.ui.viewmodel.InfoViewModel

class InfoFragment : BaseFragment<FragmentInfoBinding>(FragmentInfoBinding::inflate) {

    private val viewModel: InfoViewModel by viewModels()

    private lateinit var webView: WebView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val url = "https://pokemongolive.com/es/"

        webView = binding.wvInfoFragment
        //Enable use of javascript
        webView.settings.javaScriptEnabled = true

        viewModel.getUrlPokemonLiveData().observe(viewLifecycleOwner) {
            webView.loadUrl(it)
        }
    }
}