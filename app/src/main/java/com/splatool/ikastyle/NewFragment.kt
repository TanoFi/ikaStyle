package com.splatool.ikastyle

import android.content.Context
import com.splatool.ikastyle.common.const.GearKind
import com.splatool.ikastyle.ui.GearDialogFragment
import com.splatool.ikastyle.ui.GearDialogFragment.GearDialogListener
import android.os.Bundle
import com.splatool.ikastyle.database.AppDatabase
import android.os.AsyncTask
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.splatool.ikastyle.entity.Loadout
import com.splatool.ikastyle.common.const.NumberPlace
import com.splatool.ikastyle.ui.GearPowerReceptorImageView
import android.widget.Spinner
import com.splatool.ikastyle.ui.KeyValueArrayAdapter
import com.splatool.ikastyle.entity.MainCategory
import com.splatool.ikastyle.databaseView.CustomizationMain
import android.widget.EditText
import com.splatool.ikastyle.ui.GearImageView
import android.widget.Toast
import com.splatool.ikastyle.ui.CategorySpinnerSelectedListener
import android.util.Pair
import android.view.*
import androidx.fragment.app.Fragment
import com.splatool.ikastyle.common.Util
import java.util.*
import java.util.function.Consumer
import java.util.stream.Collectors

class NewFragment : Fragment(), GearDialogListener {
    private lateinit var categorySpinner: Spinner
    private lateinit var weaponSpinner: Spinner
    private lateinit var loadoutName: EditText
    private lateinit var headGear: GearImageView
    private lateinit var headMain: GearPowerReceptorImageView
    private lateinit var headSub1: GearPowerReceptorImageView
    private lateinit var headSub2: GearPowerReceptorImageView
    private lateinit var headSub3: GearPowerReceptorImageView
    private lateinit var clothingGear: GearImageView
    private lateinit var clothingMain: GearPowerReceptorImageView
    private lateinit var clothingSub1: GearPowerReceptorImageView
    private lateinit var clothingSub2: GearPowerReceptorImageView
    private lateinit var clothingSub3: GearPowerReceptorImageView
    private lateinit var shoesGear: GearImageView
    private lateinit var shoesMain: GearPowerReceptorImageView
    private lateinit var shoesSub1: GearPowerReceptorImageView
    private lateinit var shoesSub2: GearPowerReceptorImageView
    private lateinit var shoesSub3: GearPowerReceptorImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // GearImageViewをクリックした時の処理を定義
        onClickGearImageView = View.OnClickListener { view ->
            val gearDialogFragment = GearDialogFragment((view as GearImageView).gearKind)
            gearDialogFragment.show(childFragmentManager, "gear_dialog")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Spinnerの画面要素を取得
        categorySpinner = view.findViewById(R.id.spinner_category)
        weaponSpinner = view.findViewById(R.id.spinner_weapon)

        // textViewの画面要素を取得
        loadoutName = view.findViewById(R.id.editText_loadoutName)

        // GearPowerReceptorImageViewの画面要素を取得
        headMain = view.findViewById(R.id.receptorImageView_head_main)
        headSub1 = view.findViewById(R.id.receptorImageView_head_sub1)
        headSub2 = view.findViewById(R.id.receptorImageView_head_sub2)
        headSub3 = view.findViewById(R.id.receptorImageView_head_sub3)
        clothingMain = view.findViewById(R.id.receptorImageView_clothing_main)
        clothingSub1 = view.findViewById(R.id.receptorImageView_clothing_sub1)
        clothingSub2 = view.findViewById(R.id.receptorImageView_clothing_sub2)
        clothingSub3 = view.findViewById(R.id.receptorImageView_clothing_sub3)
        shoesMain = view.findViewById(R.id.receptorImageView_shoes_main)
        shoesSub1 = view.findViewById(R.id.receptorImageView_shoes_sub1)
        shoesSub2 = view.findViewById(R.id.receptorImageView_shoes_sub2)
        shoesSub3 = view.findViewById(R.id.receptorImageView_shoes_sub3)

        // Spinnerの項目に設定するためのDBを取得
        val db: AppDatabase = AppDatabase.getDatabase(view.context)
        // DBからデータを取得しスピナーにセット
        val task = GetDataAndSetSpinnerAsyncTask(db, view.context, categorySpinner, weaponSpinner)
        task.execute()
        val saveButton: FloatingActionButton = view.findViewById(R.id.floatingActionButton_save)
        saveButton.setOnClickListener { x: View? ->
            if (checkUserInput()) { //入力チェック
                // DBに保存
                save()
            }
        }

        // GearImageViewの画面要素を取得
        headGear = view.findViewById(R.id.gearImageView_head)
        clothingGear = view.findViewById(R.id.gearImageView_clothing)
        shoesGear = view.findViewById(R.id.gearImageView_shoes)

        // GearImageViewにonClickListenerをまとめてセット
        setOnClickListener(headGear, clothingGear, shoesGear)
    }

    override fun onListItemClick(
        dialogFragment: GearDialogFragment,
        gearKind: GearKind,
        gearId: Int
    ) {
        when (gearKind) {
            GearKind.HEAD -> headGear.gearId = gearId
            GearKind.CLOTHING -> clothingGear.gearId = gearId
            GearKind.SHOES -> shoesGear.gearId = gearId
        }
    }

