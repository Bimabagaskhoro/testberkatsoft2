package com.bimabagaskhoro.taskapp.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bimabagaskhoro.taskapp.R
import com.bimabagaskhoro.taskapp.data.Resource
import com.bimabagaskhoro.taskapp.databinding.FragmentTaskBinding
import com.bimabagaskhoro.taskapp.domain.model.ParticipantModel
import com.bimabagaskhoro.taskapp.ui.adapter.ParticipantAdapter
import com.bimabagaskhoro.taskapp.ui.adapter.ParticipantByIdAdapter
import com.bimabagaskhoro.taskapp.vm.DatasViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskFragment : Fragment() {
    private var _binding: FragmentTaskBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ParticipantByIdAdapter
    private val viewModel: DatasViewModel by viewModels()
    private lateinit var data: ParticipantModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            data = arguments?.getParcelable(EXTRA_DATA)!!
            binding.tvPeserta.text = data.name
        }
        adapter = ParticipantByIdAdapter()
        initData()
    }

    private fun initData() {
        val id = data.id
        viewModel.getByIdParticipant(id).observe(viewLifecycleOwner) {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}