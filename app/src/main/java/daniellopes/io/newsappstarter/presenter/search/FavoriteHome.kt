package daniellopes.io.newsappstarter.presenter.search

import daniellopes.io.newsappstarter.model.Article

interface FavoriteHome {

   fun showArticles(articles: List<Article>)
}