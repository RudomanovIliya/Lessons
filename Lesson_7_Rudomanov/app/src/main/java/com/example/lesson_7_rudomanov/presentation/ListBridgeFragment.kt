package com.example.lesson_7_rudomanov.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_7_rudomanov.R
import com.example.lesson_7_rudomanov.data.ApiClient
import com.example.lesson_7_rudomanov.databinding.FragmentListBridgeBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ListBridgeFragment : Fragment(R.layout.fragment_list_bridge) {
    private val binding by viewBinding(FragmentListBridgeBinding::bind)

    private val bridgesAdapter = BridgesAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewBridges.adapter = bridgesAdapter.apply {
            bridgeListener = BridgeListener { bridge,check ->
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentConteinerView,InfoBridgeFragment.newInstance(bridge,check))
                    .commit()
            }
        }
        loadBridges()
    }
    private fun loadBridges() {
        lifecycleScope.launch {
            binding.progressBar.isVisible = true
            delay(1000)
            val bridges = ApiClient.apiService.getBridges()
            bridgesAdapter.setList(bridges)
            binding.progressBar.isVisible = false
        }
    }

    companion object {
        fun newInstance(): ListBridgeFragment {
            return ListBridgeFragment()
        }
    }
}