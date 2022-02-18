package daniellopes.io.newsappstarter.presenter.search

import daniellopes.io.newsappstarter.model.Article
import daniellopes.io.newsappstarter.model.data.NewsDataSource
import daniellopes.io.newsappstarter.presenter.ViewHome

class SearchPresenter(
   val view: ViewHome.View,
   private val dataSource: NewsDataSource
   ):SearchHome.Presenter {

   override fun search(term: String) {
      TODO("Not yet implemented")
   }

   override fun showFailure(message: String) {
      TODO("Not yet implemented")
   }

   override fun hideProgressBar() {
      TODO("Not yet implemented")
   }

   override fun showArticles(articles: List<Article>) {
      TODO("Not yet implemented")
   }

}