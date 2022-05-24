package com.splatool.ikastyle.viewModel

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.lifecycle.*
import com.splatool.ikastyle.R
import com.splatool.ikastyle.common.const.NumberPlace
import com.splatool.ikastyle.model.data.database.AppDatabase
import com.splatool.ikastyle.model.data.databaseView.CustomizationMain
import com.splatool.ikastyle.model.data.entity.MainCategory
import com.splatool.ikastyle.model.data.repository.CustomizationNameRepository
import com.splatool.ikastyle.model.data.repository.MainCategoryRepository
import com.splatool.ikastyle.ui.KeyValueArrayAdapter
import kotlinx.coroutines.launch

class StoreViewModel(private val categoryRepository: MainCategoryRepository,
                     private val customizationRepository : CustomizationNameRepository) : ViewModel() {

    var categoryPairListLiveData = MutableLiveData<ArrayList<Pair<Int,String>>>()
    var customizationPairListLiveData = MutableLiveData<ArrayList<Pair<Int,String>>>()
    //var categoryPairList : List<Pair<Int, String>> = listOf()
    //var customizationPairList : ArrayList<Pair<Int, String>> = arrayListOf()
    //var customizationMainListLiveData = MutableLiveData<List<CustomizationMain>>()

    init{
        // LiveDataに初期値を入れる
        categoryPairListLiveData.value = arrayListOf()
        customizationPairListLiveData.value = arrayListOf()

        loadCategoryList()
        loadCustomizationList()
    }

    private fun loadCategoryList(){
        viewModelScope.launch {
            val categoryList = categoryRepository.getCategoryList()

            val tempCategoryPairList : ArrayList<Pair<Int, String>> = arrayListOf()
            categoryList.forEach{
                tempCategoryPairList.add(Pair(it.getAbsoluteId(), it.name))
            }
            categoryPairListLiveData.postValue(tempCategoryPairList)
        }
    }

    private fun loadCustomizationList(){
        viewModelScope.launch {
            val customizationList = customizationRepository.getCustomizationList()

            val tempCustomizationPairList : ArrayList<Pair<Int, String>> = arrayListOf()
            customizationList.forEach{
                tempCustomizationPairList.add(Pair(it.getAbsoluteId(), it.name))
            }
            customizationPairListLiveData.postValue(tempCustomizationPairList)
        }
    }

    private fun loadCustomizationListByCategory(categoryId : Int){
        viewModelScope.launch {
            val customizationList = customizationRepository.getCustomizationListByCategory(categoryId)

            val tempCustomizationPairList : ArrayList<Pair<Int, String>> = arrayListOf()
            customizationList.forEach{
                tempCustomizationPairList.add(Pair(it.getAbsoluteId(), it.name))
            }
            customizationPairListLiveData.postValue(tempCustomizationPairList)
        }
    }
    
    public fun onCategorySelected(adapterView: AdapterView<*>?, view: View?, i: Int, l: Long) {
        val spinner = adapterView as Spinner

        // 絶対IDからカテゴリーIDを割り出す
        val categoryId = ((spinner.selectedItem as Pair<*, *>).first as Int) / NumberPlace.CATEGORY_PLACE

        if (categoryId == 0) { // カテゴリーSpinnerで未選択項目が選ばれているとき
            //ブキSpinnerの項目を全表示にする
            loadCustomizationList()
        } else {
            // カテゴリーSpinnerで選択したカテゴリーに属するブキだけをブキSpinnerに表示される
            loadCustomizationListByCategory(categoryId)
        }
    }

    class StoreFactory(private val categoryRepository: MainCategoryRepository, private val customizationRepository: CustomizationNameRepository) : ViewModelProvider.NewInstanceFactory(){
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return StoreViewModel(categoryRepository, customizationRepository) as T
        }
    }
}