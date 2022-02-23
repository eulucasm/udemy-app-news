package daniellopes.io.newsappstarter.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import daniellopes.io.newsappstarter.data.local.db.ArticleDatabase
import daniellopes.io.newsappstarter.data.remote.RetrofitInstance
import daniellopes.io.newsappstarter.databinding.FragmentHomeBinding
import daniellopes.io.newsappstarter.repository.NewsRepository
import daniellopes.io.newsappstarter.ui.adapter.MainAdapter
import daniellopes.io.newsappstarter.ui.fragments.base.BaseFragment
import daniellopes.io.newsappstarter.util.state.StateResource

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

   private val mainAdaptation by lazy { MainAdapter() }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      setupRecycleView()
      observerResults()
   }

   private fun observerResults() {
      viewModel.getAll.observe(viewLifecycleOwner) { response ->
         when (response) {
            is StateResource.Success -> {
               binding.rvProgressBar.visibility = View.INVISIBLE
               response.data?.let { data ->
                  mainAdaptation.differ.submitList(data.articles.toList())
               }
            }
            is StateResource.Error -> {
               binding.rvProgressBar.visibility = View.INVISIBLE
               Toast.makeText(requireContext(),
                  "Ocorreu um erro: ${response.message.toString()}",
                  Toast.LENGTH_SHORT).show()
            }
            is StateResource.Loading -> {
               binding.rvProgressBar.visibility = View.VISIBLE
            }
         }
      }
   }

   private fun setupRecycleView() = with(binding) {
      rvNews.apply {
         adapter = mainAdaptation
         layoutManager = LinearLayoutManager(context)
         addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
         )
      }
      mainAdaptation.setOnClickListener { article ->
         val action =
            HomeFragmentDirections.actionHomeFragmentToWebViewFragment(article)
         findNavController().navigate(action)
      }
   }

   override fun getViewModel(): Class<HomeViewModel> = HomeViewModel::class.java

   override fun getFragmentRepository(): NewsRepository =
      NewsRepository(RetrofitInstance.api, ArticleDatabase.invoke(requireContext()))

   override fun getFragmentBinding(
      inflater: LayoutInflater,
      container: ViewGroup?,
   ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)
}