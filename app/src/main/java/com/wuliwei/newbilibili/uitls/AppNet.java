package com.wuliwei.newbilibili.uitls;

/**
 * Created by
 * 武立伟
 * 2017/3/22.
 */

public class AppNet {

    //直播
    public static String BASE_URL = "http://live.bilibili.com/AppNewIndex/common?_device=android&appkey=1d8b6e7d45233436&build=501000&mobi_app=android&platform=android&scale=xhdpi&ts=1490007446000&sign=18ff47e60a7263218a9befe86c7cc282";

    //发现
    public static String FIND_URL = "http://app.bilibili.com/x/v2/search/hot?appkey=1d8b6e7d45233436&build=501000&limit=50&mobi_app=android&platform=android&ts=1490014710000&sign=e5ddf94fa9a0d6876cb85756c37c4adc";

    //发现中话题,活动
    public static String HUATI_URL = "http://api.bilibili.com/topic/getlist?appkey=1d8b6e7d45233436&build=501000&mobi_app=android&page=1&pageSize=20&platform=android&ts=1490015740000&sign=be68382cdc99c168ef87f2fa423dd280";

    //原创
    public static String YC_URL = "http://app.bilibili.com/x/v2/rank?appkey=1d8b6e7d45233436&build=501000&mobi_app=android&order=origin&platform=android&pn=1&ps=20&ts=1490015891000&sign=1a5a1c73e3b23be37fb13ee0178ceef0";

    //番剧
    public static String FJ_URL = "http://app.bilibili.com/x/v2/rank?appkey=1d8b6e7d45233436&build=501000&mobi_app=android&order=bangumi&platform=android&pn=1&ps=20&ts=1490015891000&sign=c29299ef4b95c26e104efc13437cf628";

    //商城
    public static String SHOP_URL = "http://bmall.bilibili.com/apiSecond/public/getModelData.do?mark_code=7f7d1ac0796211e6ab5352223301d29a";

    //推荐----综合
    public static String ZH_URL = "http://app.bilibili.com/x/feed/index?appkey=1d8b6e7d45233436&build=501000&idx=1490013261&mobi_app=android&network=wifi&platform=android&pull=true&style=2&ts=1490015599000&sign=af4edc66aef7e443c98c28de2b660aa4";

    //追番
    public static String ZF_URL = "http://bangumi.bilibili.com/api/app_index_page_v4?build=3940&device=phone&mobi_app=iphone&platform=ios";

    //分区----gridView
    public static String GV_URL = "http://app.bilibili.com/x/v2/region?appkey=1d8b6e7d45233436&build=501000&mobi_app=android&platform=android&ts=1490170066000&sign=88793834edd7dd2977bd2de07b93a9b4";

    //分区----下面的
    public static String FQDOWN_URL = "http://app.bilibili.com/x/v2/show/region?appkey=1d8b6e7d45233436&build=501000&mobi_app=android&platform=android&ts=1490014674000&sign=93edb7634f38498a60e5c3ad0b8b0974";

    //推荐
    public static String TUIJIAN_URL = "http://live.bilibili.com/mobile/rooms?_device=android&appkey=1d8b6e7d45233436&area_id=0&build=501000&mobi_app=android&page=1&platform=android&sort=hottest&ts=1490016354000&sign=8cc71690abffd8d3b2c18ef1b548630f";

    //购物车
    public static String SHOPPINGHOME = "http://bmall.bilibili.com/apiSecond/public/getModelData.do?mark_code=7f7d1ac0796211e6ab5352223301d29a";

    //购物车下面的数据
    public static String SHOPPINGHOMEDOWN = "http://bmall.bilibili.com/api/product/list.do?pn=1&ps=6";

    //搜索
    public static String SOUSUO = "http://app.bilibili.com/x/v2/search?appkey=1d8b6e7d45233436&build=501000&duration=0&keyword=";

    //下面字段的搜索
    public static String SUOSOUDOWN = "&mobi_app=android&platform=android&pn=1&ps=20";

}
