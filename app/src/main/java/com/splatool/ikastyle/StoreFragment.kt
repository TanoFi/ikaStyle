package com.splatool.ikastyle

import android.app.AlertDialog
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.splatool.ikastyle.model.data.database.AppDatabase
import android.os.AsyncTask
import com.splatool.ikastyle.model.data.entity.Loadout
import com.splatool.ikastyle.ui.LoadoutDeleteButton
import com.splatool.ikastyle.ui.KeyValueArrayAdapter
import androidx.constraintlayout.widget.ConstraintLayout
import com.splatool.ikastyle.ui.LoadoutRecyclerViewAdapter
import com.splatool.ikastyle.model.data.entity.MainCategory
import com.splatool.ikastyle.model.data.entity.CustomizationName
import com.splatool.ikastyle.ui.CategorySpinnerSelectedListener
import android.graphics.PorterDuff
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.splatool.ikastyle.common.Util
import com.splatool.ikastyle.databinding.FragmentStoreBinding
import com.splatool.ikastyle.model.data.repository.CustomizationNameRepository
import com.splatool.ikastyle.model.data.repository.LoadoutRepository
import com.splatool.ikastyle.model.data.repository.MainCategoryRepository
import com.splatool.ikastyle.ui.CustomizationSpinnerSelectedListener
import com.splatool.ikastyle.viewModel.StoreViewModel
import java.util.ArrayList
import java.util.function.Consumer

class StoreFragment : Fragment() {
    private lateinit var categorySpinner: Spinner
    private lateinit var customizationSpinner: Spinner
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyView: ConstraintLayout
    private val colorNum = Util.getRandomColor()

    private lateinit var storeViewModel: StoreViewModel
    private lateinit var categoryAdapter : KeyValueArrayAdapter
    private lateinit var customizationAdapter : KeyValueArrayAdapter
    private lateinit var loadoutAdapter: LoadoutRecyclerViewAdapter

    private lateinit var binding : FragmentStoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db : AppDatabase = AppDatabase.getDatabase(requireContext())
        val categoryRepository = MainCategoryRepository(db.mainCategoryDao())
        val customizationRepository = CustomizationNameRepository(db.customizationNameDao())
        val loadoutRepository = LoadoutRepository(db.loadoutDao())

        storeViewModel = ViewModelProvider(this, StoreViewModel.StoreFactory(categoryRepository, customizationRepository, loadoutRepository))[StoreViewModel::class.java]

        // spinnerのアダプター作成
        categoryAdapter = KeyValueArrayAdapter(requireContext(), R.layout.spinner_list_item, storeViewModel.getCategoryPairListLiveData().value ?: arrayListOf())
        customizationAdapter = KeyValueArrayAdapter(requireContext(), R.layout.spinner_list_item, storeViewModel.getCustomizationPairListLiveData().value ?: arrayListOf())

        // recyclerViewのアダプター作成
        loadoutAdapter = LoadoutRecyclerViewAdapter(storeViewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStoreBinding.inflate(inflater, container, false)
        binding.viewModel = storeViewModel
        binding.recyclerViewLoadouts.adapter = loadoutAdapter
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // スピナーを取得
        categorySpinner = view.findViewById(R.id.spinner_category)
        customizationSpinner = view.findViewById(R.id.spinner_weapon)

        // RecyclerViewを取得
        recyclerView = view.findViewById(R.id.recyclerView_loadouts)
        recyclerView.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(view.context)
        recyclerView.layoutManager = layoutManager

        // ギアセット未登録時に表示するViewを設定
        emptyView = view.findViewById(R.id.constrainLayout_emptyInfo)
        val inkMarkImageView = view.findViewById<ImageView?>(R.id.imageView_ink_mark)
        inkMarkImageView.setColorFilter(colorNum, PorterDuff.Mode.SRC_ATOP)

        // レイアウトを付与
        categoryAdapter.setDropDownViewResource(R.layout.spinner_list_dropdown_item)
        customizationAdapter.setDropDownViewResource(R.layout.spinner_list_dropdown_item)

        // Spinnerにアダプターを設定
        categorySpinner.adapter = categoryAdapter
        customizationSpinner.adapter = customizationAdapter

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
                    emptyView.visibility = View.VISIBLE
                }
                else{
                    emptyView.visibility = View.GONE
                }
            }
        }

        viewModel.getCategoryPairListLiveData().observe(viewLifecycleOwner, categoryObserver)
        viewModel.getCustomizationPairListLiveData().observe(viewLifecycleOwner, customizationObserver)
        viewModel.getLoadoutListLiveData().observe(viewLifecycleOwner, loadoutObserver)
    }
}