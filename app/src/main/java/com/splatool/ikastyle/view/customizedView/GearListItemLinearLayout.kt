package com.splatool.ikastyle.view.customizedView

import android.content.Context
import com.splatool.ikastyle.model.common.const.GearKind
import android.widget.LinearLayout
import android.util.AttributeSet

/*
 * GearIdとGearKindを管理できるLinearLayoutクラス
 * ギアリストを表示するダイアログ上で使用
 */
class GearListItemLinearLayout(context: Context?, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    var gearId = 0
    var gearKind: GearKind? = null
}