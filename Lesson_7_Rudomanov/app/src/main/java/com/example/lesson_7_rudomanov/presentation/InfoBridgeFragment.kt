package com.example.lesson_7_rudomanov.presentation

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.lesson_7_rudomanov.R
import com.example.lesson_7_rudomanov.data.ApiClient
import com.example.lesson_7_rudomanov.data.model.Bridge
import com.example.lesson_7_rudomanov.data.model.Divorces
import com.example.lesson_7_rudomanov.databinding.FragmentInfoBridgeBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val KEY_IMAGE_OPEN = "key_image_open"
private const val KEY_IMAGE_CLOSE = "key_image_close"
private const val KEY_NAME = "key_name"
private const val KEY_DIVORCES = "key_divorces"
private const val KEY_DESCRIPTION = "key_description"
private const val KEY_CHECK = "key_check"

class InfoBridgeFragment : Fragment(R.layout.fragment_info_bridge) {
    private val binding by viewBinding(FragmentInfoBridgeBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentConteinerView, ListBridgeFragment.newInstance())
                .commit()
        }

        arguments?.let {
            val check: Int = it.getInt(KEY_CHECK)
            if (check == 0) {
                binding.imageViewBridge.setImageResource(R.drawable.ic_brige_soon)
                loadImage(it.getString(KEY_IMAGE_OPEN))
            } else {
                if ((check == 1)) {
                    binding.imageViewBridge.setImageResource(R.drawable.ic_brige_normal)
                    loadImage(it.getString(KEY_IMAGE_OPEN))
                } else {
                    binding.imageViewBridge.setImageResource(R.drawable.ic_brige_late)
                    loadImage(it.getString(KEY_IMAGE_CLOSE))

                }
            }
            binding.textViewTitle.text = it.getString(KEY_NAME)
            binding.textViewDescription.text = it.getString(KEY_DESCRIPTION)
            val listTime: ArrayList<Divorces>? = it.getParcelableArrayList(KEY_DIVORCES)
            listTime?.forEach { position -> binding.textViewTime.append(position.start + " - " + position.end + "    ") }

        }
    }

    private fun loadImage(imageURL: String?) {
        lifecycleScope.launch {
            binding.progressBar.isVisible = true
            delay(1000)
            Glide
                .with(binding.root)
                .load(imageURL)
                .into(binding.imageViewTitle)
            binding.progressBar.isVisible = false
        }
    }

    companion object {
        fun newInstance(bridge: Bridge, check: Int): InfoBridgeFragment {
            return InfoBridgeFragment().apply {
                arguments = bundleOf(
                    KEY_IMAGE_OPEN to bridge.photoOpenUrl,
                    KEY_IMAGE_CLOSE to bridge.photoCloseUrl,
                    KEY_NAME to bridge.name,
                    KEY_DIVORCES to bridge.divorces,
                    KEY_DESCRIPTION to bridge.description,
                    KEY_CHECK to check,
                )
            }
        }
    }
}