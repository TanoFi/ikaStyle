package com.splatool.ikastyle

import android.content.Context
import com.splatool.ikastyle.common.const.GearKind
import com.splatool.ikastyle.ui.GearDialogFragment
import com.splatool.ikastyle.ui.GearDialogFragment.GearDialogListener
import android.os.Bundle
import com.splatool.ikastyle.model.data.database.AppDatabase
import android.os.AsyncTask
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.splatool.ikastyle.model.data.entity.Loadout
import com.splatool.ikastyle.common.const.NumberPlace
import com.splatool.ikastyle.ui.GearPowerReceptorImageView
import android.widget.Spinner
import com.splatool.ikastyle.ui.KeyValueArrayAdapter
import com.splatool.ikastyle.model.data.entity.MainCategory
import com.splatool.ikastyle.model.data.databaseView.CustomizationMain
import android.widget.EditText
import com.splatool.ikastyle.ui.GearImageView
import android.widget.Toast
import com.splatool.ikastyle.ui.CategorySpinnerSelectedListener
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.splatool.ikastyle.common.Util
import com.splatool.ikastyle.databinding.FragmentNewBinding
import com.splatool.ikastyle.databinding.FragmentStoreBinding
import com.splatool.ikastyle.model.data.repository.CustomizationMainRepository
import com.splatool.ikastyle.model.data.repository.LoadoutRepository
import com.splatool.ikastyle.model.data.repository.MainCategoryRepository
import com.splatool.ikastyle.viewModel.NewViewModel
import androidx.lifecycle.Observer
import java.util.*
import java.util.function.Consumer
import java.util.stream.Collectors

class NewFragment : Fragment(), GearDialogListener {
    private lateinit var newViewModel : NewViewModel
    private lateinit var categoryAdapter: KeyValueArrayAdapter
    private lateinit var weaponAdapter : KeyValueArrayAdapter

    private lateinit var binding : FragmentNewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db : AppDatabase = AppDatabase.getDatabase(requireContext())
        val categoryRepository = MainCategoryRepository(db.mainCategoryDao())
        val customizationMainRepository = CustomizationMainRepository(db.customizationMainDao())
        val loadoutRepository = LoadoutRepository(db.loadoutDao())

        newViewModel = ViewModelProvider(this, NewViewModel.NewFactory(categoryRepository, customizationMainRepository, loadoutRepository))[NewViewModel::class.java]

        // spinnerのアダプター作成
        categoryAdapter = KeyValueArrayAdapter(requireContext(), R.layout.spinner_list_item, newViewModel.getCategoryPairListLiveData().value ?: arrayListOf())
        weaponAdapter = KeyValueArrayAdapter(requireContext(), R.layout.spinner_list_item, newViewModel.getWeaponPairListLiveData().value ?: arrayListOf())
        // レイアウトを付与
        categoryAdapter.setDropDownViewResource(R.layout.spinner_list_dropdown_item)
        weaponAdapter.setDropDownViewResource(R.layout.spinner_list_dropdown_item)

        // GearImageViewをクリックした時の処理を定義
        onClickGearImageView = View.OnClickListener { view ->
            val gearDialogFragment = GearDialogFragment((view as GearImageView).gearKind)
            gearDialogFragment.show(childFragmentManager, "gear_dialog")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewBinding.inflate(inflater, container, false)
        binding.viewModel = newViewModel
        binding.spinnerCategory.adapter = categoryAdapter
        binding.spinnerWeapon.adapter = weaponAdapter
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingActionButtonSave.setOnClickListener { x: View? ->
            if (checkUserInput()) { //入力チェック
                // DBに保存
                save()
            }
        }

        // GearImageViewにonClickListenerをまとめてセット
        setOnClickListener(binding.gearImageViewHead, binding.gearImageViewClothing, binding.gearImageViewShoes)

        observeViewModel(newViewModel)
    }

    private fun observeViewModel(viewModel: NewViewModel){
        val categoryObserver = Observer<ArrayList<Pair<Int, String>>>{
            it.let{
                categoryAdapter.resetKeyValues(it)
            }
        }

        val weaponObserver = Observer<ArrayList<Pair<Int, String>>>{
            it.let{
                weaponAdapter.resetKeyValues(it)
            }
        }

        viewModel.getCategoryPairListLiveData().observe(viewLifecycleOwner, categoryObserver)
        viewModel.getWeaponPairListLiveData().observe(viewLifecycleOwner, weaponObserver)
    }

