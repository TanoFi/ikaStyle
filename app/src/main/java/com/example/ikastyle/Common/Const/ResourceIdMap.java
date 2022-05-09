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

    // フクのギア画像
    public static final Map<Integer,Integer> clothingGearResourceIdMap = Collections.unmodifiableMap(
            new HashMap<Integer, Integer>(){
                {
                    put(0, R.drawable.clothing_gear0);
                    put(1, R.drawable.clothing_gear1_aloha_shirt);
                    put(2, R.drawable.clothing_gear2_anchor_life_vest);
                    put(3, R.drawable.clothing_gear3_anchor_sweat);
                    put(4, R.drawable.clothing_gear4_annaki_blue_cuff);
                    put(5, R.drawable.clothing_gear5_annaki_drive_tee);
                    put(6, R.drawable.clothing_gear6_annaki_evolution_tee);
                    put(7, R.drawable.clothing_gear7_annaki_flannel_hoodie);
                    put(8, R.drawable.clothing_gear8_annaki_polpo_pic_tee);
                    put(9, R.drawable.clothing_gear9_annaki_red_cuff);
                    put(10, R.drawable.clothing_gear10_annaki_yellow_cuff);
                    put(11, R.drawable.clothing_gear11_armor_jacket_replica);
                    put(12, R.drawable.clothing_gear12_b_ball_jersey_away);
                    put(13, R.drawable.clothing_gear13_b_ball_jersey_home);
                    put(14, R.drawable.clothing_gear14_baby_jelly_shirt_and_tie);
                    put(15, R.drawable.clothing_gear15_baby_jelly_shirt);
                    put(16, R.drawable.clothing_gear16_baseball_jersey);
                    put(17, R.drawable.clothing_gear17_basic_tee);
                    put(18, R.drawable.clothing_gear18_berry_ski_jacket);
                    put(19, R.drawable.clothing_gear19_birded_corduroy_jacket);
                    put(20, R.drawable.clothing_gear20_black_8_bit_fishfry);
                    put(21, R.drawable.clothing_gear21_black_anchor_tee);
                    put(22, R.drawable.clothing_gear22_black_baseball_ls);
                    put(23, R.drawable.clothing_gear23_black_cuttlegear_ls);
                    put(24, R.drawable.clothing_gear24_black_hoodie);
                    put(25, R.drawable.clothing_gear25_black_inky_rider);
                    put(26, R.drawable.clothing_gear26_black_layered_ls);
                    put(27, R.drawable.clothing_gear27_black_ls);
                    put(28, R.drawable.clothing_gear28_black_polo);
                    put(29, R.drawable.clothing_gear29_black_squideye);
                    put(30, R.drawable.clothing_gear30_black_tee);
                    put(31, R.drawable.clothing_gear31_black_urchin_rock_tee);
                    put(32, R.drawable.clothing_gear32_black_v_neck_tee);
                    put(33, R.drawable.clothing_gear33_black_velour_octoking_tee);
                    put(34, R.drawable.clothing_gear34_blue_16_bit_fishfry);
                    put(35, R.drawable.clothing_gear35_blue_peaks_tee);
                    put(36, R.drawable.clothing_gear36_blue_sailor_suit);
                    put(37, R.drawable.clothing_gear37_blue_tentatek_tee);
                    put(38, R.drawable.clothing_gear38_brown_fa_11_bomber);
                    put(39, R.drawable.clothing_gear39_camo_layered_ls);
                    put(40, R.drawable.clothing_gear40_camo_zip_hoodie);
                    put(41, R.drawable.clothing_gear41_carnivore_tee);
                    put(42, R.drawable.clothing_gear42_chili_octo_aloha);
                    put(43, R.drawable.clothing_gear43_chili_pepper_ski_jacket);
                    put(44, R.drawable.clothing_gear44_chilly_mountain_coat);
                    put(45, R.drawable.clothing_gear45_chirpy_chips_band_tee);
                    put(46, R.drawable.clothing_gear46_choco_layered_ls);
                    put(47, R.drawable.clothing_gear47_crimson_parashooter);
                    put(48, R.drawable.clothing_gear48_crustwear_xxl);
                    put(49, R.drawable.clothing_gear49_custom_painted_f_3);
                    put(50, R.drawable.clothing_gear50_cycle_king_jersey);
                    put(51, R.drawable.clothing_gear51_cycling_shirt);
                    put(52, R.drawable.clothing_gear52_dakro_golden_tee);
                    put(53, R.drawable.clothing_gear53_dakro_nana_tee);
                    put(54, R.drawable.clothing_gear54_dark_bomber_jacket);
                    put(55, R.drawable.clothing_gear55_dark_urban_vest);
                    put(56, R.drawable.clothing_gear56_deep_octo_satin_jacket);
                    put(57, R.drawable.clothing_gear57_dev_uniform);
                    put(58, R.drawable.clothing_gear58_dots_on_dots_shirt);
                    put(59, R.drawable.clothing_gear59_eggplant_mountain_coat);
                    put(60, R.drawable.clothing_gear60_enchanted_robe);
                    put(61, R.drawable.clothing_gear61_fa_01_jacket);
                    put(62, R.drawable.clothing_gear62_fa_01_reversed);
                    put(63, R.drawable.clothing_gear63_fc_albacore);
                    put(64, R.drawable.clothing_gear64_firefin_navy_sweat);
                    put(65, R.drawable.clothing_gear65_firewave_tee);
                    put(66, R.drawable.clothing_gear66_fishing_vest);
                    put(67, R.drawable.clothing_gear67_forest_vest);
                    put(68, R.drawable.clothing_gear68_forge_inkling_parka);
                    put(69, R.drawable.clothing_gear69_forge_octarian_jacket);
                    put(70, R.drawable.clothing_gear70_fresh_fish_gloves);
                    put(71, R.drawable.clothing_gear71_fresh_octo_tee);
                    put(72, R.drawable.clothing_gear72_friend_tee);
                    put(73, R.drawable.clothing_gear73_front_zip_vest);
                    put(74, R.drawable.clothing_gear74_fugu_tee);
                    put(75, R.drawable.clothing_gear75_garden_gear);
                    put(76, R.drawable.clothing_gear76_grape_hoodie);
                    put(77, R.drawable.clothing_gear77_grape_tee);
                    put(78, R.drawable.clothing_gear78_gray_8_bit_fishfry);
                    put(79, R.drawable.clothing_gear79_gray_college_sweat);
                    put(80, R.drawable.clothing_gear80_gray_fa_11_bomber);
                    put(81, R.drawable.clothing_gear81_gray_hoodie);
                    put(82, R.drawable.clothing_gear82_gray_mixed_shirt);
                    put(83, R.drawable.clothing_gear83_gray_vector_tee);
                    put(84, R.drawable.clothing_gear84_green_cardigan);
                    put(85, R.drawable.clothing_gear85_green_striped_ls);
                    put(86, R.drawable.clothing_gear86_green_tee);
                    put(87, R.drawable.clothing_gear87_green_v_neck_limited_tee);
                    put(88, R.drawable.clothing_gear88_green_velour_octoking_tee);
                    put(89, R.drawable.clothing_gear89_green_zip_hoodie);
                    put(90, R.drawable.clothing_gear90_green_check_shirt);
                    put(91, R.drawable.clothing_gear91_half_sleeve_sweater);
                    put(92, R.drawable.clothing_gear92_herbivore_tee);
                    put(93, R.drawable.clothing_gear93_hero_hoodie_replica);
                    put(94, R.drawable.clothing_gear94_hero_jacket_replica);
                    put(95, R.drawable.clothing_gear95_hightide_era_band_tee);
                    put(96, R.drawable.clothing_gear96_hothouse_hoodie);
                    put(97, R.drawable.clothing_gear97_hula_punk_shirt);
                    put(98, R.drawable.clothing_gear98_icewave_tee);
                    put(99, R.drawable.clothing_gear99_ink_wash_shirt);
                    put(100, R.drawable.clothing_gear100_inkfall_shirt);
                    put(101, R.drawable.clothing_gear101_inkopolis_squaps_jersey);
                    put(102, R.drawable.clothing_gear102_ivory_peaks_tee);
                    put(103, R.drawable.clothing_gear103_juice_parka);
                    put(104, R.drawable.clothing_gear104_kensa_coat);
                    put(105, R.drawable.clothing_gear105_khaki_16_bit_fishfry);
                    put(106, R.drawable.clothing_gear106_king_jersey);
                    put(107, R.drawable.clothing_gear107_krak_on_528);
                    put(108, R.drawable.clothing_gear108_kung_fu_zip_up);
                    put(109, R.drawable.clothing_gear109_layered_anchor_ls);
                    put(110, R.drawable.clothing_gear110_layered_vector_ls);
                    put(111, R.drawable.clothing_gear111_league_tee);
                    put(112, R.drawable.clothing_gear112_light_bomber_jacket);
                    put(113, R.drawable.clothing_gear113_lime_easy_stripe_shirt);
                    put(114, R.drawable.clothing_gear114_linen_shirt);
                    put(115, R.drawable.clothing_gear115_lob_stars_jersey);
                    put(116, R.drawable.clothing_gear116_logo_aloha_shirt);
                    put(117, R.drawable.clothing_gear117_lumberjack_shirt);
                    put(118, R.drawable.clothing_gear118_marinated_top);
                    put(119, R.drawable.clothing_gear119_matcha_down_jacket);
                    put(120, R.drawable.clothing_gear120_mecha_body___akm);
                    put(121, R.drawable.clothing_gear121_milky_eminence_jacket);
                    put(122, R.drawable.clothing_gear122_mint_tee);
                    put(123, R.drawable.clothing_gear123_missus_shrug_tee);
                    put(124, R.drawable.clothing_gear124_mister_shrug_tee);
                    put(125, R.drawable.clothing_gear125_moist_ghillie_suit);
                    put(126, R.drawable.clothing_gear126_mountain_vest);
                    put(127, R.drawable.clothing_gear127_n_pacer_sweat);
                    put(128, R.drawable.clothing_gear128_navy_college_sweat);
                    put(129, R.drawable.clothing_gear129_navy_deca_logo_tee);
                    put(130, R.drawable.clothing_gear130_navy_eminence_jacket);
                    put(131, R.drawable.clothing_gear131_navy_king_tank);
                    put(132, R.drawable.clothing_gear132_navy_striped_ls);
                    put(133, R.drawable.clothing_gear133_negative_longcuff_sweater);
                    put(134, R.drawable.clothing_gear134_neo_octoling_armor);
                    put(135, R.drawable.clothing_gear135_north_country_parka);
                    put(136, R.drawable.clothing_gear136_null_armor_replica);
                    put(137, R.drawable.clothing_gear137_octarian_retro);
                    put(138, R.drawable.clothing_gear138_octo_layered_ls);
                    put(139, R.drawable.clothing_gear139_octo_support_hoodie);
                    put(140, R.drawable.clothing_gear140_octo_tee);
                    put(141, R.drawable.clothing_gear141_octobowler_shirt);
                    put(142, R.drawable.clothing_gear142_octoking_hk_jersey);
                    put(143, R.drawable.clothing_gear143_office_attire);
                    put(144, R.drawable.clothing_gear144_old_timey_clothes);
                    put(145, R.drawable.clothing_gear145_olive_ski_jacket);
                    put(146, R.drawable.clothing_gear146_olive_zekko_parka);
                    put(147, R.drawable.clothing_gear147_online_jersey);
                    put(148, R.drawable.clothing_gear148_orange_cardigan);
                    put(149, R.drawable.clothing_gear149_panda_kung_fu_zip_up);
                    put(150, R.drawable.clothing_gear150_part_time_pirate);
                    put(151, R.drawable.clothing_gear151_pearl_tee);
                    put(152, R.drawable.clothing_gear152_pearlescent_hoodie);
                    put(153, R.drawable.clothing_gear153_pink_easy_stripe_shirt);
                    put(154, R.drawable.clothing_gear154_pink_hoodie);
                    put(155, R.drawable.clothing_gear155_pirate_stripe_tee);
                    put(156, R.drawable.clothing_gear156_positive_longcuff_sweater);
                    put(157, R.drawable.clothing_gear157_power_armor_mk_i);
                    put(158, R.drawable.clothing_gear158_power_armor);
                    put(159, R.drawable.clothing_gear159_prune_parashooter);
                    put(160, R.drawable.clothing_gear160_pullover_coat);
                    put(161, R.drawable.clothing_gear161_purple_camo_ls);
                    put(162, R.drawable.clothing_gear162_rainy_day_tee);
                    put(163, R.drawable.clothing_gear163_record_shop_look_ep);
                    put(164, R.drawable.clothing_gear164_red_cuttlegear_ls);
                    put(165, R.drawable.clothing_gear165_red_hula_punk_with_tie);
                    put(166, R.drawable.clothing_gear166_red_tentatek_tee);
                    put(167, R.drawable.clothing_gear167_red_v_neck_limited_tee);
                    put(168, R.drawable.clothing_gear168_red_vector_tee);
                    put(169, R.drawable.clothing_gear169_red_check_shirt);
                    put(170, R.drawable.clothing_gear170_reel_sweat);
                    put(171, R.drawable.clothing_gear171_reggae_tee);
                    put(172, R.drawable.clothing_gear172_retro_gamer_jersey);
                    put(173, R.drawable.clothing_gear173_retro_sweat);
                    put(174, R.drawable.clothing_gear174_rockenberg_black);
                    put(175, R.drawable.clothing_gear175_rockenberg_white);
                    put(176, R.drawable.clothing_gear176_rockin_leather_jacket);
                    put(177, R.drawable.clothing_gear177_rodeo_shirt);
                    put(178, R.drawable.clothing_gear178_round_collar_shirt);
                    put(179, R.drawable.clothing_gear179_sage_polo);
                    put(180, R.drawable.clothing_gear180_sailor_stripe_tee);
                    put(181, R.drawable.clothing_gear181_samurai_jacket);
                    put(182, R.drawable.clothing_gear182_school_cardigan);
                    put(183, R.drawable.clothing_gear183_school_jersey);
                    put(184, R.drawable.clothing_gear184_school_uniform);
                    put(185, R.drawable.clothing_gear185_sennyu_suit);
                    put(186, R.drawable.clothing_gear186_shirt_and_tie);
                    put(187, R.drawable.clothing_gear187_shirt_with_blue_hoodie);
                    put(188, R.drawable.clothing_gear188_short_knit_layers);
                    put(189, R.drawable.clothing_gear189_shrimp_pink_polo);
                    put(190, R.drawable.clothing_gear190_silver_tentatek_vest);
                    put(191, R.drawable.clothing_gear191_sky_blue_squideye);
                    put(192, R.drawable.clothing_gear192_slash_king_tank);
                    put(193, R.drawable.clothing_gear193_slipstream_united);
                    put(194, R.drawable.clothing_gear194_splatfest_tee_replica);
                    put(195, R.drawable.clothing_gear195_splatfest_tee);
                    put(196, R.drawable.clothing_gear196_squid_satin_jacket);
                    put(197, R.drawable.clothing_gear197_squid_squad_band_tee);
                    put(198, R.drawable.clothing_gear198_squid_yellow_layered_ls);
                    put(199, R.drawable.clothing_gear199_squid_pattern_waistcoat);
                    put(200, R.drawable.clothing_gear200_squid_stitch_tee);
                    put(201, R.drawable.clothing_gear201_squiddor_polo);
                    put(202, R.drawable.clothing_gear202_squidmark_ls);
                    put(203, R.drawable.clothing_gear203_squidmark_sweat);
                    put(204, R.drawable.clothing_gear204_squidstar_waistcoat);
                    put(205, R.drawable.clothing_gear205_squinja_suit);
                    put(206, R.drawable.clothing_gear206_srl_coat);
                    put(207, R.drawable.clothing_gear207_steel_platemail);
                    put(208, R.drawable.clothing_gear208_striped_peaks_ls);
                    put(209, R.drawable.clothing_gear209_striped_rugby);
                    put(210, R.drawable.clothing_gear210_striped_shirt);
                    put(211, R.drawable.clothing_gear211_sunny_day_tee);
                    put(212, R.drawable.clothing_gear212_swc_logo_tee);
                    put(213, R.drawable.clothing_gear213_takoroka_crazy_baseball_ls);
                    put(214, R.drawable.clothing_gear214_takoroka_galactic_tie_dye);
                    put(215, R.drawable.clothing_gear215_takoroka_jersey);
                    put(216, R.drawable.clothing_gear216_takoroka_nylon_vintage);
                    put(217, R.drawable.clothing_gear217_takoroka_rainbow_tie_dye);
                    put(218, R.drawable.clothing_gear218_takoroka_windcrusher);
                    put(219, R.drawable.clothing_gear219_tentatek_slogan_tee);
                    put(220, R.drawable.clothing_gear220_toni_k_baseball_jersey);
                    put(221, R.drawable.clothing_gear221_tricolor_rugby);
                    put(222, R.drawable.clothing_gear222_tumeric_zekko_coat);
                    put(223, R.drawable.clothing_gear223_urchins_jersey);
                    put(224, R.drawable.clothing_gear224_varsity_baseball_ls);
                    put(225, R.drawable.clothing_gear225_varsity_jacket);
                    put(226, R.drawable.clothing_gear226_vintage_check_shirt);
                    put(227, R.drawable.clothing_gear227_wet_floor_band_tee);
                    put(228, R.drawable.clothing_gear228_whale_knit_sweater);
                    put(229, R.drawable.clothing_gear229_white_8_bit_fishfry);
                    put(230, R.drawable.clothing_gear230_white_anchor_tee);
                    put(231, R.drawable.clothing_gear231_white_baseball_ls);
                    put(232, R.drawable.clothing_gear232_white_deca_logo_tee);
                    put(233, R.drawable.clothing_gear233_white_inky_rider);
                    put(234, R.drawable.clothing_gear234_white_king_tank);
                    put(235, R.drawable.clothing_gear235_white_layered_ls);
                    put(236, R.drawable.clothing_gear236_white_leather_f_3);
                    put(237, R.drawable.clothing_gear237_white_ls);
                    put(238, R.drawable.clothing_gear238_white_sailor_suit);
                    put(239, R.drawable.clothing_gear239_white_shirt);
                    put(240, R.drawable.clothing_gear240_white_striped_ls);
                    put(241, R.drawable.clothing_gear241_white_tee);
                    put(242, R.drawable.clothing_gear242_white_urchin_rock_tee);
                    put(243, R.drawable.clothing_gear243_white_v_neck_tee);
                    put(244, R.drawable.clothing_gear244_yellow_layered_ls);
                    put(245, R.drawable.clothing_gear245_yellow_urban_vest);
                    put(246, R.drawable.clothing_gear246_zapfish_satin_jacket);
                    put(247, R.drawable.clothing_gear247_zekko_baseball_ls);
                    put(248, R.drawable.clothing_gear248_zekko_hoodie);
                    put(249, R.drawable.clothing_gear249_zekko_jade_coat);
                    put(250, R.drawable.clothing_gear250_zekko_long_carrot_tee);
                    put(251, R.drawable.clothing_gear251_zekko_long_radish_tee);
                    put(252, R.drawable.clothing_gear252_zekko_redleaf_coat);
                    put(253, R.drawable.clothing_gear253_zink_layered_ls);
                    put(254, R.drawable.clothing_gear254_zink_ls);
                    put(255, R.drawable.clothing_gear255_omega_3_tee);
                }
            }
    );
}