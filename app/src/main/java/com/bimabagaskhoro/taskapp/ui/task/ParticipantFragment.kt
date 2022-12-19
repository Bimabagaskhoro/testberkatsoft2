package com.bimabagaskhoro.taskapp.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bimabagaskhoro.taskapp.R
import com.bimabagaskhoro.taskapp.data.Resource
import com.bimabagaskhoro.taskapp.databinding.FragmentParticipantBinding
import com.bimabagaskhoro.taskapp.ui.adapter.ParticipantAdapter
import com.bimabagaskhoro.taskapp.vm.DatasViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ParticipantFragment : Fragment() {
    private var _binding: FragmentParticipantBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ParticipantAdapter
    private val viewModel: DatasViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentParticipantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ParticipantAdapter()
        initData()
        binding.apply {
            fabAdd.setOnClickListener {
                initDialog()
            }
        }
    }

    private fun initData() {
        viewModel.getAllParticipant().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.progressbar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    adapter.setData(it.data!!)
                    binding.apply {
                        progressbar.visibility = View.GONE
                        rvPeserta.adapter
                        rvPeserta.layoutManager = GridLayoutManager(context, 1)
                        rvPeserta.setHasFixedSize(true)
                    }
                    adapter.onItemClicked = {
                        val bundle =
                            Bundle().apply { putParcelable(TaskFragment.EXTRA_DATA, it) }
                        findNavController().navigate(
                            R.id.action_navigation_home_to_taskFragment,
                            bundle
                        )
                    }
                }
            }
        }
    }

    private fun initDialog() {
        val showAdd = DialogAddFragment()
        showAdd.show((activity as AppCompatActivity).supportFragmentManager, "Popup")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}