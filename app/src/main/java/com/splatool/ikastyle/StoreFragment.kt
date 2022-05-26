package com.splatool.ikastyle

import android.app.AlertDialog
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.splatool.ikastyle.database.AppDatabase
import android.os.AsyncTask
import com.splatool.ikastyle.entity.Loadout
import com.splatool.ikastyle.ui.LoadoutDeleteButton
import com.splatool.ikastyle.ui.KeyValueArrayAdapter
import androidx.constraintlayout.widget.ConstraintLayout
import com.splatool.ikastyle.ui.LoadoutRecyclerViewAdapter
import com.splatool.ikastyle.entity.MainCategory
import com.splatool.ikastyle.entity.CustomizationName
import com.splatool.ikastyle.ui.CategorySpinnerSelectedListener
import android.graphics.PorterDuff
import android.util.Pair
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import com.splatool.ikastyle.common.Util
import com.splatool.ikastyle.ui.CustomizationSpinnerSelectedListener
import java.util.ArrayList
import java.util.function.Consumer

class StoreFragment : Fragment() {
    private lateinit var categorySpinner: Spinner
    private lateinit var customizationSpinner: Spinner
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyView: ConstraintLayout
    private val colorNum = Util.getRandomColor()
    private lateinit var onClickDeleteListener: View.OnClickListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // deleteボタンを押したときの処理
        onClickDeleteListener = View.OnClickListener { view ->
            // 確認ダイアログを表示
            AlertDialog.Builder(view.context)
                .setTitle(getString(R.string.dialogMessage_confirm))
                .setPositiveButton( // Yesを選んだ時
                    getString(R.string.buttonNavigation_yes)
                ) { dialogInterface, i ->

                    // データ削除
                    val db: AppDatabase = AppDatabase.getDatabase(requireContext())
                    val loadout = (view as LoadoutDeleteButton).loadout!!
                    val task = DeleteLoadoutAsyncTask(db, loadout)
                    task.execute()
                }
                .setNegativeButton( // Noを選んだ時
                    getString(R.string.buttonNavigation_no),  // 何もしない
                    null
                )
                .show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store, container, false)
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

        // Spinnerの項目に設定するためのDBを取得
        val db: AppDatabase = AppDatabase.getDatabase(view.context)
        // SpinnerDataGetAsyncTaskクラス内でContextを取得できなかったためにonPostExecute()だけインスタンス作成時に記述
        val task = GetDataAndSetSpinnerAsyncTask(db, view.context)
        task.execute()
    }

    /*
     * 非同期でDBからデータ取得しスピナーにセットするクラス
     */
    private inner class GetDataAndSetSpinnerAsyncTask(
        private val db: AppDatabase,
        private val context: Context
    ) : AsyncTask<Void?, Void?, Int?>() {
        lateinit var categoryList: List<MainCategory>
        lateinit var customizationNameList: List<CustomizationName>
        override fun doInBackground(vararg params: Void?): Int {
            //端末の言語設定を取得
            val languageCode = Util.getLanguageCode()

            //実際にDBにアクセスし結果を取得
            val categoryDao = db.mainCategoryDao()
            val customizationNameDao = db.customizationNameDao()
            categoryList = categoryDao.getMainCategoryList(languageCode) //ブキカテゴリー名を取得
            customizationNameList = customizationNameDao.getWeaponNameList(languageCode)
            return 0
        }

        override fun onPostExecute(code: Int?) {
            // Spinnerに渡す用のリストを作成
            val categoryKeyValueList = ArrayList<Pair<Int, String>>()
            val customizationKeyValueList = ArrayList<Pair<Int, String>>()

            // それぞれのリストの一番上に未選択時の項目を追加
            categoryKeyValueList.add(
                Pair(
                    0,
                    context.getString(R.string.spinnerItem_categoryUnselected)
                )
            )
            customizationKeyValueList.add(
                Pair(
                    0,
                    context.getString(R.string.spinnerItem_weaponUnselected)
                )
            )

            //それぞれのリストにデータ(IDと名前のペア)を入れる
            categoryList.forEach(Consumer { x: MainCategory ->
                categoryKeyValueList.add(
                    Pair(
                        x.getAbsoluteId(), x.name)
                )
            })
            customizationNameList.forEach(Consumer { x: CustomizationName ->
                customizationKeyValueList.add(
                    Pair(x.getAbsoluteId(), x.name)
                )
            })

            //アダプター作成
            val categoryAdapter =
                KeyValueArrayAdapter(context, R.layout.spinner_list_item, categoryKeyValueList)
            val customizationAdapter =
                KeyValueArrayAdapter(context, R.layout.spinner_list_item, customizationKeyValueList)

            //レイアウトを付与
            categoryAdapter.setDropDownViewResource(R.layout.spinner_list_dropdown_item)
            customizationAdapter.setDropDownViewResource(R.layout.spinner_list_dropdown_item)

            //スピナーにアダプターを設定
            categorySpinner.adapter = categoryAdapter
            customizationSpinner.adapter = customizationAdapter

            //リスナーを作成
            val categoryListener = CategorySpinnerSelectedListener(
                context, customizationSpinner, customizationKeyValueList
            )
            val customizationListener =
                CustomizationSpinnerSelectedListener(recyclerView, emptyView, onClickDeleteListener)

            //リスナーを設定
            categorySpinner.onItemSelectedListener = categoryListener
            customizationSpinner.onItemSelectedListener = customizationListener
        }
    }

    /*
     * 非同期でTRAN_GEAR_SETのレコードを削除する
     */
    private inner class DeleteLoadoutAsyncTask(
        private val db: AppDatabase,
        private val loadout: Loadout
    ) : AsyncTask<Void?, Void?, Int?>() {
        private lateinit var newLoadout: List<Loadout>
        override fun doInBackground(vararg params: Void?): Int {
            //実際にDBにアクセスしレコードを削除
            val loadoutDao = db.loadoutDao()
            loadoutDao.deleteLoadout(loadout)

            // 削除後のギアセットリストを取得し直す
            val selectedWeaponId: Int =
                (customizationSpinner.selectedItem as Pair<Int, String>).first
            newLoadout = loadoutDao.getLoadoutList(
                Util.getCategoryId(selectedWeaponId),
                Util.getMainId(selectedWeaponId),
                Util.getCustomizationId(selectedWeaponId)
            )
            return 0
        }

        override fun onPostExecute(integer: Int?) {
            // 選択ギアセット削除後のギアセットリストをrecycleViewにセットし直す
            val newAdapter = LoadoutRecyclerViewAdapter(newLoadout, onClickDeleteListener)
            recyclerView.adapter = newAdapter
            setEmptyViewVisibility(newLoadout.size)
        }

        // 表示するギアセットがあればEmptyViewは非表示、なければ表示
        private fun setEmptyViewVisibility(listSize: Int) {
            if (listSize == 0) {
                emptyView.visibility = View.VISIBLE
            } else {
                emptyView.visibility = View.GONE
            }
        }
    }
}