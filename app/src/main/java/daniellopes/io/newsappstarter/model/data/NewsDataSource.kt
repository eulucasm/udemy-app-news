package daniellopes.io.newsappstarter.model.data

import daniellopes.io.newsappstarter.network.RetrofitInstance
import daniellopes.io.newsappstarter.presenter.news.NewsHome
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class NewsDataSource {

   fun getBreakingNews(callback: NewsHome.Presenter){
      GlobalScope.launch(Dispatchers.Main){
         val response =  RetrofitInstance.api.getBreakingNews("br")
         if (response.isSuccessful){
            response.body()?.let { newsResponse ->
               callback.onSuccess(newsResponse)
            }
            callback.onComplete()
         }else {
            callback.onError(response.message())
            callback.onComplete()
         }
      }
   }
}