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
import com.splatool.ikastyle.model.data.repository.CustomizationNameRepository
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
    private lateinit var onClickDeleteListener: View.OnClickListener

    private lateinit var storeViewModel: StoreViewModel
    private lateinit var categoryAdapter : KeyValueArrayAdapter
    private lateinit var customizationAdapter : KeyValueArrayAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db : AppDatabase = AppDatabase.getDatabase(requireContext())
        val categoryRepository = MainCategoryRepository(db.mainCategoryDao())
        val customizationRepository = CustomizationNameRepository(db.customizationNameDao())

        storeViewModel = ViewModelProvider(this, StoreViewModel.StoreFactory(categoryRepository, customizationRepository))[StoreViewModel::class.java]

        categoryAdapter = KeyValueArrayAdapter(requireContext(), R.layout.spinner_list_item, storeViewModel.categoryPairListLiveData.value!!)
        customizationAdapter = KeyValueArrayAdapter(requireContext(), R.layout.spinner_list_item, storeViewModel.customizationPairListLiveData.value!!)


        // deleteボタンを押したときの処理
        onClickDeleteListener = View.OnClickListener { view ->
            // 確認ダイアログを表示
            AlertDialog.Builder(view.context)
                .setTitle(getString(R.string.dialogMessage_confirm))
                .setPositiveButton( // Yesを選んだ時
                    getString(R.string.buttonNavigation_yes)
                ) { dialogInterface, i ->

                    // データ削除
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


        // レイアウトを付与
        categoryAdapter.setDropDownViewResource(R.layout.spinner_list_dropdown_item)
        customizationAdapter.setDropDownViewResource(R.layout.spinner_list_dropdown_item)

        // Spinnerにアダプターを設定
        categorySpinner.adapter = categoryAdapter
        customizationSpinner.adapter = customizationAdapter

        // リスナーを作成
        val categoryListener = CategorySpinnerSelectedListener(view.context, customizationSpinner, storeViewModel.customizationPairListLiveData.value!!)
        val customizationListener = CustomizationSpinnerSelectedListener(recyclerView, emptyView, onClickDeleteListener)

        //リスナーを設定
        categorySpinner.onItemSelectedListener = categoryListener
        customizationSpinner.onItemSelectedListener = customizationListener

        observeViewModel(storeViewModel)
    }

    private fun observeViewModel(viewModel: StoreViewModel){
        val categoryObserver = Observer<ArrayList<Pair<Int,String>>>{
            it.let{
                categoryAdapter.clear()
                categoryAdapter.addAll(it)
                categoryAdapter.notifyDataSetChanged()
            }
        }

        val customizationObserver = Observer<ArrayList<Pair<Int, String>>>{
            it.let{
                customizationAdapter.clear()
                customizationAdapter.addAll(it)
                customizationAdapter.notifyDataSetChanged()
            }
        }

        viewModel.categoryPairListLiveData.observe(viewLifecycleOwner, categoryObserver)
        viewModel.customizationPairListLiveData.observe(viewLifecycleOwner, customizationObserver)
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