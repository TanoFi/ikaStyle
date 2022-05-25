package com.splatool.ikastyle.viewModel

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.*
import com.splatool.ikastyle.R
import com.splatool.ikastyle.common.Util
import com.splatool.ikastyle.common.const.NumberPlace
import com.splatool.ikastyle.model.data.database.AppDatabase
import com.splatool.ikastyle.model.data.databaseView.CustomizationMain
import com.splatool.ikastyle.model.data.entity.Loadout
import com.splatool.ikastyle.model.data.entity.MainCategory
import com.splatool.ikastyle.model.data.repository.CustomizationNameRepository
import com.splatool.ikastyle.model.data.repository.LoadoutRepository
import com.splatool.ikastyle.model.data.repository.MainCategoryRepository
import com.splatool.ikastyle.ui.KeyValueArrayAdapter
import com.splatool.ikastyle.ui.LoadoutDeleteButton
import kotlinx.coroutines.launch

class StoreViewModel(private val categoryRepository: MainCategoryRepository,
                     private val customizationRepository : CustomizationNameRepository,
                     private val loadoutRepository: LoadoutRepository) : ViewModel() {

    private val categoryPairListLiveData = MutableLiveData<ArrayList<Pair<Int,String>>>()
    private val customizationPairListLiveData = MutableLiveData<ArrayList<Pair<Int,String>>>()
    private val loadoutListLiveData = MutableLiveData<ArrayList<Loadout>>()

    fun getCategoryPairListLiveData() : LiveData<ArrayList<Pair<Int,String>>> = categoryPairListLiveData
    fun getCustomizationPairListLiveData() : LiveData<ArrayList<Pair<Int,String>>> = customizationPairListLiveData
    fun getLoadoutListLiveData() : LiveData<ArrayList<Loadout>> = loadoutListLiveData

    init{
        loadCategoryList()
        loadCustomizationList()
    }

    private fun loadCategoryList(){
        viewModelScope.launch {
            val categoryPairList = categoryRepository.getCategoryList()
            categoryPairListLiveData.postValue(categoryPairList)
        }
    }

    private fun loadCustomizationList(){
        viewModelScope.launch {
            val customizationPairList = customizationRepository.getCustomizationList()
            customizationPairListLiveData.postValue(customizationPairList)
        }
    }

    private fun loadCustomizationListByCategory(categoryId : Int){
        viewModelScope.launch {
            val customizationPairList = customizationRepository.getCustomizationListByCategory(categoryId)
            customizationPairListLiveData.postValue(customizationPairList)
        }
    }

    private fun loadLoadoutList(absoluteWeaponId : Int){
        viewModelScope.launch {
            val loadoutList = loadoutRepository.getLoadoutList(absoluteWeaponId)
            loadoutListLiveData.postValue(loadoutList)
        }
    }

    private fun deleteLoadout(loadoutId : Int){
        viewModelScope.launch {
            loadoutRepository.deleteLoadout(loadoutId)

            // 削除後のLoadoutListをライブデータに入れる
            val postDeleteLoadoutList = loadoutListLiveData.value!!.filter { it.id !=  loadoutId}.toMutableList() as ArrayList<Loadout>
            loadoutListLiveData.postValue(postDeleteLoadoutList)
        }
    }
    
    fun onCategorySelected(adapterView: AdapterView<*>?, view: View?, i: Int, l: Long) {
        val spinner = adapterView as Spinner

        // 絶対IDからカテゴリーIDを割り出す
        val categoryId = ((spinner.selectedItem as Pair<*, *>).first as Int) / NumberPlace.CATEGORY_PLACE

        if (categoryId == 0) { // カテゴリーSpinnerで未選択項目が選ばれているとき
            //ブキSpinnerの項目を全表示
            loadCustomizationList()
        } else {
            // カテゴリーSpinnerで選択したカテゴリーに属するブキだけをCustomizationSpinnerに表示
            loadCustomizationListByCategory(categoryId)
        }
    }

    fun onCustomizationSelected(adapterView: AdapterView<*>?, view: View?, i: Int, l: Long){
        val spinner = adapterView as Spinner

        val absoluteWeaponId = (spinner.selectedItem as Pair<*, *>).first as Int
        loadLoadoutList(absoluteWeaponId)
    }

    fun onDeleteButtonClicked(view: View){
        val deleteButton : LoadoutDeleteButton = view as LoadoutDeleteButton

        // 確認ダイアログを表示
        AlertDialog.Builder(view.context)
            .setTitle(view.context.getString(R.string.dialogMessage_confirm))
            .setPositiveButton( // Yesを選んだ時
                view.context.getString(R.string.buttonNavigation_yes)
            ) { dialogInterface, i ->
                deleteLoadout(deleteButton.loadoutId) // 当該のloadoutデータ削除
            }
            .setNegativeButton( // Noを選んだ時
                view.context.getString(R.string.buttonNavigation_no),  // 何もしない
                null
            )
            .show()
    }

    class StoreFactory(private val categoryRepository: MainCategoryRepository,
                       private val customizationRepository: CustomizationNameRepository,
                       private val loadoutRepository: LoadoutRepository) : ViewModelProvider.NewInstanceFactory(){
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return StoreViewModel(categoryRepository, customizationRepository, loadoutRepository) as T
        }
    }
}