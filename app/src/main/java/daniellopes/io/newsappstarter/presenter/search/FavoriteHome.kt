package daniellopes.io.newsappstarter.presenter.search

import daniellopes.io.newsappstarter.data.local.model.Article

interface FavoriteHome {

   fun Presenter(articles: List<Article>)
}