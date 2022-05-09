package com.example.ikastyle.Common.Const;

import com.example.ikastyle.R;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/*
 * ユニークなIDとそれに対応する画像のResourceIdのマップ
 */
public class ResourceIdMap {
    // ギアパワー画像
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

    // アタマのギア画像
    public static final Map<Integer,Integer> headGearResourceIdMap = Collections.unmodifiableMap(
            new HashMap<Integer, Integer>(){
                {
                    put(0, R.drawable.headgear0);
                    put(1, R.drawable.headgear1_18k_aviators);
                    put(2, R.drawable.headgear2_anglerfish_mask);
                    put(3, R.drawable.headgear3_annaki_beret_and_glasses);
                    put(4, R.drawable.headgear4_annaki_beret);
                    put(5, R.drawable.headgear5_annaki_mask);
                    put(6, R.drawable.headgear6_armor_helmet_replica);
                    put(7, R.drawable.headgear7_b_ball_headband);
                    put(8, R.drawable.headgear8_backwards_cap);
                    put(9, R.drawable.headgear9_bamboo_hat);
                    put(10, R.drawable.headgear10_beekeeper_hat);
                    put(11, R.drawable.headgear11_bike_helmet);
                    put(12, R.drawable.headgear12_black_arrowbands);
                    put(13, R.drawable.headgear13_black_fishfry_bandana);
                    put(14, R.drawable.headgear14_blowfish_bell_hat);
                    put(15, R.drawable.headgear15_blowfish_newsie);
                    put(16, R.drawable.headgear16_bobble_hat);
                    put(17, R.drawable.headgear17_bucket_hat);
                    put(18, R.drawable.headgear1_18k_aviators);
                    put(19, R.drawable.headgear19_camping_hat);
                    put(20, R.drawable.headgear20_cap_of_legend);
                    put(21, R.drawable.headgear21_classic_straw_boater);
                    put(22, R.drawable.headgear22_conductor_cap);
                    put(23, R.drawable.headgear23_cycle_king_cap);
                    put(24, R.drawable.headgear24_cycling_cap);
                    put(25, R.drawable.headgear25_deca_tackle_visor_helmet);
                    put(26, R.drawable.headgear26_designer_headphones);
                    put(27, R.drawable.headgear27_digi_camo_forge_mask);
                    put(28, R.drawable.headgear28_do_rag_cap_and_glasses);
                    put(29, R.drawable.headgear29_double_egg_shades);
                    put(30, R.drawable.headgear30_dust_blocker_2000);
                    put(31, R.drawable.headgear31_eel_cake_hat);
                    put(32, R.drawable.headgear32_eminence_cuff);
                    put(33, R.drawable.headgear33_enchanted_hat);
                    put(34, R.drawable.headgear34_eye_of_justice);
                    put(35, R.drawable.headgear35_face_visor);
                    put(36, R.drawable.headgear36_fake_contacts);
                    put(37, R.drawable.headgear37_festive_party_cone);
                    put(38, R.drawable.headgear38_fierce_fishskull);
                    put(39, R.drawable.headgear39_firefin_facemask);
                    put(40, R.drawable.headgear40_fishfry_biscuit_bandana);
                    put(41, R.drawable.headgear41_fishfry_visor);
                    put(42, R.drawable.headgear42_five_panel_cap);
                    put(43, R.drawable.headgear43_forge_mask);
                    put(44, R.drawable.headgear44_fresh_fish_head);
                    put(45, R.drawable.headgear45_fugu_bell_hat);
                    put(46, R.drawable.headgear46_full_moon_glasses);
                    put(47, R.drawable.headgear47_gas_mask);
                    put(48, R.drawable.headgear48_golden_toothpick);
                    put(49, R.drawable.headgear49_golf_visor);
                    put(50, R.drawable.headgear50_green_novelty_visor);
                    put(51, R.drawable.headgear51_half_rim_glasses);
                    put(52, R.drawable.headgear52_headlamp_helmet);
                    put(53, R.drawable.headgear53_hero_headphones_replica);
                    put(54, R.drawable.headgear54_hero_headset_replica);
                    put(55, R.drawable.headgear55_hickory_work_cap);
                    put(56, R.drawable.headgear56_hivemind_antenna);
                    put(57, R.drawable.headgear57_hockey_helmet);
                    put(58, R.drawable.headgear58_hockey_mask);
                    put(59, R.drawable.headgear59_hothouse_hat);
                    put(60, R.drawable.headgear60_house_tag_denim_cap);
                    put(61, R.drawable.headgear62_jellyvader_cap);
                    put(63, R.drawable.headgear63_jet_cap);
                    put(64, R.drawable.headgear64_jetflame_crest);
                    put(65, R.drawable.headgear65_jogging_headband);
                    put(66, R.drawable.headgear66_jungle_hat);
                    put(67, R.drawable.headgear67_king_facemask);
                    put(68, R.drawable.headgear68_king_flip_mesh);
                    put(69, R.drawable.headgear69_knitted_hat);
                    put(70, R.drawable.headgear70_koshien_bandana);
                    put(71, R.drawable.headgear71_kyonshi_hat);
                    put(72, R.drawable.headgear72_lil_devil_horns);
                    put(73, R.drawable.headgear73_lightweight_cap);
                    put(74, R.drawable.headgear74_long_billed_cap);
                    put(75, R.drawable.headgear75_marinated_headphones);
                    put(76, R.drawable.headgear76_matte_bike_helmet);
                    put(77, R.drawable.headgear77_mecha_head___htr);
                    put(78, R.drawable.headgear78_moist_ghillie_helmet);
                    put(79, R.drawable.headgear79_motocross_nose_guard);
                    put(80, R.drawable.headgear80_mountie_hat);
                    put(81, R.drawable.headgear81_mtb_helmet);
                    put(82, R.drawable.headgear82_new_years_glasses_dx);
                    put(83, R.drawable.headgear83_noise_cancelers);
                    put(84, R.drawable.headgear84_null_visor_replica);
                    put(85, R.drawable.headgear85_oceanic_hard_hat);
                    put(86, R.drawable.headgear86_octo_tackle_helmet_deco);
                    put(87, R.drawable.headgear87_octoglasses);
                    put(88, R.drawable.headgear88_octoking_facemask);
                    put(89, R.drawable.headgear89_octoleet_goggles);
                    put(90, R.drawable.headgear90_octoling_shades);
                    put(91, R.drawable.headgear91_old_timey_hat);
                    put(92, R.drawable.headgear92_orange_novelty_visor);
                    put(93, R.drawable.headgear93_paintball_mask);
                    put(94, R.drawable.headgear94_painters_mask);
                    put(95, R.drawable.headgear95_paisley_bandana);
                    put(96, R.drawable.headgear96_patched_hat);
                    put(97, R.drawable.headgear97_pearlescent_crown);
                    put(98, R.drawable.headgear98_pilot_goggles);
                    put(99, R.drawable.headgear99_pilot_hat);
                    put(100, R.drawable.headgear100_pink_novelty_visor);
                    put(101, R.drawable.headgear101_power_mask_mk_i);
                    put(102, R.drawable.headgear102_power_mask);
                    put(103, R.drawable.headgear103_purple_novelty_visor);
                    put(104, R.drawable.headgear104_retro_specs);
                    put(105, R.drawable.headgear105_safari_hat);
                    put(106, R.drawable.headgear106_sailor_cap);
                    put(107, R.drawable.headgear107_samurai_helmet);
                    put(108, R.drawable.headgear108_seashell_bamboo_hat);
                    put(109, R.drawable.headgear109_sennyu_bon_bon_beanie);
                    put(110, R.drawable.headgear110_sennyu_goggles);
                    put(111, R.drawable.headgear111_sennyu_headphones);
                    put(112, R.drawable.headgear112_sennyu_specs);
                    put(113, R.drawable.headgear113_short_beanie);
                    put(114, R.drawable.headgear114_skate_helmet);
                    put(115, R.drawable.headgear115_skull_bandana);
                    put(116, R.drawable.headgear116_sneaky_beanie);
                    put(117, R.drawable.headgear117_snorkel_mask);
                    put(118, R.drawable.headgear118_soccer_headband);
                    put(119, R.drawable.headgear119_special_forces_beret);
                    put(120, R.drawable.headgear120_splash_goggles);
                    put(121, R.drawable.headgear121_sporty_bobble_hat);
                    put(122, R.drawable.headgear122_squash_headband);
                    put(123, R.drawable.headgear123_squid_clip_ons);
                    put(124, R.drawable.headgear124_squid_facemask);
                    put(125, R.drawable.headgear125_squid_hairclip);
                    put(126, R.drawable.headgear126_squid_nordic);
                    put(127, R.drawable.headgear127_squid_stitch_cap);
                    put(128, R.drawable.headgear128_squidfin_hook_cans);
                    put(129, R.drawable.headgear129_squidlife_headphones);
                    put(130, R.drawable.headgear130_squidvader_cap);
                    put(131, R.drawable.headgear131_squinja_mask);
                    put(132, R.drawable.headgear132_stealth_goggles);
                    put(133, R.drawable.headgear133_steel_helm);
                    put(134, R.drawable.headgear134_straw_boater);
                    put(135, R.drawable.headgear135_streetstyle_cap);
                    put(136, R.drawable.headgear136_striped_beanie);
                    put(137, R.drawable.headgear137_studio_headphones);
                    put(138, R.drawable.headgear138_studio_octophones);
                    put(139, R.drawable.headgear139_sun_visor);
                    put(140, R.drawable.headgear140_sv925_circle_shades);
                    put(141, R.drawable.headgear141_swim_goggles);
                    put(142, R.drawable.headgear142_takoroka_mesh);
                    put(143, R.drawable.headgear143_takoroka_visor);
                    put(144, R.drawable.headgear144_tennis_headband);
                    put(145, R.drawable.headgear145_tinted_shades);
                    put(146, R.drawable.headgear146_toni_kensa_goggles);
                    put(147, R.drawable.headgear147_treasure_hunter);
                    put(148, R.drawable.headgear148_tulip_parasol);
                    put(149, R.drawable.headgear149_twisty_headband);
                    put(150, R.drawable.headgear150_two_stripe_mesh);
                    put(151, R.drawable.headgear151_urchins_cap);
                    put(152, R.drawable.headgear152_visor_skate_helmet);
                    put(153, R.drawable.headgear153_welding_mask);
                    put(154, R.drawable.headgear154_white_arrowbands);
                    put(155, R.drawable.headgear155_white_headband);
                    put(156, R.drawable.headgear156_woolly_urchins_classic);
                    put(157, R.drawable.headgear157_workers_cap);
                    put(158, R.drawable.headgear158_workers_head_towel);
                    put(159, R.drawable.headgear159_yamagiri_beanie);
                    put(160, R.drawable.headgear160_zekko_cap);
                    put(161, R.drawable.headgear161_zekko_mesh);
                }
            }
    );
}
