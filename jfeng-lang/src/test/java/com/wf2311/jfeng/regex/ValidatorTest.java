package com.wf2311.jfeng.regex;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author wf2311
 */
public class ValidatorTest {
    @Test
    public void testChinese() {
        String text = "123汉字abc";
        String c1 = "汉";
        String c2 = "汉字";
        assertEquals(c2, Matchers.match(text, Regex.CHINESE));
        assertEquals(c1, Matchers.match(text, Regex.ONE_CHINESE), c1);
        assertEquals(false, Validator.fullMatch(text, Regex.ONE_CHINESE));
        assertEquals(true, Validator.fullMatch(c1, Regex.ONE_CHINESE));
        assertEquals(false, Validator.fullMatch(c2, Regex.ONE_CHINESE));
        assertEquals(true, Validator.fullMatch(c1, Regex.CHINESE));
        assertEquals(true, Validator.fullMatch(c2, Regex.CHINESE));
    }

    @Test
    public void testMatchAll() {
        String text = "由于邮1箱的基2本格式4为“名称@域名”，需6要使用“^”匹配邮箱的开始部分，用“$”匹配邮7箱结束部分以保证邮箱前后不能有其他字符，所以最终邮箱的正则表达式为";
        List<String> list = Matchers.matchAll(text, Regex.CHINESE);
        if (!list.isEmpty()) {
            list.forEach(System.out::println);
        }
    }
    @Test
    public void testMatchSentence(){
        String text = "最近学习正则表达式，发现Java中的一些术语与其他地方描述的有所差异。。。\n比如Java正则表达式中的“组”概念与《正则表达式必知必会》一书中讲述的“子表达式”其实是一样的，只是表述不同而已。由此也引发了使用JavaAPI时对group(int group)、start(int group)、end(int group)不是太理解。在阅读了关于正则表达式问题：有谁用过Matcher类的group()方法没有 之后彻底明白，并写了一个小程序测试。";
        List<String> list = Matchers.subSentence(text);
        if (!list.isEmpty()) {
            list.forEach(c-> System.out.println(c+"\n==========================="));
        }
    }

    @Test
    public void testMatchMobile(){
        List<String> list = Matchers.matchAllMobile(telephones);
        list.forEach(System.out::println);
    }

