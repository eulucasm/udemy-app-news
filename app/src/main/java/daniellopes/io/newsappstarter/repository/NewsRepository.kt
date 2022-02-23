package daniellopes.io.newsappstarter.repository

import daniellopes.io.newsappstarter.data.local.model.Article
import daniellopes.io.newsappstarter.data.local.db.ArticleDatabase
import daniellopes.io.newsappstarter.data.remote.NewsApi

class NewsRepository(
   private val db: NewsApi,
   private val api: ArticleDatabase,
   ) {

   //remeto
   suspend fun getAllRemote() = api.getBreakingNews()
   suspend fun search(query: String) = api.searchNews(query)

   //local
   suspend fun updateInsert(article: Article) = db.getArticleDao().updateInsert(article)
   fun getAll(): List<Article> = db.getArticleDao().getAll()
   suspend fun delete(article: Article) = db.getArticleDao().delete(article)


}