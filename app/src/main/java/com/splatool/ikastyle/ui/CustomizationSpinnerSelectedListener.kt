package com.splatool.ikastyle.ui

import androidx.recyclerview.widget.RecyclerView
import com.splatool.ikastyle.database.AppDatabase
import android.os.AsyncTask
import com.splatool.ikastyle.entity.Loadout
import android.widget.Spinner
import android.widget.AdapterView
import androidx.constraintlayout.widget.ConstraintLayout
import android.util.Pair
import android.view.*
import com.splatool.ikastyle.common.Util

class CustomizationSpinnerSelectedListener(
    private val recyclerView: RecyclerView,
    private val emptyView: ConstraintLayout,
    private val onClickDeleteListener: View.OnClickListener
) : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, i: Int, l: Long) {
        val spinner = adapterView as Spinner
        // Spinnerで選択したブキの絶対ID
        val absoluteId: Int = (spinner.selectedItem as Pair<Int, String>).first
        val db: AppDatabase = AppDatabase.getDatabase(spinner.context)
        val task = GetLoadoutListAsyncTask(db, absoluteId)
        task.execute()
    }

    override fun onNothingSelected(adapterView: AdapterView<*>?) {}

    /*
     * 選択したブキのギアセットリストをDBから非同期で取得
     */
    private inner class GetLoadoutListAsyncTask(
        private val db: AppDatabase,
        private val absoluteId: Int
    ) : AsyncTask<Void?, Void?, Int?>() {
        private lateinit var loadoutList: List<Loadout>
        override fun doInBackground(vararg params: Void?): Int {
            //実際にDBにアクセスし結果を取得
            val loadoutDao = db.loadoutDao()
            loadoutList = loadoutDao.getLoadoutList(Util.getCategoryId(absoluteId), Util.getMainId(absoluteId), Util.getCustomizationId(absoluteId))
            return 0
        }

        override fun onPostExecute(code: Int?) {
            val adapter = LoadoutRecyclerViewAdapter(loadoutList, onClickDeleteListener)
            recyclerView.adapter = adapter
            setEmptyViewVisibility(loadoutList.size)
        }

        // 表示するギアセットがあればEmptyViewは非表示、なければ表示
        private fun setEmptyViewVisibility(listSize : Int) {
            if (listSize == 0) {
                emptyView.visibility = View.VISIBLE
            } else {
                emptyView.visibility = View.GONE
            }
        }
    }
}