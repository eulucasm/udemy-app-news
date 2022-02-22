package daniellopes.io.newsappstarter.repository

import daniellopes.io.newsappstarter.data.local.model.Article
import daniellopes.io.newsappstarter.data.local.db.ArticleDatabase

class NewsRepository(private val db: ArticleDatabase) {

   suspend fun updateInsert(article: Article) = db.getArticleDao().updateInsert(article)

   fun getAll(): List<Article> = db.getArticleDao().getAll()

   suspend fun delete(article: Article) = db.getArticleDao().delete(article)


}