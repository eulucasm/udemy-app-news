package daniellopes.io.newsappstarter.ui.fragments.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import androidx.viewbinding.ViewBindings
import daniellopes.io.newsappstarter.repository.NewsRepository

abstract class BaseFragment<VM: ViewModel, VB: ViewBinding>: Fragment() {

   protected lateinit var binding: VB
   protected lateinit var viewModel: VM

   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {

      binding = getFragmentBinding(inflater, container)
      val factory = ViewModelFactory(getFragmentRepository())

      viewModel = ViewModelProvider(viewModelStore, factory).get(getViewModel())

      return binding.root
   }

   abstract fun getViewModel(): Class<VM>

   abstract fun getFragmentRepository(): NewsRepository

   abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): VB
}