package com.lx.simplepass.app;

/**
 * com.lx.simplepass.app
 * SimplePass
 * Created by lixiao2
 * 2019/1/10.
 */

public class HttpUrl {

    public static String BASE_URL = "http://apicloud.mob.com";

    /** 获取城市URL **/
    public static String GET_CITY_URL = BASE_URL + "/v1/weather/citys";

    /** 获取菜谱分类URL **/
    public static String GET_COOK_CATEGORY_URL = BASE_URL + "/v1/cook/category/query";

    /** 根据条件查询菜谱列表URL **/
    public static String GET_COOK_FOOD_LIST_URL = BASE_URL + "/v1/cook/menu/search";

    /** 根据菜谱ID查询菜谱详细信息URL **/
    public static String GET_COOK_DETAIL_URL = BASE_URL + "/v1/cook/menu/query";

    /** 根据条件查询微信精选列表URL **/
    public static String GET_WEIXIN_PIECE_URL = BASE_URL + "/wx/article/search";
    /** 根据条件查询微信精选类别URL **/
    public static String GET_WEIXIN_CATEGORY_URL = BASE_URL + "/wx/article/category/query";
}
