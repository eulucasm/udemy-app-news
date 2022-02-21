package daniellopes.io.newsappstarter.presenter.search

import daniellopes.io.newsappstarter.model.Article
import daniellopes.io.newsappstarter.model.NewsResponse

interface SearchHome {

   interface Presenter{
      fun search(term:String)
      fun showFailure(message:String)
      fun hideProgressBar()
      fun showArticles(articles: List<Article>)

   }
}