    private static final String telephones = "fz1\t123456\n" +
            "测试爱爱爱\t13000000000\n" +
            "测试22qqqqq\t13022225895\n" +
            "测试120000000\t13022581410\n" +
            "ceshi 1617111\t13045125589\n" +
            "4嗯嗯嗯\t13045673380\n" +
            "hdhfhd888\t13045685500\n" +
            "jjkkllo\t13052386699\n" +
            "cjjs1256\t13052630012\n" +
            "uuiio\t13052893321\n" +
            "如水雾\t13054103697\n" +
            "萌哒哒\t13054825623\n" +
            "测试yywuiii\t13055523001\n" +
            "测试测试1111-1\t13055525899\n" +
            "测试sjsjs\t13055565623\n" +
            "hhddd\t13055623450\n" +
            "测试11111112\t13055672271\n" +
            "rrggce\t13055685977\n" +
            "hhhdhh谁谁谁\t13055689966\n" +
            "测试uuu22222\t13055891237\n" +
            "ceshi728219\t13056021239\n" +
            "eeeeet\t13056201475\n" +
            "测试零零谁\t13056214859\n" +
            "测试-001\t13056231148\n" +
            "ddsiii\t13056231207\n" +
            "kk\t13056234555\n" +
            "666666\t13056234589\n" +
            "测试99938333\t13056238550\n" +
            "厉害了shshshshs\t13056270063\n" +
            "测绘搜\t13056282259\n" +
            "llslsl看is就\t13056285566\n" +
            "测试3333\t13056526611\n" +
            "测试11111号\t13056562220\n" +
            "iioo\t13056725896\n" +
            "hhhpp\t13056781200\n" +
            "ttttg\t13056783450\n" +
            "测试00221221\t13056820015\n" +
            "测试lls\t13056821479\n" +
            "测试1111101\t13056821520\n" +
            "测试qwer\t13056892220\n" +
            "测试是是是是是是11\t13056892225\n" +
            "瞅瞅测试三\t13056892258\n" +
            "测试uuuyo\t13056895555\n" +
            "测试0928kkk\t13056898877\n" +
            "yuoi测四\t13058201115\n" +
            "ceshi18888888\t13058412288\n" +
            "Ella的\t13058523023\n" +
            "测试92990000\t13058623339\n" +
            "东大遇见\t13058623369\n" +
            "测试2832\t13058692258\n" +
            "六六六\t13058895615\n" +
            "测试有哟\t13058961254\n" +
            "5555555\t13058963330\n" +
            "hhsj\t13066689212\n" +
            "畏畏缩缩\t13066743366\n" +
            "ggdsssl\t13066782367\n" +
            "ces19999\t13066789290\n" +
            "次次次35\t13066792031\n" +
            "ssjj测试\t13067802233\n" +
            "测试77779\t13067892204\n" +
            "测试yyiyiyiyi\t13067892214\n" +
            "gggg8\t13067899055\n" +
            "gggqqww\t13068901722\n" +
            "lijjsj\t13069542321\n" +
            "热热王企鹅\t13069712503\n" +
            "jack\t13072639902\n" +
            "法克法克法克\t13073823421\n" +
            "测试10000001\t13075211777\n" +
            "测试诚诚\t13075273391\n" +
            "hhhshss\t13075478882\n" +
            "测试0009\t13076554993\n" +
            "13076628815\t13076628815\n" +
            "yuyuuoo\t13077389962\n" +
            "测试11111199999\t13077546781\n" +
            "测试00009999\t13077621821\n" +
            "jjjj\t13077659931\n" +
            "测试7272看看书\t13077692203\n" +
            "ssjj测试2\t13077762288\n" +
            "ssjj测试3\t13077762289\n" +
            "ssjj测试4\t13077762290\n" +
            "ssjj测试5\t13077762291\n" +
            "ssjj测试6\t13077762292\n" +
            "ssjj测试7\t13077762293\n" +
            "ssjj测试8\t13077762294\n" +
            "ssjj测试9\t13077762295\n" +
            "ssjj测试10\t13077762296\n" +
            "ssjj测试11\t13077762297\n" +
            "ssjj测试12\t13077762298\n" +
            "ssjj测试13\t13077762299\n" +
            "ssjj测试14\t13077762300\n" +
            "ssjj测试15\t13077762301\n" +
            "ssjj测试16\t13077762302\n" +
            "ssjj测试17\t13077762303\n" +
            "ssjj测试18\t13077762304\n" +
            "ssjj测试19\t13077762305\n" +
            "ssjj测试20\t13077762306\n" +
            "ssjj测试21\t13077762307\n" +
            "ssjj测试22\t13077762308\n" +
            "ssjj测试23\t13077762309\n" +
            "ssjj测试24\t13077762310\n" +
            "ssjj测试25\t13077762311\n" +
            "ssjj测试26\t13077762312\n" +
            "ssjj测试27\t13077762313\n" +
            "ssjj测试28\t13077762314\n" +
            "ssjj测试29\t13077762315\n" +
            "ssjj测试30\t13077762316\n" +
            "ssjj测试31\t13077762317\n" +
            "ssjj测试32\t13077762318\n" +
            "ssjj测试33\t13077762319\n" +
            "ssjj测试34\t13077762320\n" +
            "ssjj测试35\t13077762321\n" +
            "ssjj测试36\t13077762322\n" +
            "ssjj测试37\t13077762323\n" +
            "ssjj测试38\t13077762324\n" +
            "ssjj测试39\t13077762325\n" +
            "ssjj测试40\t13077762326\n" +
            "ssjj测试41\t13077762327\n" +
            "ssjj测试42\t13077762328\n" +
            "ssjj测试43\t13077762329\n" +
            "ssjj测试44\t13077762330\n" +
            "ssjj测试45\t13077762331\n" +
            "ssjj测试46\t13077762332\n" +
            "ssjj测试47\t13077762333\n" +
            "ssjj测试48\t13077762334\n" +
            "ssjj测试49\t13077762335\n" +
            "ssjj测试50\t13077762336\n" +
            "ssjj测试51\t13077762337\n" +
            "ssjj测试52\t13077762338\n" +
            "ssjj测试53\t13077762339\n" +
            "ssjj测试54\t13077762340\n" +
            "ssjj测试55\t13077762341\n" +
            "ssjj测试56\t13077762342\n" +
            "ssjj测试57\t13077762343\n" +
            "ssjj测试58\t13077762344\n" +
            "ssjj测试59\t13077762345\n" +
            "ssjj测试60\t13077762346\n" +
            "ssjj测试61\t13077762347\n" +
            "ssjj测试62\t13077762348\n" +
            "ssjj测试63\t13077762349\n" +
            "ssjj测试64\t13077762350\n" +
            "ssjj测试65\t13077762351\n" +
            "ssjj测试66\t13077762352\n" +
            "ssjj测试67\t13077762353\n" +
            "ssjj测试68\t13077762354\n" +
            "ssjj测试69\t13077762355\n" +
            "ssjj测试70\t13077762356\n" +
            "ssjj测试71\t13077762357\n" +
            "ssjj测试72\t13077762358\n" +
            "ssjj测试73\t13077762359\n" +
            "ssjj测试74\t13077762360\n" +
            "ssjj测试75\t13077762361\n" +
            "ssjj测试76\t13077762362\n" +
            "ssjj测试77\t13077762363\n" +
            "ssjj测试78\t13077762364\n" +
            "ssjj测试79\t13077762365\n" +
            "ssjj测试80\t13077762366\n" +
            "ssjj测试81\t13077762367\n" +
            "ssjj测试82\t13077762368\n" +
            "ssjj测试83\t13077762369\n" +
            "ssjj测试84\t13077762370\n" +
            "ssjj测试85\t13077762371\n" +
            "ssjj测试86\t13077762372\n" +
            "ssjj测试87\t13077762373\n" +
            "ssjj测试88\t13077762374\n" +
            "ssjj测试89\t13077762375\n" +
            "ssjj测试90\t13077762376\n" +
            "ssjj测试91\t13077762377\n" +
            "ssjj测试92\t13077762378\n" +
            "ssjj测试93\t13077762379\n" +
            "ssjj测试94\t13077762380\n" +
            "ssjj测试95\t13077762381\n" +
            "ssjj测试96\t13077762382\n" +
            "ssjj测试97\t13077762383\n" +
            "ssjj测试98\t13077762384\n" +
            "ssjj测试99\t13077762385\n" +
            "ssjj测试100\t13077762386\n" +
            "ssjj测试101\t13077762387\n" +
            "ssjj测试102\t13077762388\n" +
            "ssjj测试103\t13077762389\n" +
            "ssjj测试104\t13077762390\n" +
            "ssjj测试105\t13077762391\n" +
            "ssjj测试106\t13077762392\n" +
            "ssjj测试107\t13077762393\n" +
            "ssjj测试108\t13077762394\n" +
            "ssjj测试109\t13077762395\n" +
            "ssjj测试110\t13077762396\n" +
            "ssjj测试111\t13077762397\n" +
            "ssjj测试112\t13077762398\n" +
            "ssjj测试113\t13077762399\n" +
            "ssjj测试114\t13077762400\n" +
            "ssjj测试115\t13077762401\n" +
            "ssjj测试116\t13077762402\n" +
            "ssjj测试117\t13077762403\n" +
            "ssjj测试118\t13077762404\n" +
            "ssjj测试119\t13077762405\n" +
            "ssjj测试120\t13077762406\n" +
            "ssjj测试121\t13077762407\n" +
            "ssjj测试122\t13077762408\n" +
            "ssjj测试123\t13077762409\n" +
            "ssjj测试124\t13077762410\n" +
            "ssjj测试125\t13077762411\n" +
            "ssjj测试126\t13077762412\n" +
            "ssjj测试127\t13077762413\n" +
            "ssjj测试128\t13077762414\n" +
            "ssjj测试129\t13077762415\n" +
            "ssjj测试130\t13077762416\n" +
            "ssjj测试131\t13077762417\n" +
            "ssjj测试132\t13077762418\n" +
            "ssjj测试133\t13077762419\n" +
            "ssjj测试134\t13077762420\n" +
            "ssjj测试135\t13077762421\n" +
            "ssjj测试136\t13077762422\n" +
            "ssjj测试137\t13077762423\n" +
            "ssjj测试138\t13077762424\n" +
            "ssjj测试139\t13077762425\n" +
            "ssjj测试140\t13077762426\n" +
            "ssjj测试141\t13077762427\n" +
            "ssjj测试142\t13077762428\n" +
            "ssjj测试143\t13077762429\n" +
            "ssjj测试144\t13077762430\n" +
            "666\t13077772511\n" +
            "测试kkk\t13077782123\n" +
            "ccccccc\t13077789221\n" +
            "测试88六六六\t13077821106\n" +
            "侧是夜夜\t13077822345\n" +
            "rrrr55\t13077823411\n" +
            "测试77662\t13077829321\n" +
            "测试6111\t13077868002\n" +
            "cehsi92212\t13077890002\n" +
            "测试lin\t13077890211\n" +
            "测试llll\t13077892021\n" +
            "测试415\t13077892073\n" +
            "jjjwolii\t13077892097\n" +
            "好好读书\t13077892206\n" +
            "eeddcc\t13077892211\n" +
            "hhh222\t13077892218\n" +
            "cesskk\t13077892235\n" +
            "测试沟通\t13077892271\n" +
            "jjdj\t13078239032\n" +
            "rrr天天\t13078377234\n" +
            "就简简单单\t13078767081\n" +
            "utter\t13078798004\n" +
            "rrr4\t13078802537\n" +
            "测试56789\t13078901102\n" +
            "hhwuli\t13078902235\n" +
            "jjllooo\t13078903334\n" +
            "fdeer\t13078903345\n" +
            "rrtry\t13078903567\n" +
            "kkeeee\t13078907772\n" +
            "测4eg\t13078921992\n" +
            "ceshi0008223\t13078926371\n" +
            "思思思思\t13078929911\n" +
            "测试提提提\t13078973721\n" +
            "hhh\t13078992221\n" +
            "测试5ttgk\t13078992253\n" +
            "测试022d\t13085236950\n" +
            "ceshi000ooo\t13085236950\n" +
            "过得更好是\t13085261152\n" +
            "花蝴蝶11\t13085645448\n" +
            "rrrr\t13085652200\n" +
            "测试414\t13086529911\n" +
            "冷飞飞\t13086628362\n" +
            "t121212d\t13087543312\n" +
            "354354\t13087543332\n" +
            "测试77565\t13087608822\n" +
            "测试72772\t13087620032\n" +
            "cvv\t13087628111\n" +
            "测试44647去去去\t13087768921\n" +
            "二二二特\t13087793021\n" +
            "好好说说看\t13087862468\n" +
            "测试11嗷嗷嗷\t13088239021\n" +
            "撤回啥\t13088273224\n" +
            "诚诚11 \t13088362934\n" +
            "胡好的好\t13088372275\n" +
            "热热额\t13088372539\n" +
            "测试01234\t13088643721\n" +
            "ggggkk\t13088721234\n" +
            "测试789111\t13088729951\n" +
            "测报表\t13088752832\n" +
            "测报表2\t13088752870\n" +
            "楠楠111\t13088762234\n" +
            "测试2okkj\t13088762357\n" +
            "而额头\t13088762811\n" +
            "fffhh\t13088762812\n" +
            "对对对对\t13088763832\n" +
            "测试test0\t13088771892\n" +
            "测试3333333\t13088772211\n" +
            "hhh测试ceeee\t13088832731\n" +
            "ceshi0324\t13088862341\n" +
            "hhh111\t13088872231\n" +
            "哈哈哈\t13088873344\n" +
            "测试3331000\t13088876222\n" +
            "测试666712\t13088882712\n" +
            "测试12121212\t13088889200\n" +
            "454546\t13088889332\n" +
            "ceffff\t13088889971\n" +
            "测试611\t13088890008\n" +
            "测试611\t13088890034\n" +
            "测试600\t13088890066\n" +
            "测试111333\t13088892113\n" +
            "测试一号\t13088892123\n" +
            "测试\t13088892201\n" +
            "测试10009999\t13088892230\n" +
            "测试11113\t13088892234\n" +
            "测试72ueu\t13088892277\n" +
            "小小人儿\t13088892331\n" +
            "测试1111010101\t13088892384\n" +
            "测试1去啊啊啊啊\t13088892931\n" +
            "测试uu1去去去\t13088898221\n" +
            "tttyyyy\t13088902012\n" +
            "测试416\t13088902273\n" +
            "测试1qaa\t13088902277\n" +
            "kkk\t13088903321\n" +
            "测试1eew\t13088920034\n" +
            "fffjs\t13088921123\n" +
            "hshsh\t13088927321\n" +
            "测试772321\t13088952265\n" +
            "测试417\t13088992013\n" +
            "测试777\t13089002211\n" +
            "测试jjkk破\t13089003722\n" +
            "测试cccyyy他\t13089754123\n" +
            "测试737733\t13089832221\n" +
            "fddl\t13089873357\n" +
            "测试a\t13089922311\n" +
            "测试b\t13089922322\n" +
            "测试c\t13089922333\n" +
            "测试a\t13089922348\n" +
            "测试f\t13089922366\n" +
            "测试g\t13089922367\n" +
            "测试H\t13089922368\n" +
            "测试Hi\t13089922369\n" +
            "测试J\t13089922370\n" +
            "测试K\t13089922371\n" +
            "测试L\t13089922372\n" +
            "测试d\t13089922444\n" +
            "测试e\t13089922555\n" +
            "测试418\t13089926781\n" +
            "13089926781\t13089926781\n" +
            "测试5qq\t13090098872\n" +
            "4545454\t13095442245\n" +
            "测试yyiii\t13097821872\n" +
            "下雨天 \t13098628321\n" +
            "hhd28282\t13098638882\n" +
            "4\t13098721110\n" +
            "ffww\t13098765555\n" +
            "ceskk\t13099093728\n" +
            "测试22212\t13099823321\n" +
            "3\t13099862182\n" +
            "ffff\t13099863353\n" +
            "测试882732\t13099863641\n" +
            "fff\t13099866222\n" +
            "测试111110001\t13099872214\n" +
            "测试82882222\t13099872256\n" +
            "ceshi8800111\t13099872610\n" +
            "hhh3\t13099873321\n" +
            "测试1220000\t13099924556\n" +
            "雯雯\t13099926362\n" +
            "的点点滴滴\t13099926612\n" +
            "的点点滴滴3\t13099926635\n" +
            "tttt\t13099972234\n" +
            "fff\t13099973444\n" +
            "测试111111\t13099982821\n" +
            "哒哒哒2\t13099982891\n" +
            "kkk\t13099986732\n" +
            "ueuue\t13099987672\n" +
            "测试1\t13099990011\n" +
            "测试4\t13099990022\n" +
            "ceshi5\t13099990044\n" +
            "测试6\t13099990055\n" +
            "ceshi1\t13099990215\n" +
            "hhd282w\t13099990987\n" +
            "测测测\t13099991012\n" +
            "11111\t13099992713\n" +
            "沟沟壑壑\t13099993002\n" +
            "ggjj\t13099996531\n" +
            "huwuwu\t13099997261\n" +
            "iwjdksc\t13099998265\n" +
            "cvvcvcvcvcvcvcv\t13099998345\n" +
            "测测测ooo\t13099998721\n" +
            "擦擦擦擦\t13099998727\n" +
            "cccccc\t13099999221\n" +
            "黄先生\t13101262453\n" +
            "黄先生\t13103432324\n" +
            "黄先生\t13103896829\n" +
            "黄先生\t13105867801\n" +
            "黄先生\t13107847075\n" +
            "黄先生\t13112001088\n" +
            "黄先生\t13113179589\n" +
            "黄先生\t13113253636\n" +
            "eee\t13114023987\n" +
            "黄先生\t13116473618\n" +
            "黄先生\t13117702805\n" +
            "黄先生\t13118287190\n" +
            "黄先生\t13122729017\n" +
            "黄先生\t13124194401\n" +
            "黄先生\t13124614280\n" +
            "黄先生\t13127295558\n" +
            "黄先生\t13127360219\n" +
            "黄先生\t13127972787\n" +
            "黄先生\t13129001588\n" +
            "黄先生\t13131935718\n" +
            "黄先生\t13131978873\n" +
            "黄先生\t13138481847\n" +
            "黄先生\t13142404267\n" +
            "黄先生\t13143953277\n" +
            "黄先生\t13144821690\n" +
            "黄先生\t13145333771\n" +
            "提提提、\t13148497726\n" +
            "黄先生\t13148612456\n" +
            "黄先生\t13149482990\n" +
            "黄先生\t13150700797\n" +
            "黄先生\t13152554098\n" +
            "黄先生\t13153800478\n" +
            "黄先生\t13155796970\n" +
            "dhdhdk\t13157433397\n" +
            "黄先生\t13157576829\n" +
            "黄先生\t13157954890\n" +
            "黄先生\t13159593097\n" +
            "黄先生\t13160521739\n" +
            "黄先生\t13160788442\n" +
            "yyy\t13160931021\n" +
            "testfort1388920\t13161796783\n" +
            "黄先生\t13163219098\n" +
            "黄先生\t13163382672\n" +
            "黄先生\t13164242682\n" +
            "黄先生\t13164579486\n" +
            "黄先生\t13170079313\n" +
            "黄先生\t13170251488\n" +
            "黄先生\t13170937763\n" +
            "黄先生\t13171861363\n" +
            "黄先生\t13182356063\n" +
            "黄先生\t13185853636\n" +
            "黄先生\t13186242369\n" +
            "黄先生\t13188228450\n" +
            "黄先生\t13188831300\n" +
            "黄先生\t13189828358\n" +
            "黄先生\t13194042505\n" +
            "黄先生\t13194269775\n" +
            "黄先生\t13196926901\n" +
            "黄先生\t13199965401\n" +
            "testfort200010\t13199999997\n" +
            "testfort10016342\t13199999998\n" +
            "testfort10005485\t13199999999\n" +
            "朱1百\t13210011001\n" +
            "朱龙俊2\t13210011333\n" +
            "方12\t13212365897\n" +
            "testfort10016333\t13222226666\n" +
            "ffff\t13303187152\n" +
            "fff4\t13303187156\n" +
            "kklaloo\t13325910532\n" +
            "testfort10011469\t13325910539\n" +
            "测试爱爱爱啊\t13412345678\n" +
            "testfort1561752\t13427535167\n" +
            "testfort10016360\t13455556666\n" +
            "进度\t13456789101\n" +
            "111测试\t13502662480\n" +
            "测试1\t13502665544\n" +
            "测试2\t13502665545\n" +
            "测试3\t13502665546\n" +
            "测试4\t13502665547\n" +
            "测试5\t13502665548\n" +
            "测试6\t13502665549\n" +
            "测试7\t13502665550\n" +
            "测试8\t13502665551\n" +
            "测试9\t13502665552\n" +
            "测试10\t13502665553\n" +
            "测试11\t13502665554\n" +
            "测试12\t13502665555\n" +
            "测试13\t13502665556\n" +
            "测试14\t13502665557\n" +
            "测试15\t13502665558\n" +
            "测试16\t13502665559\n" +
            "测试17\t13502665560\n" +
            "测试18\t13502665561\n" +
            "测试19\t13502665562\n" +
            "测试21\t13502665564\n" +
            "测试23\t13502665566\n" +
            "测试24\t13502665567\n" +
            "测试25\t13502665568\n" +
            "测试26\t13502665569\n" +
            "测试27\t13502665570\n" +
            "测试28\t13502665571\n" +
            "测试30\t13502665573\n" +
            "测试31\t13502665574\n" +
            "测试72\t13502665615\n" +
            "测试74\t13502665617\n" +
            "测试75\t13502665618\n" +
            "测试76\t13502665619\n" +
            "测试79\t13502665622\n" +
            "13502665627\t13502665627\n" +
            "测试100\t13502667544\n" +
            "测试200\t13502667545\n" +
            "测试201\t13502667546\n" +
            "测试202\t13502667547\n" +
            "测试203\t13502667548\n" +
            "测试204\t13502667549\n" +
            "测试205\t13502667550\n" +
            "测试206\t13502667551\n" +
            "测试207\t13502667552\n" +
            "测试208\t13502667553\n";
}