    override fun onListItemClick(
        dialogFragment: GearDialogFragment,
        gearKind: GearKind,
        gearId: Int
    ) {
        when (gearKind) {
            GearKind.HEAD -> binding.gearImageViewHead.gearId = gearId
            GearKind.CLOTHING -> binding.gearImageViewClothing.gearId = gearId
            GearKind.SHOES -> binding.gearImageViewShoes.gearId = gearId
        }
    }

    /*
     * 入力チェック用のメソッド
     * 戻り値 true → 問題なし, false → 問題あり
     */
    private fun checkUserInput(): Boolean {
        // ブキSpinnerが未選択状態
        if ((binding.spinnerCategory.selectedItem as Pair<Int, String>).first == 0) {
            // Toastでメッセージ表示
            val toast = Toast.makeText(
                context,
                getString(R.string.toastMessage_spinnerNotSelected),
                Toast.LENGTH_LONG
            )
            toast.show()
            return false
        }

        // メインギアパワーが設定されていない
        if (binding.receptorImageViewHeadMain.gearPowerKind == 0 || binding.receptorImageViewClothingMain.gearPowerKind == 0 || binding.receptorImageViewShoesMain.gearPowerKind == 0) {
            // Toastでメッセージ表示
            val toast = Toast.makeText(
                context,
                getString(R.string.toastMessage_mainGearPowerNotSet),
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
    private fun save() {
        // 選択したブキの絶対ID
        val absoluteId: Int = (binding.spinnerWeapon.selectedItem as Pair<Int, String>).first

        // Insert用のLoadoutインスタンス作成
        val loadout = Loadout(
            0, // IDが自動生成されるので適当に0をInsert
            binding.editTextLoadoutName.text.toString(),
            Util.getCategoryId(absoluteId),
            Util.getMainId(absoluteId),
            Util.getCustomizationId(absoluteId),
            binding.gearImageViewHead.gearId,
            binding.receptorImageViewHeadMain.gearPowerKind,
            binding.receptorImageViewHeadSub1.gearPowerKind,
            binding.receptorImageViewHeadSub2.gearPowerKind,
            binding.receptorImageViewHeadSub3.gearPowerKind,
            binding.gearImageViewClothing.gearId,
            binding.receptorImageViewClothingMain.gearPowerKind,
            binding.receptorImageViewClothingSub1.gearPowerKind,
            binding.receptorImageViewClothingSub2.gearPowerKind,
            binding.receptorImageViewClothingSub3.gearPowerKind,
            binding.gearImageViewShoes.gearId,
            binding.receptorImageViewShoesMain.gearPowerKind,
            binding.receptorImageViewShoesSub1.gearPowerKind,
            binding.receptorImageViewShoesSub2.gearPowerKind,
            binding.receptorImageViewShoesSub3.gearPowerKind,
            System.currentTimeMillis()
        )
        val db: AppDatabase = AppDatabase.getDatabase(requireContext())
        val task = InsertLoadoutAsyncTask(db, loadout)
        task.execute()
    }

    /*
     * GearImageViewにonClickListenerをまとめてセットする
     */
    private fun setOnClickListener(vararg gearImageViews: GearImageView) {
        for (gearImageView in gearImageViews) {
            gearImageView.setOnClickListener(onClickGearImageView)
        }
    }

    // View初期化用メソッド
    private fun init() {
        // Spinner選択項目初期化
        binding.spinnerCategory.setSelection(0)
        binding.spinnerWeapon.setSelection(0)

        // TextView文字入力初期化
        binding.editTextLoadoutName.setText("")

        // GearImageView選択項目初期化
        binding.gearImageViewHead.init()
        binding.gearImageViewClothing.init()
        binding.gearImageViewShoes.init()

        // ReceptorImageView設定ギア初期化
        binding.receptorImageViewHeadMain.init()
        binding.receptorImageViewHeadSub1.init()
        binding.receptorImageViewHeadSub2.init()
        binding.receptorImageViewHeadSub3.init()
        binding.receptorImageViewClothingMain.init()
        binding.receptorImageViewClothingSub1.init()
        binding.receptorImageViewClothingSub2.init()
        binding.receptorImageViewClothingSub3.init()
        binding.receptorImageViewShoesMain.init()
        binding.receptorImageViewShoesSub1.init()
        binding.receptorImageViewShoesSub2.init()
        binding.receptorImageViewShoesSub3.init()
    }

    /*
     * 非同期でDBにLoadoutデータをInsert
     */
    private inner class InsertLoadoutAsyncTask(
        private val db: AppDatabase,
        private val loadout: Loadout
    ) : AsyncTask<Void?, Void?, Int?>() {
        override fun doInBackground(vararg params: Void?): Int {
            //実際にDBにアクセスし結果を取得
            val loadoutDao = db.loadoutDao()
            loadoutDao.insertLoadout(loadout)
            return 0
        }

        override fun onPostExecute(code: Int?) {
            // Viewを初期化
            init()

            // ToastでInsert完了のメッセージ表示
            val toast = Toast.makeText(
                context,
                getString(R.string.toastMessage_insertCompleted),
                Toast.LENGTH_LONG
            )
            toast.show()
        }
    }

//    /*
//     * 非同期でDBからデータ取得しスピナーにセットするクラス
//     */
//    private inner class GetDataAndSetSpinnerAsyncTask(
//        private val db: AppDatabase,
//        private val context: Context,
//        private val categorySpinner: Spinner,
//        private val weaponSpinner: Spinner
//    ) : AsyncTask<Void?, Void?, Int>() {
//        lateinit var categoryList: List<MainCategory>
//        lateinit var customizationMainList: List<CustomizationMain>
//        override fun doInBackground(vararg params: Void?): Int {
//            //端末の言語設定を取得
//            val languageCode = Util.getLanguageCode()
//
//            //実際にDBにアクセスし結果を取得
//            val categoryDao = db.mainCategoryDao()
//            val customizationMainDao = db.customizationMainDao()
//            categoryList = categoryDao.getMainCategoryList_nonSuspend(languageCode) //ブキカテゴリー名を取得
//            customizationMainList = customizationMainDao.getWeaponMainList(languageCode)
//            return 0
//        }
//
//        override fun onPostExecute(code: Int) {
//            // ひとつのメインウェポン : そのメインウェポンを持つブキセットのリスト でMapを作成
//            // 昇順でソートしたいのでTreeMap
//            val customizationMainMap = TreeMap(customizationMainList.stream().collect(
//                Collectors.groupingBy { x: CustomizationMain -> x.categoryId * NumberPlace.CATEGORY_PLACE + x.mainId * NumberPlace.MAIN_PLACE }
//            ))
//
//            // Spinnerに渡す用のリストを作成
//            val categoryKeyValueList = ArrayList<Pair<Int, String>>()
//            val mainAndCustomizationKeyValueList = ArrayList<Pair<Int, String>>()
//
//            // それぞれのリストの一番上に未選択時の項目を追加
//            categoryKeyValueList.add(
//                Pair(
//                    0,
//                    context.getString(R.string.spinnerItem_categoryUnselected)
//                )
//            )
//            mainAndCustomizationKeyValueList.add(
//                Pair(
//                    0,
//                    context.getString(R.string.spinnerItem_weaponUnselected)
//                )
//            )
//
//            //それぞれのリストにデータ(IDと名前のペア)を入れる
//            categoryList.forEach(Consumer { x: MainCategory ->
//                categoryKeyValueList.add(
//                    Pair(
//                        x.getAbsoluteId(),
//                        x.name
//                    )
//                )
//            })
//            for ((key, value) in customizationMainMap) {
//                mainAndCustomizationKeyValueList.add(
//                    Pair(
//                        key,
//                        value[0].mainName
//                    )
//                ) //メインウェポンのデータをAdd
//                value.forEach(Consumer { x: CustomizationMain ->
//                    mainAndCustomizationKeyValueList.add(
//                        Pair(x.getAbsoluteId(), x.weaponName)
//                    )
//                }) //ブキセットのデータをAdd
//            }
//
//            //アダプター作成
//            val categoryAdapter =
//                KeyValueArrayAdapter(context, R.layout.spinner_list_item, categoryKeyValueList)
//            val weaponAdapter = KeyValueArrayAdapter(
//                context,
//                R.layout.spinner_list_item,
//                mainAndCustomizationKeyValueList
//            )
//
//            //レイアウトを付与
//            categoryAdapter.setDropDownViewResource(R.layout.spinner_list_dropdown_item)
//            weaponAdapter.setDropDownViewResource(R.layout.spinner_list_dropdown_item)
//
//            //スピナーにアダプターを設定
//            categorySpinner.adapter = categoryAdapter
//            weaponSpinner.adapter = weaponAdapter
//
//            //リスナーを作成
//            val categoryListener = CategorySpinnerSelectedListener(
//                requireContext(), weaponSpinner, mainAndCustomizationKeyValueList
//            )
//
//            //リスナーを設定
//            categorySpinner.onItemSelectedListener = categoryListener
//        }
//    }

    companion object {
        // GearImageViewをクリックしたときの処理
        private var onClickGearImageView: View.OnClickListener? = null
    }
}