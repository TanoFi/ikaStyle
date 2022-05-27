package com.splatool.ikastyle.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.splatool.ikastyle.R
import com.splatool.ikastyle.model.common.const.GearKind
import com.splatool.ikastyle.databinding.FragmentNewBinding
import com.splatool.ikastyle.model.data.database.AppDatabase
import com.splatool.ikastyle.model.data.entity.Loadout
import com.splatool.ikastyle.model.data.repository.CustomizationMainRepository
import com.splatool.ikastyle.model.data.repository.LoadoutRepository
import com.splatool.ikastyle.model.data.repository.MainCategoryRepository
import com.splatool.ikastyle.view.GearDialogFragment.GearDialogListener
import com.splatool.ikastyle.view.customizedView.GearImageView
import com.splatool.ikastyle.view.customizedView.GearPowerReceptorImageView
import com.splatool.ikastyle.view.apapter.KeyValueArrayAdapter
import com.splatool.ikastyle.viewModel.NewViewModel

class NewFragment : Fragment(), GearDialogListener {
    private lateinit var newViewModel : NewViewModel
    private lateinit var categoryAdapter: KeyValueArrayAdapter
    private lateinit var weaponAdapter : KeyValueArrayAdapter
    private lateinit var loadout: Loadout

    private lateinit var binding : FragmentNewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db : AppDatabase = AppDatabase.getDatabase(requireContext())
        val categoryRepository = MainCategoryRepository(db.mainCategoryDao())
        val customizationMainRepository = CustomizationMainRepository(db.customizationMainDao())
        val loadoutRepository = LoadoutRepository(db.loadoutDao())

        newViewModel = ViewModelProvider(this, NewViewModel.NewFactory(categoryRepository, customizationMainRepository, loadoutRepository))[NewViewModel::class.java]

        // spinnerのアダプター作成
        categoryAdapter = KeyValueArrayAdapter(requireContext(),
            R.layout.spinner_list_item, newViewModel.getCategoryPairListLiveData().value ?: arrayListOf())
        weaponAdapter = KeyValueArrayAdapter(requireContext(),
            R.layout.spinner_list_item, newViewModel.getWeaponPairListLiveData().value ?: arrayListOf())

        // レイアウトを付与
        categoryAdapter.setDropDownViewResource(R.layout.spinner_list_dropdown_item)
        weaponAdapter.setDropDownViewResource(R.layout.spinner_list_dropdown_item)

        loadout = newViewModel.getLoadoutLiveData().value ?: Loadout()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewBinding.inflate(inflater, container, false)
        binding.viewModel = newViewModel
        binding.loadout = loadout
        binding.spinnerCategory.adapter = categoryAdapter
        binding.spinnerWeapon.adapter = weaponAdapter
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // GearImageViewにonClickListenerをまとめてセット
        setOnClickListener(binding.gearImageViewHead, binding.gearImageViewClothing, binding.gearImageViewShoes)

        // ReceptorImageViewにonDragListenerをまとめてセット
        setOnDragListener(binding.receptorImageViewHeadMain, binding.receptorImageViewHeadSub1, binding.receptorImageViewHeadSub2, binding.receptorImageViewHeadSub3,
            binding.receptorImageViewClothingMain, binding.receptorImageViewClothingSub1, binding.receptorImageViewClothingSub2, binding.receptorImageViewClothingSub3,
            binding.receptorImageViewShoesMain, binding.receptorImageViewShoesSub1, binding.receptorImageViewShoesSub2, binding.receptorImageViewShoesSub3)

        // editTextLoadoutNameに入力値変更時用のListenerをセット
        binding.editTextLoadoutName.addTextChangedListener(newViewModel.loadoutNameTextWatcher)

        observeViewModel(newViewModel)
    }

    private fun observeViewModel(viewModel: NewViewModel){
        val categoryObserver = Observer<ArrayList<Pair<Int, String>>>{
            it.let{
                it.add(0, Pair(0, requireContext().getString(R.string.spinnerItem_categoryUnselected)))
                categoryAdapter.resetKeyValues(it)
            }
        }

        val weaponObserver = Observer<ArrayList<Pair<Int, String>>>{
            it.let{
                it.add(0, Pair(0, requireContext().getString(R.string.spinnerItem_weaponUnselected)))
                weaponAdapter.resetKeyValues(it)
            }
        }

        val loadoutObserver = Observer<Loadout> {
            it.let{
                binding.editTextLoadoutName.setText(it.name)
                binding.gearImageViewHead.setImageResource(it.headGearResourceId)
                binding.receptorImageViewHeadMain.setImageResource(it.headMainResourceId)
                binding.receptorImageViewHeadSub1.setImageResource(it.headSub1ResourceId)
                binding.receptorImageViewHeadSub2.setImageResource(it.headSub2ResourceId)
                binding.receptorImageViewHeadSub3.setImageResource(it.headSub3ResourceId)
                binding.gearImageViewClothing.setImageResource(it.clothingGearResourceId)
                binding.receptorImageViewClothingMain.setImageResource(it.clothingMainResourceId)
                binding.receptorImageViewClothingSub1.setImageResource(it.clothingSub1ResourceId)
                binding.receptorImageViewClothingSub2.setImageResource(it.clothingSub2ResourceId)
                binding.receptorImageViewClothingSub3.setImageResource(it.clothingSub3ResourceId)
                binding.gearImageViewShoes.setImageResource(it.shoesGearResourceId)
                binding.receptorImageViewShoesMain.setImageResource(it.shoesMainResourceId)
                binding.receptorImageViewShoesSub1.setImageResource(it.shoesSub1ResourceId)
                binding.receptorImageViewShoesSub2.setImageResource(it.shoesSub2ResourceId)
                binding.receptorImageViewShoesSub3.setImageResource(it.shoesSub3ResourceId)
            }
        }

        viewModel.getCategoryPairListLiveData().observe(viewLifecycleOwner, categoryObserver)
        viewModel.getWeaponPairListLiveData().observe(viewLifecycleOwner, weaponObserver)
        viewModel.getLoadoutLiveData().observe(viewLifecycleOwner, loadoutObserver)
    }

    override fun onListItemClick(dialogFragment: GearDialogFragment, gearKind: GearKind, gearId: Int) {
        newViewModel.onPostDialogItemClicked(gearKind, gearId)
    }

    /*
     * GearImageViewにonClickListenerをまとめてセットする
     */
    private fun setOnClickListener(vararg gearImageViews: GearImageView) {
        for (gearImageView in gearImageViews) {
            gearImageView.setOnClickListener { view ->
                val gearDialogFragment = GearDialogFragment((view as GearImageView).gearKind)
                gearDialogFragment.show(childFragmentManager, "gear_dialog")
            }
        }
    }

    private fun setOnDragListener(vararg receptorImageViews: GearPowerReceptorImageView){
        for(receptorImageView in receptorImageViews){
            receptorImageView.setOnDragListener(newViewModel.onGearPowerDragListener)
        }
    }
}