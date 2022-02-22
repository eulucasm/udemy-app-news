package daniellopes.io.newsappstarter.ui.fragments.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import daniellopes.io.newsappstarter.repository.NewsRepository
import daniellopes.io.newsappstarter.ui.fragments.home.HomeViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(
   private val repository: NewsRepository
): ViewModelProvider.NewInstanceFactory() {

   override fun <T : ViewModel> create(modelClass: Class<T>): T {
      return when{
         modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository) as T
         else -> throw IllegalArgumentException("ViewModel não encontrado")
      }
   }
}