    /*
     * 入力チェック用のメソッド
     * 戻り値 true → 問題なし, false → 問題あり
     */
    private fun checkUserInput(): Boolean {
        // ブキSpinnerが未選択状態
        if ((weaponSpinner.selectedItem as Pair<Int, String>).first == 0) {
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
        if (headMain.gearPowerKind == 0 || clothingMain.gearPowerKind == 0 || shoesMain.gearPowerKind == 0) {
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
        val absoluteId: Int = (weaponSpinner.selectedItem as Pair<Int, String>).first

        // Insert用のLoadoutインスタンス作成
        val loadout = Loadout(
            0, // IDが自動生成されるので適当に0をInsert
            loadoutName.text.toString(),
            Util.getCategoryId(absoluteId),
            Util.getMainId(absoluteId),
            Util.getCustomizationId(absoluteId),
            headGear.gearId,
            headMain.gearPowerKind,
            headSub1.gearPowerKind,
            headSub2.gearPowerKind,
            headSub3.gearPowerKind,
            clothingGear.gearId,
            clothingMain.gearPowerKind,
            clothingSub1.gearPowerKind,
            clothingSub2.gearPowerKind,
            clothingSub3.gearPowerKind,
            shoesGear.gearId,
            shoesMain.gearPowerKind,
            shoesSub1.gearPowerKind,
            shoesSub2.gearPowerKind,
            shoesSub3.gearPowerKind,
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
        categorySpinner.setSelection(0)
        weaponSpinner.setSelection(0)

        // TextView文字入力初期化
        loadoutName.setText("")

        // GearImageView選択項目初期化
        headGear.init()
        clothingGear.init()
        shoesGear.init()

        // ReceptorImageView設定ギア初期化
        headMain.init()
        headSub1.init()
        headSub2.init()
        headSub3.init()
        clothingMain.init()
        clothingSub1.init()
        clothingSub2.init()
        clothingSub3.init()
        shoesMain.init()
        shoesSub1.init()
        shoesSub2.init()
        shoesSub3.init()
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

    /*
     * 非同期でDBからデータ取得しスピナーにセットするクラス
     */
    private inner class GetDataAndSetSpinnerAsyncTask(
        private val db: AppDatabase,
        private val context: Context,
        private val categorySpinner: Spinner,
        private val weaponSpinner: Spinner
    ) : AsyncTask<Void?, Void?, Int>() {
        lateinit var categoryList: List<MainCategory>
        lateinit var customizationMainList: List<CustomizationMain>
        override fun doInBackground(vararg params: Void?): Int {
            //端末の言語設定を取得
            val languageCode = Util.getLanguageCode()

            //実際にDBにアクセスし結果を取得
            val categoryDao = db.mainCategoryDao()
            val customizationMainDao = db.customizationMainDao()
            categoryList = categoryDao.getMainCategoryList(languageCode) //ブキカテゴリー名を取得
            customizationMainList = customizationMainDao.getWeaponMainList(languageCode)
            return 0
        }

        override fun onPostExecute(code: Int) {
            // ひとつのメインウェポン : そのメインウェポンを持つブキセットのリスト でMapを作成
            // 昇順でソートしたいのでTreeMap
            val customizationMainMap = TreeMap(customizationMainList.stream().collect(
                Collectors.groupingBy { x: CustomizationMain -> x.categoryId * NumberPlace.CATEGORY_PLACE + x.mainId * NumberPlace.MAIN_PLACE }
            ))

            // Spinnerに渡す用のリストを作成
            val categoryKeyValueList = ArrayList<Pair<Int, String>>()
            val mainAndCustomizationKeyValueList = ArrayList<Pair<Int, String>>()

            // それぞれのリストの一番上に未選択時の項目を追加
            categoryKeyValueList.add(
                Pair(
                    0,
                    context.getString(R.string.spinnerItem_categoryUnselected)
                )
            )
            mainAndCustomizationKeyValueList.add(
                Pair(
                    0,
                    context.getString(R.string.spinnerItem_weaponUnselected)
                )
            )

            //それぞれのリストにデータ(IDと名前のペア)を入れる
            categoryList.forEach(Consumer { x: MainCategory ->
                categoryKeyValueList.add(
                    Pair(
                        x.getAbsoluteId(),
                        x.name
                    )
                )
            })
            for ((key, value) in customizationMainMap) {
                mainAndCustomizationKeyValueList.add(
                    Pair(
                        key,
                        value[0].mainName
                    )
                ) //メインウェポンのデータをAdd
                value.forEach(Consumer { x: CustomizationMain ->
                    mainAndCustomizationKeyValueList.add(
                        Pair(x.getAbsoluteId(), x.weaponName)
                    )
                }) //ブキセットのデータをAdd
            }

            //アダプター作成
            val categoryAdapter =
                KeyValueArrayAdapter(context, R.layout.spinner_list_item, categoryKeyValueList)
            val weaponAdapter = KeyValueArrayAdapter(
                context,
                R.layout.spinner_list_item,
                mainAndCustomizationKeyValueList
            )

            //レイアウトを付与
            categoryAdapter.setDropDownViewResource(R.layout.spinner_list_dropdown_item)
            weaponAdapter.setDropDownViewResource(R.layout.spinner_list_dropdown_item)

            //スピナーにアダプターを設定
            categorySpinner.adapter = categoryAdapter
            weaponSpinner.adapter = weaponAdapter

            //リスナーを作成
            val categoryListener = CategorySpinnerSelectedListener(
                requireContext(), weaponSpinner, mainAndCustomizationKeyValueList
            )

            //リスナーを設定
            categorySpinner.onItemSelectedListener = categoryListener
        }
    }

    companion object {
        // GearImageViewをクリックしたときの処理
        private var onClickGearImageView: View.OnClickListener? = null
    }
}