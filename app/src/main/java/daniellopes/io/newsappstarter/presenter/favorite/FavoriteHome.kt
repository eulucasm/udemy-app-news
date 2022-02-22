package daniellopes.io.newsappstarter.presenter.favorite

import daniellopes.io.newsappstarter.model.Article

interface FavoriteHome {

   interface Presenter{
      fun onSuccess(articles: List<Article>)
   }


}