package com.splatool.ikastyle.view

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        // spinnerのアダプター作成
        categoryAdapter = KeyValueArrayAdapter(requireContext(),
            R.layout.spinner_list_item, storeViewModel.getCategoryPairListLiveData().value ?: arrayListOf())
        customizationAdapter = KeyValueArrayAdapter(requireContext(),
            R.layout.spinner_list_item, storeViewModel.getCustomizationPairListLiveData().value ?: arrayListOf())

        // レイアウトを付与
        categoryAdapter.setDropDownViewResource(R.layout.spinner_list_dropdown_item)
        customizationAdapter.setDropDownViewResource(R.layout.spinner_list_dropdown_item)

        // recyclerViewのアダプター作成
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

        binding.recyclerViewLoadouts.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(view.context)
        binding.recyclerViewLoadouts.layoutManager = layoutManager

        // ギアセット未登録時に表示する画像にランダム色を設定
        binding.imageViewInkMark.setColorFilter(colorNum, PorterDuff.Mode.SRC_ATOP)


        observeViewModel(storeViewModel)
    }

    private fun observeViewModel(viewModel: StoreViewModel){
        val categoryObserver = Observer<ArrayList<Pair<Int,String>>>{
            it.let{
                categoryAdapter.resetKeyValues(it)
            }
        }

        val customizationObserver = Observer<ArrayList<Pair<Int, String>>>{
            it.let{
                customizationAdapter.resetKeyValues(it)
            }
        }

        val loadoutObserver = Observer<ArrayList<Loadout>> {
            it.let{
                loadoutAdapter.setLoadoutList(it)

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
}