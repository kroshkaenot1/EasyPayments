package com.template.easypayments.presentation.payments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.template.easypayments.R
import com.template.easypayments.databinding.FragmentPaymentsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentsFragment : Fragment(R.layout.fragment_payments) {
    private var _binding: FragmentPaymentsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PaymentsViewModel by viewModels()
    private val paymentsAdapter = PaymentsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.paymentsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = paymentsAdapter
        }

        viewModel.listOfPayments.observe(viewLifecycleOwner, Observer { payments ->
            paymentsAdapter.submitList(payments)
        })

        viewModel.getAllPayments()

        binding.logoutButton.setOnClickListener {
            viewModel.logout()
            val navController = findNavController()
            navController.navigate(R.id.action_paymentsFragment_to_loginFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
