package daniellopes.io.newsappstarter.presenter.search

import daniellopes.io.newsappstarter.model.Article

interface SearchHome {

   interface Presenter{
      fun search(term:String)
      fun showFailure(message:String)
      fun hideProgressBar()
      fun showArticles(articles: List<Article>)
   }
}