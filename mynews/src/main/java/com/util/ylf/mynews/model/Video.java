package com.util.ylf.mynews.model;

import java.util.List;

/**
 * Created by Administrator on 2016/9/7.
 */
public class Video {

    /**
     * vid : 908617
     * p : http://i0.itc.cn/20121214/2b49_dcfd52e7_0fbf_d2b6_6863_2672738a51a0_1.jpg
     * p1 : http://i2.itc.cn/20121214/2b49_dcfd52e7_0fbf_d2b6_6863_2672738a51a0_2.jpg
     * l : http://tv.sohu.com/s2012/mrwl/
     * t : 美人无泪
     * t_ : （更新至第8集）
     * t1 : 于正继《美人心计》、《美人天下》后的第三部美人系列剧，以孝庄文皇后的一生为线索，讲述从皇太极征战天下到其孙康熙继位的那段清初历史。有皇太极与海兰珠之间的倾城之恋，也有多尔衮与大玉儿之间的隐忍之恋……
     * mtype : [{"t":"古装剧","l":"http://so.tv.sohu.com/list_p12_p2_u90FD_u5E02_u5267_p3_p4_p5_p6_p7_p8_p9.html"},{"t":"言情剧","l":"http://so.tv.sohu.com/list_p12_p2_u8A00_u60C5_u5267_p3_p4_p5_p6_p7_p8_p9.html"}]
     * mdirector : [{"t":"梁胜权","l":"http://so.tv.sohu.com/mts?&wd=%u6C6A%u4FCA"}]
     * mactor : [{"t":"刘恺威","l":"http://so.tv.sohu.com/mts?&wd=%u9648%u6570"},{"t":"袁姗姗","l":"http://so.tv.sohu.com/mts?&wd=%u9EC4%u78CA"}]
     */

    private String vid;
    private String p;
    private String p1;
    private String l;
    private String t;
    private String t_;
    private String t1;
    /**
     * t : 古装剧
     * l : http://so.tv.sohu.com/list_p12_p2_u90FD_u5E02_u5267_p3_p4_p5_p6_p7_p8_p9.html
     */

    private List<MtypeEntity> mtype;
    /**
     * t : 梁胜权
     * l : http://so.tv.sohu.com/mts?&wd=%u6C6A%u4FCA
     */

    private List<MdirectorEntity> mdirector;
    /**
     * t : 刘恺威
     * l : http://so.tv.sohu.com/mts?&wd=%u9648%u6570
     */

    private List<MactorEntity> mactor;

    public void setVid(String vid) {
        this.vid = vid;
    }

    public void setP(String p) {
        this.p = p;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public void setL(String l) {
        this.l = l;
    }

    public void setT(String t) {
        this.t = t;
    }

    public void setT_(String t_) {
        this.t_ = t_;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    public void setMtype(List<MtypeEntity> mtype) {
        this.mtype = mtype;
    }

    public void setMdirector(List<MdirectorEntity> mdirector) {
        this.mdirector = mdirector;
    }

    public void setMactor(List<MactorEntity> mactor) {
        this.mactor = mactor;
    }

    public String getVid() {
        return vid;
    }

    public String getP() {
        return p;
    }

    public String getP1() {
        return p1;
    }

    public String getL() {
        return l;
    }

    public String getT() {
        return t;
    }

    public String getT_() {
        return t_;
    }

    public String getT1() {
        return t1;
    }

    public List<MtypeEntity> getMtype() {
        return mtype;
    }

    public List<MdirectorEntity> getMdirector() {
        return mdirector;
    }

    public List<MactorEntity> getMactor() {
        return mactor;
    }

    public static class MtypeEntity {
        private String t;
        private String l;

        public void setT(String t) {
            this.t = t;
        }

        public void setL(String l) {
            this.l = l;
        }

        public String getT() {
            return t;
        }

        public String getL() {
            return l;
        }
    }

    public static class MdirectorEntity {
        private String t;
        private String l;

        public void setT(String t) {
            this.t = t;
        }

        public void setL(String l) {
            this.l = l;
        }

        public String getT() {
            return t;
        }

        public String getL() {
            return l;
        }
    }

    public static class MactorEntity {
        private String t;
        private String l;

        public void setT(String t) {
            this.t = t;
        }

        public void setL(String l) {
            this.l = l;
        }

        public String getT() {
            return t;
        }

        public String getL() {
            return l;
        }
    }
}
