package com.splatool.ikastyle.viewModel

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.lifecycle.*
import com.splatool.ikastyle.common.const.NumberPlace
import com.splatool.ikastyle.model.data.entity.Loadout
import com.splatool.ikastyle.model.data.repository.CustomizationMainRepository
import com.splatool.ikastyle.model.data.repository.LoadoutRepository
import com.splatool.ikastyle.model.data.repository.MainCategoryRepository
import kotlinx.coroutines.launch

class NewViewModel(private val categoryRepository: MainCategoryRepository,
                   private val customizationMainRepository: CustomizationMainRepository,
                   private val loadoutRepository: LoadoutRepository) : ViewModel() {

    private val categoryPairListLiveData = MutableLiveData<ArrayList<Pair<Int,String>>>()
    private val weaponPairListLiveData = MutableLiveData<ArrayList<Pair<Int,String>>>()

    fun getCategoryPairListLiveData() : LiveData<ArrayList<Pair<Int,String>>> = categoryPairListLiveData
    fun getWeaponPairListLiveData() : LiveData<ArrayList<Pair<Int,String>>> = weaponPairListLiveData

    init{
        // LiveDataに初期値を入れる
        categoryPairListLiveData.value = arrayListOf()
        weaponPairListLiveData.value = arrayListOf()

        loadCategoryList()
        loadWeaponList()
    }

    private fun loadCategoryList(){
        viewModelScope.launch {
            val categoryPairList = categoryRepository.getCategoryList()
            categoryPairListLiveData.postValue(categoryPairList)
        }
    }

    private fun loadWeaponList(){
        viewModelScope.launch {
            val weaponPairList = customizationMainRepository.getWeapon()
            weaponPairListLiveData.postValue(weaponPairList)
        }
    }

    private fun loadWeaponListByCategory(categoryId : Int){
        viewModelScope.launch {
            val weaponPairList = customizationMainRepository.getWeaponByCategory(categoryId)
            weaponPairListLiveData.postValue(weaponPairList)
        }
    }

    fun onCategorySelected(adapterView: AdapterView<*>?, view: View?, i: Int, l: Long) {
        val spinner = adapterView as Spinner

        // 絶対IDからカテゴリーIDを割り出す
        val categoryId = ((spinner.selectedItem as Pair<*, *>).first as Int) / NumberPlace.CATEGORY_PLACE

        if (categoryId == 0) { // カテゴリーSpinnerで未選択項目が選ばれているとき
            //ブキSpinnerの項目を全表示
            loadWeaponList()
        } else {
            // カテゴリーSpinnerで選択したカテゴリーに属するブキだけをWeaponSpinnerに表示
            loadWeaponListByCategory(categoryId)
        }
    }

    class NewFactory(private val categoryRepository: MainCategoryRepository,
                     private val customizationMainRepository: CustomizationMainRepository,
                     private val loadoutRepository: LoadoutRepository) : ViewModelProvider.NewInstanceFactory(){
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return NewViewModel(categoryRepository, customizationMainRepository, loadoutRepository) as T
        }
    }
}