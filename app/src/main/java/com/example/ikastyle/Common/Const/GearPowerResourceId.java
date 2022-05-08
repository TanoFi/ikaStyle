package com.example.ikastyle.Common.Const;

import com.example.ikastyle.R;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/*
 * ギアパワー画像のResourceId用の定数クラス
 */
public class GearPowerResourceId {
    // 後から変更できないように一応Collections.unmodifiableMap()を使用
    public static final Map<Integer, Integer> gearPowerResourceIdMap = Collections.unmodifiableMap(
            new HashMap<Integer, Integer>(){
                {
                    put(0, R.drawable.not_set); //未設定
                    put(1, R.drawable.ink_saver_main); //メイン効率
                    put(2, R.drawable.ink_saver_sub); //サブ効率
                    put(3, R.drawable.ink_recovery_up); //インク回復
                    put(4, R.drawable.run_speed_up); //ヒト速
                    put(5, R.drawable.swim_speed_up); //イカ速
                    put(6, R.drawable.special_charge_up); //スペ増
                    put(7, R.drawable.special_saver); //スペ減
                    put(8, R.drawable.special_power_up); //スペ強
                    put(9, R.drawable.quick_respawn); //復活短縮
                    put(10, R.drawable.quick_super_jump); //ジャンプ短縮
                    put(11, R.drawable.sub_power_up); //サブ性能
                    put(12, R.drawable.ink_resistance_up); //安全靴
                    put(13, R.drawable.bomb_defence_up_dx); //爆風軽減
                    put(14, R.drawable.main_power_up); //メイン性能
                    put(100, R.drawable.opening_gambit); //スタートダッシュ
                    put(101, R.drawable.last_ditch_effort); //ラストスパート
                    put(102, R.drawable.tenacity); //逆境
                    put(103, R.drawable.comeback); //カムバック
                    put(200, R.drawable.ninja_squid); //イカ忍者
                    put(201, R.drawable.haunt); //リベンジ
                    put(202, R.drawable.thermal_ink); //サーマルインク
                    put(203, R.drawable.respawn_punisher); //ペナアップ
                    put(204, R.drawable.ability_doubler); //サブギア倍化
                    put(300, R.drawable.stealth_jump); //ステルスジャンプ
                    put(301, R.drawable.object_shredder); //対物攻撃力アップ
                    put(302, R.drawable.drop_roller); //受け身術
                }
            }
    );
}
