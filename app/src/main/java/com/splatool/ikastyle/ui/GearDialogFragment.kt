package com.splatool.ikastyle.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import com.splatool.ikastyle.common.const.GearKind
import com.splatool.ikastyle.R
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.splatool.ikastyle.database.AppDatabase
import android.os.AsyncTask
import androidx.fragment.app.DialogFragment
import com.splatool.ikastyle.common.Util
import java.lang.ClassCastException
import java.lang.NullPointerException

/*
 * ギア一覧を表示するダイアログ
 */
class GearDialogFragment(private val gearKind: GearKind?) : DialogFragment() {
    private lateinit var recyclerView: RecyclerView

    // このinterfaceを呼び出し元フラグメントで実装することで呼び出し元フラグメントの更新を行う
    interface GearDialogListener {
        fun onListItemClick(
            dialogFragment: GearDialogFragment,
            gearKind: GearKind,
            gearId: Int
        )
    }

    private lateinit var listener: GearDialogListener
    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = when (parentFragment) {
            null -> {
                throw NullPointerException("GearDialogFragmentの親フラグメントが見つかりません")
            }
            !is GearDialogListener -> {
                throw ClassCastException(parentFragment.toString() + "はGearDialogListenerを実装していません")
            }
            else -> {
                parentFragment as GearDialogListener
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)

        // fragment_gear_dialog.xmlのレイアウトをViewとしてインスタンス化
        val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val content = inflater.inflate(R.layout.fragment_gear_dialog, null)

        // fragment_gear_dialog.xml上のRecyclerViewを取得しレイアウト設定
        recyclerView = content.findViewById(R.id.recyclerView_gear)
        recyclerView.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(content.context)
        recyclerView.layoutManager = layoutManager

        // DBからギアデータのリストを取得しRecyclerViewに表示
        val db: AppDatabase = AppDatabase.getDatabase(requireContext())
        val task = GetGearListAsyncTask(db, gearKind!!)
        task.execute()

        // ダイアログにキャンセルボタン設置
        builder.setNegativeButton(getString(R.string.buttonNavigation_cancel), null)

        // ダイアログにViewを設定
        builder.setView(content)
        return builder.create()
    }

    /*
     * ギアリストをDBから非同期で取得
     */
    private inner class GetGearListAsyncTask(
        private val db: AppDatabase,
        private val gearKind: GearKind
    ) : AsyncTask<Void?, Void?, Int>() {
        private val languageCode = Util.getLanguageCode()
        private lateinit var gearList: List<*>
        override fun doInBackground(vararg params: Void?): Int {
            //実際にDBにアクセスし結果を取得
            gearList = when (gearKind) {
                GearKind.HEAD -> {
                    val headGearDao = db.headGearDao()
                    headGearDao.getHeadGearList(languageCode)
                }
                GearKind.CLOTHING -> {
                    val clothingGearDao = db.clothingGearDao()
                    clothingGearDao.getClothingGearList(languageCode)
                }
                GearKind.SHOES -> {
                    val shoesGearDao = db.shoesGearDao()
                    shoesGearDao.getShoesGearList(languageCode)
                }
            }
            return 0
        }

        override fun onPostExecute(code: Int) {
            val adapter = GearRecyclerViewAdapter(gearList)
            recyclerView.adapter = adapter
            // ギアリストのItemを選択した時の処理
            adapter.setOnItemClickListener { view ->
                val gearId = (view as GearListItemLinearLayout).gearId
                val gearKind = (view).gearKind
                // 元Fragment(NewFragment)にコールバック
                listener.onListItemClick(this@GearDialogFragment, gearKind!!, gearId)
                // ダイアログを消す
                dismiss()
            }
        }
    }
}