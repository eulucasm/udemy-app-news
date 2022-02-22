package daniellopes.io.newsappstarter.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import daniellopes.io.newsappstarter.R
import daniellopes.io.newsappstarter.adapter.MainAdapter
import daniellopes.io.newsappstarter.databinding.ActivityMainBinding
import daniellopes.io.newsappstarter.model.Article
import daniellopes.io.newsappstarter.model.data.NewsDataSource
import daniellopes.io.newsappstarter.presenter.ViewHome
import daniellopes.io.newsappstarter.presenter.news.NewsPresenter


class MainActivity : AppCompatActivity(), ViewHome.View {

    private val mainAdapter by lazy {
        MainAdapter()
    }
    private lateinit var presenter: NewsPresenter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val dataSource = NewsDataSource(this)
        presenter = NewsPresenter(this, dataSource)
        presenter.requestAll()
        configRecycles()
        clickAdapter()
    }


    private fun configRecycles(){
        with(binding.rvNews){
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(
                this@MainActivity, DividerItemDecoration.VERTICAL
            ))
        }
    }
    private fun clickAdapter(){
        mainAdapter.setOnClickListener { article ->
            val intent = Intent(this, ArticleActivity::class.java)
            intent.putExtra("article", article)
            startActivity(intent)
        }
    }

    override fun showProgressBar() {
        binding.rvProgressBar.visibility = View.VISIBLE
    }

    override fun showFailure(message: String) {
        Toast.makeText(this, message,Toast.LENGTH_LONG).show()
    }

    override fun hideProgressBar() {
        binding.rvProgressBar.visibility = View.INVISIBLE

    }

    override fun showArticles(articles: List<Article>) {
        mainAdapter.differ.submitList(articles.toList())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.search_menu->{
                Intent(this, SearchActivity::class.java).apply {
                    startActivity(this)
                }
            }
            R.id.Favorite->{
                Intent(this, FavoriteActivity::class.java).apply {
                    startActivity(this)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

}