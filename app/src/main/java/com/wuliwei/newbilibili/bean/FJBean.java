package com.wuliwei.newbilibili.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by
 * 武立伟
 * 2017/3/23.
 * <p>
 * 作用：番剧的bean
 */

public class FJBean {


    /**
     * code : 0
     * data : [{"title":"【1月】珈百璃的堕落 11","cover":"http://i0.hdslb.com/bfs/archive/050cee70527166eed3a8f9c85007e83dbd6f77d1.jpg_320x200.jpg","uri":"bilibili://video/9291420","param":"9291420","goto":"av","name":"哔哩哔哩番剧","play":563131,"danmaku":21993,"pts":415276},{"title":"【1月】小林家的龙女仆 10【独家正版】","cover":"http://i2.hdslb.com/bfs/archive/c1ef52083ec6339fba7eac7ce796ef9f37bbaf12.jpg_320x200.jpg","uri":"bilibili://video/9187768","param":"9187768","goto":"av","name":"哔哩哔哩番剧","play":221213,"danmaku":3740,"pts":204838},{"title":"【1月】小林家的龙女仆 11【独家正版】","cover":"http://i1.hdslb.com/bfs/archive/a1080d35c404c099ee066646113fca17a5f131e5.jpg_320x200.jpg","uri":"bilibili://video/9326290","param":"9326290","goto":"av","name":"哔哩哔哩番剧","play":195361,"danmaku":10747,"pts":175547},{"title":"【4月】双星之阴阳师 49","cover":"http://i2.hdslb.com/bfs/archive/4a69258c93dba82814810066f57b0f981a3d3ac5.jpg_320x200.jpg","uri":"bilibili://video/9323043","param":"9323043","goto":"av","name":"TV-TOKYO","play":177766,"danmaku":7473,"pts":162802},{"title":"【1月】人渣的本愿 10【幻樱】","cover":"http://i0.hdslb.com/bfs/archive/c4f451cfe07a9fe1c056a2c111fad82ba8c25c73.jpg_320x200.jpg","uri":"bilibili://video/9208819","param":"9208819","goto":"av","name":"真鱼","play":156989,"danmaku":3398,"pts":144026},{"title":"【1月/完结】黑白来看守所 25【独家正版】","cover":"http://i1.hdslb.com/bfs/archive/cd8f757cd39cd5be9b83d85532ea925bdd36f39a.jpg_320x200.jpg","uri":"bilibili://video/9310212","param":"9310212","goto":"av","name":"哔哩哔哩番剧","play":148749,"danmaku":4376,"pts":140147},{"title":"【1月】小魔女学园 11【独家正版】","cover":"http://i1.hdslb.com/bfs/archive/78494a0d955b299b880eebe21a5b178b89832ce3.jpg_320x200.jpg","uri":"bilibili://video/9272345","param":"9272345","goto":"av","name":"哔哩哔哩番剧","play":128859,"danmaku":4097,"pts":127669},{"title":"【1月】热诚传说 X 24","cover":"http://i0.hdslb.com/bfs/archive/3899d07cc7f259dc25317d57a6702954e8be8408.jpg_320x200.jpg","uri":"bilibili://video/9270699","param":"9270699","goto":"av","name":"哔哩哔哩番剧","play":129010,"danmaku":2015,"pts":124977},{"title":"【1月】小林家的龙女仆 01【独家正版】","cover":"http://i2.hdslb.com/bfs/archive/4a2420aa6d3664aa69fe35e94fdaac364a053452.jpg_320x200.jpg","uri":"bilibili://video/7961887","param":"7961887","goto":"av","name":"哔哩哔哩番剧","play":102012,"danmaku":-504,"pts":103265},{"title":"【1月】人渣的本愿 01【幻樱/澄空】","cover":"http://i2.hdslb.com/bfs/archive/b61bc343b7bf19f82c30b671615bd0df9aad4fbb.jpg_320x200.jpg","uri":"bilibili://video/7964205","param":"7964205","goto":"av","name":"真鱼","play":107062,"danmaku":1533,"pts":97521},{"title":"【1月】猫咪日常 11【独家正版】","cover":"http://i1.hdslb.com/bfs/archive/7b6800cea575f906f67d84039e4412b7d87dccce.jpg_320x200.jpg","uri":"bilibili://video/9270612","param":"9270612","goto":"av","name":"哔哩哔哩番剧","play":99025,"danmaku":316,"pts":91617},{"title":"【1月】小林家的龙女仆 09【独家正版】","cover":"http://i2.hdslb.com/bfs/archive/1ec8ffb12a9c6313cba04cd7ac27cd47eb39a3fc.jpg_320x200.jpg","uri":"bilibili://video/9042355","param":"9042355","goto":"av","name":"哔哩哔哩番剧","play":98923,"danmaku":1252,"pts":90283},{"title":"【1月】小林家的龙女仆 02【独家正版】","cover":"http://i2.hdslb.com/bfs/archive/fbc8ce3986e7dd4d587501fa1dde45d8d729da47.jpg_320x200.jpg","uri":"bilibili://video/8084513","param":"8084513","goto":"av","name":"哔哩哔哩番剧","play":94783,"danmaku":1059,"pts":86908},{"title":"【1月】珈百璃的堕落 10","cover":"http://i0.hdslb.com/bfs/archive/d6061870323a6d794f5794b8a4e06fb3c4242775.jpg_320x200.jpg","uri":"bilibili://video/9149361","param":"9149361","goto":"av","name":"哔哩哔哩番剧","play":92740,"danmaku":1159,"pts":84154},{"title":"【1月】Hand Shakers 11","cover":"http://i1.hdslb.com/bfs/archive/87e7c602d482a2628a1c2ddf68f9b15cf9ed42d6.jpg_320x200.jpg","uri":"bilibili://video/9305917","param":"9305917","goto":"av","name":"哔哩哔哩番剧","play":88961,"danmaku":1540,"pts":82676},{"title":"【1月】ACCA13区监察课 11","cover":"http://i1.hdslb.com/bfs/archive/2e5d701c8e680bdf00b4e4e64b102d113ffac6fc.jpg_320x200.jpg","uri":"bilibili://video/9309906","param":"9309906","goto":"av","name":"哔哩哔哩番剧","play":80925,"danmaku":3047,"pts":78227},{"title":"【剧场版】游戏王 次元之暗面【MGRTxNEOAVP】","cover":"http://i1.hdslb.com/bfs/archive/a7d748dee6844b5660e1594e72079dda57045710.jpg_320x200.jpg","uri":"bilibili://video/9123347","param":"9123347","goto":"av","name":"MGRT音花雪月","play":37912,"danmaku":1812,"pts":77912},{"title":"【1月】小林家的龙女仆 08【独家正版】","cover":"http://i2.hdslb.com/bfs/archive/f952f9281e98422e38d8f3bebb271534e809046f.jpg_320x200.jpg","uri":"bilibili://video/8908904","param":"8908904","goto":"av","name":"哔哩哔哩番剧","play":84717,"danmaku":851,"pts":77113},{"title":"【1月】暗芝居 第四季 12","cover":"http://i1.hdslb.com/bfs/archive/e7cf1b26b1af428d655409a3ab273cc8cfc2756d.jpg_320x200.jpg","uri":"bilibili://video/9272928","param":"9272928","goto":"av","name":"TV-TOKYO","play":82497,"danmaku":874,"pts":76112},{"title":"【1月】小林家的龙女仆 03【独家正版】","cover":"http://i1.hdslb.com/bfs/archive/bed04be09056c1ed547cfe7571511f3a2caf2e59.jpg_320x200.jpg","uri":"bilibili://video/8212411","param":"8212411","goto":"av","name":"哔哩哔哩番剧","play":83394,"danmaku":632,"pts":76020}]
     * message :
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * title : 【1月】珈百璃的堕落 11
         * cover : http://i0.hdslb.com/bfs/archive/050cee70527166eed3a8f9c85007e83dbd6f77d1.jpg_320x200.jpg
         * uri : bilibili://video/9291420
         * param : 9291420
         * goto : av
         * name : 哔哩哔哩番剧
         * play : 563131
         * danmaku : 21993
         * pts : 415276
         */

        private String title;
        private String cover;
        private String uri;
        private String param;
        @JSONField(name = "goto")
        private String gotoX;
        private String name;
        private int play;
        private int danmaku;
        private int pts;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }

        public String getGotoX() {
            return gotoX;
        }

        public void setGotoX(String gotoX) {
            this.gotoX = gotoX;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPlay() {
            return play;
        }

        public void setPlay(int play) {
            this.play = play;
        }

        public int getDanmaku() {
            return danmaku;
        }

        public void setDanmaku(int danmaku) {
            this.danmaku = danmaku;
        }

        public int getPts() {
            return pts;
        }

        public void setPts(int pts) {
            this.pts = pts;
        }
    }
}
