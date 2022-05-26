package com.splatool.ikastyle.viewModel

import android.view.DragEvent
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.lifecycle.*
import com.splatool.ikastyle.common.Util
import com.splatool.ikastyle.common.const.GearPowerPositionKind
import com.splatool.ikastyle.common.const.NumberPlace
import com.splatool.ikastyle.model.data.entity.Loadout
import com.splatool.ikastyle.model.data.repository.CustomizationMainRepository
import com.splatool.ikastyle.model.data.repository.LoadoutRepository
import com.splatool.ikastyle.model.data.repository.MainCategoryRepository
import com.splatool.ikastyle.ui.GearPowerImageView
import com.splatool.ikastyle.ui.GearPowerReceptorImageView
import kotlinx.coroutines.launch

class NewViewModel(private val categoryRepository: MainCategoryRepository,
                   private val customizationMainRepository: CustomizationMainRepository,
                   private val loadoutRepository: LoadoutRepository) : ViewModel() {

    private val categoryPairListLiveData = MutableLiveData<ArrayList<Pair<Int,String>>>()
    private val weaponPairListLiveData = MutableLiveData<ArrayList<Pair<Int,String>>>()
    private val loadoutLiveData = MutableLiveData<Loadout>()
    //var loadout : Loadout = Loadout()

    fun getCategoryPairListLiveData() : LiveData<ArrayList<Pair<Int,String>>> = categoryPairListLiveData
    fun getWeaponPairListLiveData() : LiveData<ArrayList<Pair<Int,String>>> = weaponPairListLiveData
    fun getLoadoutLiveData() : LiveData<Loadout> = loadoutLiveData

    init{
        loadCategoryList()
        loadWeaponList()

        initLoadout()
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

    private fun initLoadout(){
        loadoutLiveData.postValue(Loadout())
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

    val onGearPowerDragListener : View.OnDragListener = View.OnDragListener { view, dragEvent ->
        if(view is GearPowerReceptorImageView)
        {
            if (dragEvent?.action == DragEvent.ACTION_DROP) {
                val draggedView = dragEvent.localState as? GearPowerImageView
                val gearPowerKind = draggedView?.gearPowerKind ?: 0

                // 一部のギアパワーは特定の部位のメインギアにしか付けられないためこのようなif条件を入れている(e.g.ラストスパートはアタマのメインギアにしか付けられない)
                if (gearPowerKind / 100 == 0 || gearPowerKind / 100 == view.receptorKind.getId() / 10) {
                    // loadoutに値設定
                    when(view.receptorKind){
                        GearPowerPositionKind.HEAD_MAIN -> loadoutLiveData.value!!.headMain = gearPowerKind //loadout.headMain = gearPowerKind
                        GearPowerPositionKind.HEAD_SUB1 -> loadoutLiveData.value!!.headSub1 = gearPowerKind //loadout.headSub1 = gearPowerKind
                        GearPowerPositionKind.HEAD_SUB2 -> loadoutLiveData.value!!.headSub2 = gearPowerKind //loadout.headSub2 = gearPowerKind
                        GearPowerPositionKind.HEAD_SUB3 -> loadoutLiveData.value!!.headSub3 = gearPowerKind //loadout.headSub3 = gearPowerKind
                        GearPowerPositionKind.CLOTHING_MAIN -> loadoutLiveData.value!!.clothingMain = gearPowerKind //loadout.clothingMain = gearPowerKind
                        GearPowerPositionKind.CLOTHING_SUB1 -> loadoutLiveData.value!!.clothingSub1 = gearPowerKind //loadout.clothingSub1 = gearPowerKind
                        GearPowerPositionKind.CLOTHING_SUB2 -> loadoutLiveData.value!!.clothingSub2 = gearPowerKind //loadout.clothingSub2 = gearPowerKind
                        GearPowerPositionKind.CLOTHING_SUB3 -> loadoutLiveData.value!!.clothingSub3 = gearPowerKind //loadout.clothingSub3 = gearPowerKind
                        GearPowerPositionKind.SHOES_MAIN -> loadoutLiveData.value!!.shoesMain = gearPowerKind //loadout.shoesMain = gearPowerKind
                        GearPowerPositionKind.SHOES_SUB1 -> loadoutLiveData.value!!.shoesSub1 = gearPowerKind // loadout.shoesSub1 = gearPowerKind
                        GearPowerPositionKind.SHOES_SUB2 -> loadoutLiveData.value!!.shoesSub2 = gearPowerKind //loadout.shoesSub2 = gearPowerKind
                        GearPowerPositionKind.SHOES_SUB3 -> loadoutLiveData.value!!.shoesSub3 = gearPowerKind //loadout.shoesSub3 = gearPowerKind
                    }
                    loadoutLiveData.postValue(loadoutLiveData.value)
                }
            }
            return@OnDragListener true
        }
        return@OnDragListener false
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