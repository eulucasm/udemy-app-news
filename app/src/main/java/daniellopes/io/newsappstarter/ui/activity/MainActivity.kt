package daniellopes.io.newsappstarter.ui.activity

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
import daniellopes.io.newsappstarter.ui.adapter.MainAdapter
import daniellopes.io.newsappstarter.databinding.ActivityMainBinding
import daniellopes.io.newsappstarter.data.local.model.Article
import daniellopes.io.newsappstarter.repository.NewsDataSource
import daniellopes.io.newsappstarter.presenter.ViewHome
import daniellopes.io.newsappstarter.presenter.news.NewsPresenter


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}

