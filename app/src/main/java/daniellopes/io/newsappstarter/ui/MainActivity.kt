package daniellopes.io.newsappstarter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import daniellopes.io.newsappstarter.R
import daniellopes.io.newsappstarter.adapter.MainAdapter
import daniellopes.io.newsappstarter.model.Article
import daniellopes.io.newsappstarter.model.data.NewsDataSource
import daniellopes.io.newsappstarter.presenter.ViewHome
import daniellopes.io.newsappstarter.presenter.news.NewsPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AbstractActivity(), ViewHome.View {

    private val mainAdapter by lazy {
        MainAdapter()
    }
    private lateinit var presenter: NewsPresenter

    override fun getLayout(): Int = R.layout.activity_main

    override fun onInject() {
        val dataSource = NewsDataSource()
        presenter = NewsPresenter(this, dataSource)
        presenter.requestAll()
        configRecycles()
    }

    private fun configRecycles(){
        with(rvNews){
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(
                this@MainActivity, DividerItemDecoration.VERTICAL
            ))
        }
    }

    override fun showProgressBar() {
        rvProgressBar.visibility = View.VISIBLE
    }

    override fun showFailure(message: String) {
        Toast.makeText(this, message,Toast.LENGTH_LONG).show()
    }

    override fun hideProgressBar() {
        rvProgressBar.visibility = View.INVISIBLE

    }

    override fun showArticles(articles: List<Article>) {
        mainAdapter.differ.submitList(articles.toList())
    }

}