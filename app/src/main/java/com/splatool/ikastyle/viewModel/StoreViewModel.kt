package com.splatool.ikastyle.viewModel

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.*
import com.splatool.ikastyle.R
import com.splatool.ikastyle.model.common.const.NumberPlace
import com.splatool.ikastyle.model.data.entity.Loadout
import com.splatool.ikastyle.model.data.repository.CustomizationNameRepository
import com.splatool.ikastyle.model.data.repository.LoadoutRepository
import com.splatool.ikastyle.model.data.repository.MainCategoryRepository
import com.splatool.ikastyle.view.customizedView.LoadoutDeleteButton
import kotlinx.coroutines.launch

class StoreViewModel(private val categoryRepository: MainCategoryRepository,
                     private val customizationRepository : CustomizationNameRepository,
                     private val loadoutRepository: LoadoutRepository) : ViewModel() {

    private val categoryPairListLiveData = MutableLiveData<ArrayList<Pair<Int,String>>>()
    private val customizationPairListLiveData = MutableLiveData<ArrayList<Pair<Int,String>>>()
    private val loadoutListLiveData = MutableLiveData<ArrayList<Loadout>>()

    var categorySpinnerSelectedId : Int = 0
    var weaponSpinnerSelectedId : Int = 0

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

            // ????????????LoadoutList?????????????????????????????????
            val postDeleteLoadoutList = loadoutListLiveData.value!!.filter { it.id !=  loadoutId}.toMutableList() as ArrayList<Loadout>
            loadoutListLiveData.postValue(postDeleteLoadoutList)
        }
    }
    
    fun onCategorySelected(adapterView: AdapterView<*>?, view: View?, i: Int, l: Long) {
        val spinner = adapterView as Spinner

        // ??????ID?????????????????????ID???????????????
        categorySpinnerSelectedId = (spinner.selectedItem as Pair<*, *>).first as Int
        val categoryId = categorySpinnerSelectedId / NumberPlace.CATEGORY_PLACE

        if (categoryId == 0) { // ???????????????Spinner?????????????????????????????????????????????
            //??????Spinner?????????????????????
            loadCustomizationList()
        } else {
            // ???????????????Spinner?????????????????????????????????????????????????????????CustomizationSpinner?????????
            loadCustomizationListByCategory(categoryId)
        }
    }

    fun onCustomizationSelected(adapterView: AdapterView<*>?, view: View?, i: Int, l: Long){
        val spinner = adapterView as Spinner

        // ???????????????????????????????????????
        if(spinner.isFocusable.not()){
            spinner.isFocusable = true
            return
        }

        val absoluteWeaponId = (spinner.selectedItem as Pair<*, *>).first as Int
        weaponSpinnerSelectedId = absoluteWeaponId
        loadLoadoutList(absoluteWeaponId)
    }

    fun onDeleteButtonClicked(view: View){
        val deleteButton : LoadoutDeleteButton = view as LoadoutDeleteButton

        // ??????????????????????????????
        AlertDialog.Builder(view.context)
            .setTitle(view.context.getString(R.string.dialogMessage_confirm))
            .setPositiveButton( // Yes???????????????
                view.context.getString(R.string.buttonNavigation_yes)
            ) { _, _ ->
                deleteLoadout(deleteButton.loadoutId) // ?????????loadout???????????????
            }
            .setNegativeButton( // No???????????????
                view.context.getString(R.string.buttonNavigation_no),  // ???????????????
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