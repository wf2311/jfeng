package com.wf2311.jfeng.regex;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author wangfeng
 * @time 2018/02/06 15:56.
 */
public class MatchersTest {


    @Test
    public void subSentence() {
        String text = "原标题：韩国冬奥反恐军犬跑了！10小时后被抓回一脸无辜\n" +
                "\n" +
                "海外网2月6日电 最近，韩国平昌有些闹心。冬奥会本周五（9日）就要开始，却仍是小插曲不断。先是30名安保人员出现食物中毒，引爆舆论质疑。紧接着，韩国军方又扎心地发现，派到平昌支援反恐的一只军犬悄悄溜掉了！\n" +
                "\n" +
                "据韩媒NEWS 1报道，当地时间5日早上8点30分，一只6岁的雄性寻回犬逃出平昌郡大和面的军营后下落不明。韩国军方紧急通过乡村广播等告知附近居民，并投入兵力进行搜寻。\n" +
                "\n" +
                "虽然韩军连空中侦察的手段都动用了，但走失的军犬还是没有下落。10个小时之后，即当天下午6点20分左右，军犬终于在距离军营1.5公里处被居民找到了。他把狗拴在自家的牲口棚后，向军方进行了汇报。\n" +
                "\n" +
                "韩国军方表示，军犬在走失过程中未造成人身及财产损失，目前已经归队。韩媒公布的照片显示，这只黑色的军犬健康状况不错，虽然犯了“擅离岗位”的错误，但一脸无辜还是让人忍俊不禁。（编译/海外网刘强）";

        List<String> strings = Matchers.subSentence(text);
        strings.forEach(System.out::println);
    }

    String mobiles = " 15625201888,深圳联通￥1.41万, 18911813777,北京电信￥1.19万, 15998152345,沈阳移动￥9300, 13322876666,珠海电信￥3.80万, 18328612128,成都移动￥500, 13166630555,沈阳联通￥7200, 15840090001,沈阳移动￥1.10万, 15010651666,北京移动￥8650, 13341149443,北京电信￥7200, 18625555599,郑州联通￥1.33万, 15840075777,沈阳移动￥1.70万, 15833337000,保定移动￥3.56万, 15104006999,沈阳移动￥1.50万, 13660312345,广州移动￥2.91万, 18810025888,北京移动￥4.65万, 13088888685,深圳联通￥1.06万, 13066910666,深圳联通￥8300, 15040116888,沈阳移动￥1.50万, 18800001016,北京移动￥1.28万, 13329811111,宜昌电信￥4.90万, 18734821111,太原移动￥3.50万, 13911950099,北京移动￥1.98万, 13699112020,北京移动￥5500, 15958018666,杭州移动￥9900, 18811125588,北京移动￥1.98万, 18510101010,北京联通￥25万, 15105397979,临沂移动￥3620, 13358055555,苏州电信￥8.88万, 13391966660,北京电信￥1.50万, 13339500444";

    @Test
    public void matchMobile() {
        String mobile = Matchers.matchMobile(mobiles);
        Assert.assertEquals("15625201888", mobile);

    }

    @Test
    public void matchAllMobile() {
        List<String> ms = Matchers.matchAllMobile(mobiles);
        Assert.assertEquals(30, ms.size());
    }

    String emails = "发件人： zhangwenting@sangame.com <zhangwenting@sangame.com>\n" +
            "发送日期： 2018年1月2日 15:55\n" +
            "发送至： wangfeng03 <wangfeng03@sangame.com> 、 caihuiqin <caihuiqin@sangame.com> 、 liuweibin <liuweibin@sangame.com> 、 xulijie <xulijie@sangame.com> 、 renzemin <renzemin@sangame.com> 、 panshuai <panshuai@sangame.com>\n" +
            "抄送人： tenghongshui <tenghongshui@sangame.com> 、 weifan <weifan@sangame.com> 、 guoxingye <guoxingye@sangame.com> 、 yuancan <yuancan@sangame.com> 、 yinjun <yinjun@sangame.com> 、 zhaoxinmiao01 <zhaoxinmiao01@sangame.com>\n" +
            "主题： 广告管理系统V1.5测试报告\n";

    @Test
    public void matchEmail() {
        String email = Matchers.matchEmail(emails);
        Assert.assertEquals("zhangwenting@sangame.com", email);
    }

    @Test
    public void matchAllEmail() {
        List<String> es = Matchers.matchAllEmail(emails);
        System.out.println(es.size());

    }
}