package com.wf2311.jfeng;

import com.alibaba.fastjson.JSON;
import com.wf2311.jfeng.exception.CustomException;
import com.wf2311.jfeng.lang.CollectionUtils;
import lombok.Data;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author wangfeng
 */
public class ShiJiTest {

    @Data
    class Agentia {
        /**
         * 序号
         */
        int id;
        /**
         * 是否是毒药
         */
        boolean isPoison = false;
    }

    /**
     * 生成1000个试剂样品，其中有且只有一瓶毒药
     *
     * @param posionPosition 毒药的位置
     * @return
     */
    private List<Agentia> generateSpecimen(int posionPosition) {
        return IntStream.rangeClosed(1, 1000).mapToObj(i -> {
            Agentia a = new Agentia();
            a.setId(i);
            a.setPoison(i == posionPosition);
            return a;
        }).collect(Collectors.toList());
    }

    /**
     * 判断混合试剂是否有毒
     *
     * @param list 混合试剂
     */
    private boolean isPoisonous(List<Agentia> list) {
        return list.stream().anyMatch(Agentia::isPoison);
    }

    /**
     * 把试剂平均混合成指定等份
     *
     * @param source 总试剂
     * @param size   要混合成的份数
     * @return
     */
    private List<List<Agentia>> mix(List<Agentia> source, int size) {
        return CollectionUtils.splitList(source, size);
    }

    /**
     * 检查试剂
     *
     * @param source  原始样品
     * @param miceNum 小白鼠数量
     */
    public int[] detect(List<Agentia> source, int miceNum) {
        List<List<Agentia>> lists = mix(source, miceNum);
        for (List<Agentia> mix : lists) {
            if (isPoisonous(mix)) {
                if (miceNum == 0) {
                    throw new CustomException("小白鼠已全部死亡，未能找出毒药！");
                }
                miceNum--;
//                System.out.println(String.format("确定毒药在%d到第%d瓶之间，此时还剩余小白鼠数量：%d", mix.get(0).getId(), mix.get(mix.size() - 1).getId(), miceNum));
                if (mix.size() == 1) {
                    return new int[]{mix.get(0).getId(), miceNum};
                }
                return detect(mix, miceNum);
            }
        }
        throw new CustomException("未能找出毒药！");
    }

    @Test
    public void test1() {
        List<Agentia> list = generateSpecimen(1);
        int[] detect = detect(list, 10);
        System.out.println(String.format("已找到毒药位置：%d,剩余的小白鼠%d只", detect[0], detect[1]));
        System.out.println(JSON.toJSONString(detect));
    }

    @Test
    public void test2() {
        int[] array = IntStream.rangeClosed(1, 1000).map(i -> {
            List<Agentia> list = generateSpecimen(i);
            int[] detect = detect(list, 10);
            if (detect[1] == 8) {
                System.out.println(detect[0]);
            }
            return (10 - detect[1]);
        }).toArray();
        System.out.println("在1000种情况中");
        System.out.println("平均每次消耗老鼠数量：" + Arrays.stream(array).average().orElseGet(null));
        System.out.println("消耗老鼠数量的最少情况：" + Arrays.stream(array).min().orElseGet(null));
        System.out.println("消耗老鼠数量的最多情况：" + Arrays.stream(array).max().orElseGet(null));
    }
}
