package daniellopes.io.newsappstarter.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import daniellopes.io.newsappstarter.R
import daniellopes.io.newsappstarter.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

   private lateinit var navHostFragment: NavHostFragment
   private lateinit var binding: ActivityMainBinding

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityMainBinding.inflate(layoutInflater)
      setContentView(binding.root)

      initViews(binding)
   }

   private fun initViews(binding: ActivityMainBinding) {
      navHostFragment =
         supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
      val navController = navHostFragment.navController

      //seta o nome na toolbar com o nome descrito na nav_graph
      navController.addOnDestinationChangedListener {_, destination, _ ->
         title = destination.label
      }

      binding.bottomNavigation.apply {
         setupWithNavController(navController)
         setOnNavigationItemReselectedListener { }
      }
   }

}

