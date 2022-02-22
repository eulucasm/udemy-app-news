package daniellopes.io.newsappstarter.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.*
import com.google.android.material.snackbar.Snackbar
import daniellopes.io.newsappstarter.R
import daniellopes.io.newsappstarter.adapter.MainAdapter
import daniellopes.io.newsappstarter.databinding.ActivityFavoriteBinding
import daniellopes.io.newsappstarter.model.Article
import daniellopes.io.newsappstarter.model.data.NewsDataSource
import daniellopes.io.newsappstarter.presenter.ViewHome
import daniellopes.io.newsappstarter.presenter.favorite.FavoritePresenter

class FavoriteActivity : AppCompatActivity(), ViewHome.Favorite {

   private val mainAdapter by lazy {
      MainAdapter()
   }

   private lateinit var presenter: FavoritePresenter
   private lateinit var binding: ActivityFavoriteBinding

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityFavoriteBinding.inflate(layoutInflater)
      val view = binding.root
      setContentView(view)

      val dataSource = NewsDataSource(this)
      presenter = FavoritePresenter(this, dataSource)
      presenter.getAll()
      configRecycles()
      clickAdapter()

      val itemTouchPerCallback = object : ItemTouchHelper.SimpleCallback(
         ItemTouchHelper.UP or ItemTouchHelper.DOWN,
         ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
      ) {
         override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder,
         ): Boolean {
            return true
         }

         override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            val article = mainAdapter.differ.currentList[position]
            presenter.deleteArticle(article)
            Snackbar.make(
               viewHolder.itemView, R.string.article_delete_successful,
               Snackbar.LENGTH_SHORT
            ).apply {
               setAction(getString(R.string.undo)) {
                  presenter.saveArticle(article)
                  mainAdapter.notifyDataSetChanged()
               }
               show()
            }
         }
      }
      ItemTouchHelper(itemTouchPerCallback).apply{
         attachToRecyclerView(binding.rvFavorite)
      }
      presenter.getAll()
   }


   override fun showArticles(articles: List<Article>) {
      mainAdapter.differ.submitList(articles.toList())
   }

   private fun configRecycles() {
      with(binding.rvFavorite) {
         adapter = mainAdapter
         layoutManager = LinearLayoutManager(this@FavoriteActivity)
         addItemDecoration(DividerItemDecoration(
            this@FavoriteActivity, DividerItemDecoration.VERTICAL
         ))
      }
   }

   private fun clickAdapter() {
      mainAdapter.setOnClickListener { article ->
         val intent = Intent(this, ArticleActivity::class.java)
         intent.putExtra("article", article)
         startActivity(intent)
      }
   }


}