package daniellopes.io.newsappstarter.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class AbstractActivity : AppCompatActivity() {

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(getLayout())
      onInject()
   }

   //passando essa anotação, ele realmente vai ser um arquivo de resources layout
   @LayoutRes
   protected abstract fun getLayout(): Int

   protected abstract fun onInject()
}