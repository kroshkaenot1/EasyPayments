package com.template.easypayments.presentation.login


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.template.easypayments.R
import com.template.easypayments.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (viewModel.isTokenExist()){
            val navController = findNavController()
            navController.navigate(R.id.action_loginFragment_to_paymentsFragment)
        }
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLogin.setOnClickListener {
            val login = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()
            viewModel.login(login, password)
        }

        viewModel.user.observe(viewLifecycleOwner) { user ->
            val navController = findNavController()
            navController.navigate(R.id.action_loginFragment_to_paymentsFragment)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            if (errorMessage.isNotEmpty()) {
                binding.textViewError.text = errorMessage
                binding.textViewError.visibility = View.VISIBLE
            } else {
                binding.textViewError.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}