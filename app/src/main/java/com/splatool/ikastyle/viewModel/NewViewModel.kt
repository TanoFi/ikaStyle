package com.splatool.ikastyle.viewModel

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.DragEvent
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.*
import com.splatool.ikastyle.R
import com.splatool.ikastyle.model.common.Util
import com.splatool.ikastyle.model.common.const.GearKind
import com.splatool.ikastyle.model.common.const.GearPowerPositionKind
import com.splatool.ikastyle.model.common.const.NumberPlace
import com.splatool.ikastyle.model.data.entity.Loadout
import com.splatool.ikastyle.model.data.repository.CustomizationMainRepository
import com.splatool.ikastyle.model.data.repository.LoadoutRepository
import com.splatool.ikastyle.model.data.repository.MainCategoryRepository
import com.splatool.ikastyle.view.customizedView.GearPowerImageView
import com.splatool.ikastyle.view.customizedView.GearPowerReceptorImageView
import kotlinx.coroutines.launch

class NewViewModel(private val categoryRepository: MainCategoryRepository,
                   private val customizationMainRepository: CustomizationMainRepository,
                   private val loadoutRepository: LoadoutRepository) : ViewModel() {

    private val categoryPairListLiveData = MutableLiveData<ArrayList<Pair<Int,String>>>()
    private val weaponPairListLiveData = MutableLiveData<ArrayList<Pair<Int,String>>>()
    private val loadoutLiveData = MutableLiveData<Loadout>()

    var categorySpinnerSelectedId : Int = 0
    var weaponSpinnerSelectedId : Int = 0

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

    private fun saveLoadout(loadout: Loadout){
        viewModelScope.launch {
            loadoutRepository.saveLoadout(loadout)
            initLoadout()
        }
    }

    private fun initLoadout(){
        loadoutLiveData.postValue(Loadout())
    }

    fun onCategorySelected(adapterView: AdapterView<*>?, view: View?, i: Int, l: Long) {
        val spinner = adapterView as Spinner

        // 初回表示時は処理を呼ばない
        if(spinner.isFocusable.not()){
            spinner.isFocusable = true
            return
        }

        // 絶対IDからカテゴリーIDを割り出す
        categorySpinnerSelectedId = (spinner.selectedItem as Pair<*, *>).first as Int
        val categoryId = categorySpinnerSelectedId / NumberPlace.CATEGORY_PLACE

        if (categoryId == 0) { // カテゴリーSpinnerで未選択項目が選ばれているとき
            //ブキSpinnerの項目を全表示
            loadWeaponList()
        } else {
            // カテゴリーSpinnerで選択したカテゴリーに属するブキだけをWeaponSpinnerに表示
            loadWeaponListByCategory(categoryId)
        }
    }

    fun onWeaponSelected(adapterView: AdapterView<*>?, view: View?, i: Int, l: Long) {
        val spinner = adapterView as Spinner
        val absoluteWeaponId = (spinner.selectedItem as Pair<*, *>).first as Int
        weaponSpinnerSelectedId = absoluteWeaponId
        loadoutLiveData.value!!.categoryId = Util.getCategoryId(absoluteWeaponId)
        loadoutLiveData.value!!.mainId = Util.getMainId(absoluteWeaponId)
        loadoutLiveData.value!!.customizationId = Util.getCustomizationId(absoluteWeaponId)
    }

    fun onSaveButtonClicked(view : View){
        loadoutLiveData.value!!.updateDate = System.currentTimeMillis()

        if(checkUserInput(view.context)){
            save(view.context)
        }
    }

    fun onPostDialogItemClicked(gearKind: GearKind, gearId: Int){
        when(gearKind){
            GearKind.HEAD -> loadoutLiveData.value!!.headGearId = gearId
            GearKind.CLOTHING -> loadoutLiveData.value!!.clothingGearId = gearId
            GearKind.SHOES -> loadoutLiveData.value!!.shoesGearId = gearId
        }

        loadoutLiveData.postValue(loadoutLiveData.value)
    }

    /*
    * 入力チェック用のメソッド
    * 戻り値 true → 問題なし, false → 問題あり
    */
    private fun checkUserInput(context : Context): Boolean {
        // ブキSpinnerが未選択状態
        if (loadoutLiveData.value!!.categoryId == 0 && loadoutLiveData.value!!.mainId == 0 && loadoutLiveData.value!!.customizationId == 0) {
            // Toastでメッセージ表示
            val toast = Toast.makeText(
                context,
                context.getString(R.string.toastMessage_spinnerNotSelected),
                Toast.LENGTH_LONG
            )
            toast.show()
            return false
        }

        // メインギアパワーが設定されていない
        if (loadoutLiveData.value!!.headMain == 0 || loadoutLiveData.value!!.clothingMain == 0 || loadoutLiveData.value!!.shoesMain == 0) {
            // Toastでメッセージ表示
            val toast = Toast.makeText(
                context,
                context.getString(R.string.toastMessage_mainGearPowerNotSet),
                Toast.LENGTH_LONG
            )
            toast.show()
            return false
        }
        return true
    }

    /*
     * 入力値をデータベースに保存
     */
    private fun save(context: Context) {
        saveLoadout(loadoutLiveData.value!!)
        val toast = Toast.makeText(context, context.getString(R.string.toastMessage_insertCompleted), Toast.LENGTH_LONG)
        toast.show()
        initLoadout()
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

    val loadoutNameTextWatcher : TextWatcher = object : TextWatcher{
        override fun afterTextChanged(p0: Editable?) {
            loadoutLiveData.value!!.name = p0?.toString() ?: ""
        }
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
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