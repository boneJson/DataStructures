package com.zxk.algorithm.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

//贪心算法
public class GreedyAlgorithm {
    public static void main(String[] args) {
        //创建广播电台,放入到 Map
        HashMap<String, HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();
        //将各个电台放入到 broadcasts
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
        //加入到 map
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        //allAreas 存放所有的地区
        HashSet<String> allAreas = new HashSet<String>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        //选择结果
        List<String> selects = new ArrayList<String>();

        //存放最大结果集(即本轮选择的电台和其新覆盖的地区)
        String bigKey = "";
        HashSet<String> bigSet =null;
        //存放遍历的结果与全地区的交集(即能覆盖的新地区)
        HashSet<String> tempSet =null;

        //只要全地区还有元素,说明仍有地区未覆盖
        while (allAreas.size() > 0) {

            //遍历广播电台的同时,获取与全地区的交集,贪心:暂存本轮交集最大的电台
            for (String key : broadcasts.keySet()) {
                tempSet = broadcasts.get(key);
                tempSet.retainAll(allAreas);
                if (bigSet==null||broadcasts.get(key).size() > bigSet.size()) {
                    bigSet= tempSet;
                    bigKey = key;
                }
            }

            //收集本轮的电台选择结果并删除全地区中该电台覆盖的地区
            selects.add(bigKey);
            allAreas.removeAll(bigSet);

        }
        System.out.println(selects);
    }
}
