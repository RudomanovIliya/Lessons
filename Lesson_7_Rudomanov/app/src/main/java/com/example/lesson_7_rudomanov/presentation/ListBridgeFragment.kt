package com.example.lesson_7_rudomanov.presentation

import android.os.Bundle
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.view.WindowManager
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_7_rudomanov.R
import com.example.lesson_7_rudomanov.data.ApiClient
import com.example.lesson_7_rudomanov.data.model.Bridge
import com.example.lesson_7_rudomanov.databinding.FragmentListBridgeBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ListBridgeFragment : Fragment(R.layout.fragment_list_bridge) {

    private val binding by viewBinding(FragmentListBridgeBinding::bind)
    private val bridgesAdapter = BridgesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewBridges.adapter = bridgesAdapter.apply {
            bridgeListener = BridgeListener { bridge, stateBridge ->
                parentFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .add(
                        R.id.fragmentConteinerView,
                        InfoBridgeFragment.newInstance(bridge, stateBridge)
                    )
                    .commit()
            }
        }
        loadBridges()
    }

    private fun loadBridges() {
        lifecycleScope.launch {
            try {
                binding.progressBar.isVisible = true
                val bridges = ApiClient.apiService.getBridges()
                if (bridges != null)
                    bridgesAdapter.setList(bridges)
                else {
                    binding.progressBar.isVisible = false
                    binding.recyclerViewBridges.visibility = View.GONE
                    binding.textViewError.visibility = View.VISIBLE
                    binding.textViewError.text = getString(R.string.data_not_download)
                }
                binding.progressBar.isVisible = false
            } catch (e: Exception) {
                binding.progressBar.isVisible = false
                binding.recyclerViewBridges.visibility = View.GONE
                binding.textViewError.visibility = View.VISIBLE
                binding.textViewError.text = e.message
            }
        }
    }

    companion object {
        fun newInstance(): ListBridgeFragment {
            return ListBridgeFragment()
        }
    }
}