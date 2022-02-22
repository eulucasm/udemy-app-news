package daniellopes.io.newsappstarter.repository

import android.content.Context
import daniellopes.io.newsappstarter.data.local.db.ArticleDatabase
import daniellopes.io.newsappstarter.data.local.model.Article
import daniellopes.io.newsappstarter.data.remote.RetrofitInstance
import daniellopes.io.newsappstarter.presenter.news.NewsHome
import daniellopes.io.newsappstarter.presenter.search.FavoriteHome
import daniellopes.io.newsappstarter.presenter.search.SearchHome
import kotlinx.coroutines.*

class NewsDataSource(context: Context) {

   private var db: ArticleDatabase = ArticleDatabase(context)
   private var newsRepository: NewsRepository = NewsRepository(db)

   fun getBreakingNews(callback: NewsHome.Presenter) {
      GlobalScope.launch(Dispatchers.Main) {
         val response = RetrofitInstance.api.getBreakingNews("br")
         if (response.isSuccessful) {
            response.body()?.let { newsResponse ->
               callback.onSuccess(newsResponse)
            }
            callback.onComplete()
         } else {
            callback.onError(response.message())
            callback.onComplete()
         }
      }
   }

   fun searchNews(term: String, callback: SearchHome.Presenter) {
      CoroutineScope(Dispatchers.Main).launch {
         val response = RetrofitInstance.api.searchNews(term)
         if (response.isSuccessful) {
            response.body()?.let { newsResponse ->
               callback.onSuccess(newsResponse)
            }
            callback.onComplete()
         } else {
            callback.onError(response.message())
            callback.onComplete()
         }

      }
   }

   fun saveArticle(article: Article) {
      CoroutineScope(Dispatchers.Main).launch {
         newsRepository.updateInsert(article)
      }
   }

   fun getAllArticle(callback: FavoriteHome) {
      var allArticles: List<Article>
      CoroutineScope(Dispatchers.IO).launch {
         allArticles = newsRepository.getAll()
      }
   }

   fun deleteArticle(article: Article?) {
      CoroutineScope(Dispatchers.Main).launch {
         article?.let { articleDelete ->
            newsRepository.delete(articleDelete)
         }
      }
   }

}