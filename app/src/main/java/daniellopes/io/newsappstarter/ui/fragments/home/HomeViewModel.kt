package daniellopes.io.newsappstarter.ui.fragments.home

import androidx.lifecycle.ViewModel
import daniellopes.io.newsappstarter.repository.NewsRepository

class HomeViewModel constructor(
   private val repository: NewsRepository
):ViewModel() {

}