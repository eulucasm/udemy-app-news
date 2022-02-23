package daniellopes.io.newsappstarter.ui.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import daniellopes.io.newsappstarter.data.local.model.NewsResponse
import daniellopes.io.newsappstarter.repository.NewsRepository
import daniellopes.io.newsappstarter.util.state.StateResource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class HomeViewModel constructor(
   private val repository: NewsRepository
):ViewModel() {

   private val _getAll = MutableLiveData<StateResource<NewsResponse>>()
   val getAll: LiveData<StateResource<NewsResponse>> get() = _getAll

   init{
      fetchAll()
   }

   private fun fetchAll()= viewModelScope.launch {
      safeFetchAll()
   }

   private suspend fun safeFetchAll() = viewModelScope.launch {
      _getAll.value = StateResource.Loading()
      try{
         val response = repository.getAllRemote()
         _getAll.value = handleResponse(response)
      }catch (e: Exception){
         _getAll.value = StateResource.Error("Artigos não encontrador: ${e.message}")
      }
   }

   private fun handleResponse(response: Response<NewsResponse>): StateResource<NewsResponse>{
      if(response.isSuccessful){
         response.body()?.let{values->
            return StateResource.Success(values)
         }
      }
      return StateResource.Error(response.message())
   }

}