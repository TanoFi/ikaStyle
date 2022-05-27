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
import com.splatool.ikastyle.model.data.database.AppDatabase
import android.os.AsyncTask
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.splatool.ikastyle.common.Util
import com.splatool.ikastyle.databinding.FragmentGearDialogBinding
import com.splatool.ikastyle.model.data.entity.ClothingGear
import com.splatool.ikastyle.model.data.entity.HeadGear
import com.splatool.ikastyle.model.data.entity.Loadout
import com.splatool.ikastyle.model.data.entity.ShoesGear
import com.splatool.ikastyle.model.data.repository.GearRepository
import com.splatool.ikastyle.model.data.repository.MainCategoryRepository
import com.splatool.ikastyle.viewModel.GearDialogViewModel
import com.splatool.ikastyle.viewModel.NewViewModel
import com.splatool.ikastyle.viewModel.StoreViewModel
import java.lang.ClassCastException
import java.lang.NullPointerException
import java.util.ArrayList

/*
 * ギア一覧を表示するダイアログ
 */
class GearDialogFragment(private val gearKind: GearKind) : DialogFragment() {
    lateinit var gearAdapter: GearRecyclerViewAdapter
    lateinit var binding : FragmentGearDialogBinding

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

        val db : AppDatabase = AppDatabase.getDatabase(requireContext())
        val gearRepository = GearRepository(db.headGearDao(), db.clothingGearDao(), db.shoesGearDao())

        val gearViewModel = ViewModelProvider(this, GearDialogViewModel.GearFactory(gearRepository, gearKind))[GearDialogViewModel::class.java]

        // fragment_gear_dialog.xmlのレイアウトをViewとしてインスタンス化
        val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val content = inflater.inflate(R.layout.fragment_gear_dialog, null)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gear_dialog, null, false)

        gearAdapter = GearRecyclerViewAdapter(gearViewModel)

        val manager: RecyclerView.LayoutManager = LinearLayoutManager(content.context)
        binding.recyclerViewGear.apply {
            setHasFixedSize(true)
            layoutManager = manager
            adapter = gearAdapter
        }

        // ダイアログにキャンセルボタン設置
        builder.setNegativeButton(getString(R.string.buttonNavigation_cancel), null)

        // ダイアログにViewを設定
        builder.setView(binding.root)

        observeViewModel(gearViewModel)
        return builder.create()
    }

    private fun observeViewModel(viewModel: GearDialogViewModel){
        val gearListObserver = Observer<ArrayList<*>> {
            it.let{
                gearAdapter.setGearList(it)
            }
        }

        val selectedObserver = Observer<Int?>{
            it.let{
                listener.onListItemClick(this@GearDialogFragment, gearKind, it)
                dismiss()
            }
        }

        viewModel.getGearListLiveData().observe(this, gearListObserver)
        viewModel.getSelectedGearIdLiveData().observe(this, selectedObserver)
    }
}