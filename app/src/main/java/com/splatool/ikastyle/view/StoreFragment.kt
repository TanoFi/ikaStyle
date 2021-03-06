package com.splatool.ikastyle.view

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.splatool.ikastyle.R
import com.splatool.ikastyle.model.common.Util
import com.splatool.ikastyle.databinding.FragmentStoreBinding
import com.splatool.ikastyle.model.data.database.AppDatabase
import com.splatool.ikastyle.model.data.entity.Loadout
import com.splatool.ikastyle.model.data.repository.CustomizationNameRepository
import com.splatool.ikastyle.model.data.repository.LoadoutRepository
import com.splatool.ikastyle.model.data.repository.MainCategoryRepository
import com.splatool.ikastyle.view.apapter.KeyValueArrayAdapter
import com.splatool.ikastyle.view.apapter.LoadoutRecyclerViewAdapter
import com.splatool.ikastyle.viewModel.StoreViewModel

class StoreFragment : Fragment() {
    private lateinit var storeViewModel: StoreViewModel
    private lateinit var categoryAdapter : KeyValueArrayAdapter
    private lateinit var customizationAdapter : KeyValueArrayAdapter
    private lateinit var loadoutAdapter: LoadoutRecyclerViewAdapter

    private lateinit var binding : FragmentStoreBinding

    private val colorNum = Util.getRandomColor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db : AppDatabase = AppDatabase.getDatabase(requireContext())
        val categoryRepository = MainCategoryRepository(db.mainCategoryDao())
        val customizationRepository = CustomizationNameRepository(db.customizationNameDao())
        val loadoutRepository = LoadoutRepository(db.loadoutDao())

        storeViewModel = ViewModelProvider(this, StoreViewModel.StoreFactory(categoryRepository, customizationRepository, loadoutRepository))[StoreViewModel::class.java]

        // spinner????????????????????????
        categoryAdapter = KeyValueArrayAdapter(requireContext(),
            R.layout.spinner_list_item, storeViewModel.getCategoryPairListLiveData().value ?: arrayListOf())
        customizationAdapter = KeyValueArrayAdapter(requireContext(),
            R.layout.spinner_list_item, storeViewModel.getCustomizationPairListLiveData().value ?: arrayListOf())

        // ????????????????????????
        categoryAdapter.setDropDownViewResource(R.layout.spinner_list_dropdown_item)
        customizationAdapter.setDropDownViewResource(R.layout.spinner_list_dropdown_item)

        // recyclerView????????????????????????
        loadoutAdapter = LoadoutRecyclerViewAdapter(storeViewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStoreBinding.inflate(inflater, container, false)
        binding.viewModel = storeViewModel
        binding.spinnerCategory.adapter = categoryAdapter
        binding.spinnerWeapon.adapter = customizationAdapter
        binding.recyclerViewLoadouts.adapter = loadoutAdapter
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ????????????onItemSelected()?????????????????????????????????????????????
        setSpinnerFocusableFalse()
        // spinner???onItemSelectedListener?????????
        setCategorySelectedListener()

        // recyclerView????????????????????????
        binding.recyclerViewLoadouts.apply {
            binding.recyclerViewLoadouts.setHasFixedSize(true)
            binding.recyclerViewLoadouts.layoutManager = LinearLayoutManager(view.context)
        }

        // ???????????????????????????????????????????????????????????????????????????
        binding.imageViewInkMark.setColorFilter(colorNum, PorterDuff.Mode.SRC_ATOP)

        // LiveData???Observer??????
        observeViewModel(storeViewModel)
    }

    /*
     * LiveData???Observer?????????
     */
    private fun observeViewModel(viewModel: StoreViewModel){
        val categoryObserver = Observer<ArrayList<Pair<Int,String>>>{
            it.let{
                // CategorySpinner?????????????????????????????????????????????????????????
                if(it[0].first != 0) {
                    it.add(0, Pair(0, requireContext().getString(R.string.spinnerItem_categoryUnselected)))
                }
                // CategorySpinner???????????????
                categoryAdapter.resetKeyValues(it)
            }
        }

        val customizationObserver = Observer<ArrayList<Pair<Int, String>>>{
            it.let{
                // WeaponSpinner?????????????????????????????????????????????????????????
                if(it[0].first != 0) {
                    it.add(0, Pair(0, requireContext().getString(R.string.spinnerItem_weaponUnselected)))
                }
                // WeaponSpinner???????????????
                customizationAdapter.resetKeyValues(it)
            }
        }

        val loadoutObserver = Observer<ArrayList<Loadout>> {
            it.let{
                // LoadoutAdapter???????????????
                loadoutAdapter.setLoadoutList(it)

                // ?????????????????????????????????EmptyView???????????????????????????
                if(it.size == 0){
                    binding.constrainLayoutEmptyInfo.visibility = View.VISIBLE
                }
                else{
                    binding.constrainLayoutEmptyInfo.visibility = View.GONE
                }
            }
        }

        viewModel.getCategoryPairListLiveData().observe(viewLifecycleOwner, categoryObserver)
        viewModel.getCustomizationPairListLiveData().observe(viewLifecycleOwner, customizationObserver)
        viewModel.getLoadoutListLiveData().observe(viewLifecycleOwner, loadoutObserver)
    }

    /*
     * Spinner???isFocusable???false?????????
     */
    private fun setSpinnerFocusableFalse(){
        binding.spinnerCategory.isFocusable = false
        binding.spinnerWeapon.isFocusable = false
    }

    /*
     * CategorySpinner???onItemSelectedListener?????????
     */
    private fun setCategorySelectedListener(){
        binding.spinnerCategory.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(adapter: AdapterView<*>?, view: View?, i: Int, l: Long) {
                val spinner = adapter as Spinner
                val selectedCategorySpinnerId = (spinner.selectedItem as Pair<*,*>).first as Int
                val selectedWeaponSpinnerId = (binding.spinnerWeapon.selectedItem as Pair<*, *>).first as Int

                // ???????????????????????????????????????
                if((spinner.isFocusable.not() || selectedWeaponSpinnerId == storeViewModel.weaponSpinnerSelectedId) && selectedCategorySpinnerId == storeViewModel.categorySpinnerSelectedId){
                    spinner.isFocusable = true
                    return
                }
                else{
                    storeViewModel.onCategorySelected(adapter, view, i, l)
                    binding.spinnerWeapon.setSelection(0)
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }
}