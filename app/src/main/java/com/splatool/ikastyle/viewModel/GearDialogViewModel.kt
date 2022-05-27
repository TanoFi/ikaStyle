package com.splatool.ikastyle.viewModel

import android.view.View
import androidx.lifecycle.*
import com.splatool.ikastyle.NewFragment
import com.splatool.ikastyle.common.const.GearKind
import com.splatool.ikastyle.model.data.entity.ClothingGear
import com.splatool.ikastyle.model.data.entity.HeadGear
import com.splatool.ikastyle.model.data.entity.ShoesGear
import com.splatool.ikastyle.model.data.repository.CustomizationNameRepository
import com.splatool.ikastyle.model.data.repository.GearRepository
import com.splatool.ikastyle.model.data.repository.LoadoutRepository
import com.splatool.ikastyle.model.data.repository.MainCategoryRepository
import com.splatool.ikastyle.ui.GearListItemLinearLayout
import kotlinx.coroutines.launch

class GearDialogViewModel(private val gearRepository: GearRepository, gearKind: GearKind) : ViewModel() {
    private val  gearListLiveData = MutableLiveData<ArrayList<*>>()
    private val selectedGearIdLiveData = MutableLiveData<Int>()

    fun getGearListLiveData() : LiveData<ArrayList<*>> = gearListLiveData
    fun getSelectedGearIdLiveData() : LiveData<Int> = selectedGearIdLiveData

    init{
        when(gearKind){
            GearKind.HEAD -> {
                getHeadGear()
            }
            GearKind.CLOTHING -> {
                getClothingGear()
            }
            GearKind.SHOES -> {
                getShoesGear()
            }
        }
    }

    private fun getHeadGear(){
        viewModelScope.launch{
            val headGearList = gearRepository.getHeadGearList()
            gearListLiveData.postValue(headGearList)
        }
    }

    private fun getClothingGear(){
        viewModelScope.launch {
            val clothingGearList = gearRepository.getClothingGear()
            gearListLiveData.postValue(clothingGearList)
        }
    }

    private fun getShoesGear(){
        viewModelScope.launch {
            val shoesGearList = gearRepository.getShoesGear()
            gearListLiveData.postValue(shoesGearList)
        }
    }

    fun onListItemClicked(view : View){
        val listItem = view as GearListItemLinearLayout
        selectedGearIdLiveData.postValue(listItem.gearId)
    }

    class GearFactory(private val gearRepository: GearRepository, private val gearKind: GearKind
    ) : ViewModelProvider.NewInstanceFactory(){
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return GearDialogViewModel(gearRepository, gearKind) as T
        }
    }

}