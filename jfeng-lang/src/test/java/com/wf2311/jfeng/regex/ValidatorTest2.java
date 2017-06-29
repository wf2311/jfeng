package com.wf2311.jfeng.regex;

import org.junit.Test;

import java.util.List;

/**
 * @author wangfeng
 * @time 2017/06/29 15:58.
 */
public class ValidatorTest2 {

    @Test
    public void testMatchEmail(){
        String text = "发件人: \n" +
                "xulijie@sangame.com<xulijie@sangame.com>\n" +
                "收件人: \n" +
                "郭兴业<guoxingye@sangame.com>\n" +
                "抄送: \n" +
                "tenghongshui@sangame.com<tenghongshui@sangame.com>,   zhangyiyi<zhangyiyi@sangame.com>,   '范倩倩'<fanqianqian@sangame.com>,   wangyixiao01<wangyixiao01@sangame.com>,   chenyating<chenyating@sangame.com>,   caihuiqin<caihuiqin@sangame.com>,   hyy01<hyy01@sangame.com>,   wangfeng03<wangfeng03@sangame.com>,   yigui<yigui@sangame.com>,   yuancan<yuancan@sangame.com>\n" +
                "时间: \n" +
                "2017年6月27日 (周二) 17:21";

        List<String> list = Matchers.matchAll(text, Regex.EMAIL);
        list.forEach(System.out::println);

        List<String> list1 = Matchers.matchAll(text, Regex.CHINESE);
        list1.forEach(System.out::println);

        List<String> list2 = Matchers.matchAll(text.replace("\n",""), Regex.SENTENCE);
        list2.forEach(System.out::println);

    }

    @Test
    public void testMatch2(){
        String text = "原标题：中国再向菲律宾伸出援手杜特尔特:有中国这样的朋友真好\n" +
                "\n" +
                "参考消息网6月29日报道 据英国广播公司网站6月29日报道，中国28日赠送了大量枪支给菲律宾。这批自动步枪和狙击步枪和弹药是杜特尔特上台以来从中国得到的第一批军援。其中有中国提供的具有世界先进水平的7.62毫米高精度狙击步枪。\n" +
                "\n" +
                "报道称，杜特尔特说，这批价值人民币5000万元的军火“说明菲律宾和中国关系进入了一个新阶段”。\n" +
                "\n" +
                "杜特尔特说，“有了中国这样理解我们的朋友，真是很好。”杜特尔特担任总统后就威胁要疏远美国这个传统盟友，同北京发展关系。杜特尔特说要寻求从中国和俄罗斯得到更多武器。美国一直批评杜特尔特的禁毒战争。他宣布在菲律宾南部实行戒严以打击极端组织后，也受到西方国家的抨击。\n" +
                "\n" +
                "报道称，菲律宾人也表示在过去的一年里因为警方加强了执法行动，他们感到更安全。\n" +
                "\n" +
                "据英国广播公司报道，中国驻菲律宾大使赵鉴华在移交这批武器时说，不久会向菲律宾提供“第二批”武器。赵鉴华大使说，这批捐赠的数量不大，但是在两国军事关系发展中具有重大意义。他说中国在反恐方面愿意探讨在联合训练，情报分享以及联合军事演习方面同菲律宾合作。\n" +
                "\n" +
                "另据中国驻菲律宾大使馆消息，6月27日下午，赵鉴华大使前往菲律宾总统府，代表中国政府向菲总统杜特尔特提供1500万比索人道主义捐赠，用于马拉维民众安置事宜。社会福利部部长塔圭瓦罗、卫生部副部长巴列接收了捐赠支票。菲外交部部长卡亚塔诺、总统特别助理克里斯托弗·吴、中国驻菲使馆参赞贺湘琦等在座。赵大使表示，相信在杜特尔特总统的领导下，菲律宾一定能够战胜恐怖主义，马拉维人民一定能建设更加美好的家园。杜特尔特总统表示，诚挚感谢中方的援助，这体现出中国人民对菲律宾人民的深情厚谊。";
        List<String> list2 = Matchers.matchAll(text.replace("\n",""), Regex.SENTENCE);
        list2.forEach(System.out::println);

    }

}
