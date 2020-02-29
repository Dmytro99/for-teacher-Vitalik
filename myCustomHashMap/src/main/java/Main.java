import logic.MyCustomHashMap;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        MyCustomHashMap<Integer, String> integerStringMyCustomHashMap = new MyCustomHashMap<Integer, String>();
        integerStringMyCustomHashMap.put(1, "aaaaaa");
        integerStringMyCustomHashMap.put(2, "bbbbbb");
        integerStringMyCustomHashMap.put(3, "bbbbbb");
        integerStringMyCustomHashMap.put(4, "bbbbbb");
        integerStringMyCustomHashMap.put(5, "bbbbbb");
        integerStringMyCustomHashMap.put(6, "bbbbbb");
        integerStringMyCustomHashMap.put(234, "bbbbbb");
        integerStringMyCustomHashMap.put(52352, "bbbbbb");
        integerStringMyCustomHashMap.put(263234, "bbbbbb");
//        integerStringMyCustomHashMap.put(11, "bbbbbb");
//        integerStringMyCustomHashMap.put(2345, "bbbbbb");
//        integerStringMyCustomHashMap.put(12, "bbbbbb");
//        integerStringMyCustomHashMap.put(13, "bbbbbb");
//        integerStringMyCustomHashMap.put(14, "bbbbbb");
//        integerStringMyCustomHashMap.put(645, "bbbbbb");
//        integerStringMyCustomHashMap.put(543, "bbbbbb");


        System.out.println(integerStringMyCustomHashMap.size());
        System.out.println(integerStringMyCustomHashMap.keySet());

//
//        HashMap hashMap = new HashMap();
//        hashMap.put(1, "jbk");
//        hashMap.put(4, "bbbbbb");
//        hashMap.put(2, "bbbbbb");
//        hashMap.put(3, "bbbbbb");
//        hashMap.put(5, "bbbbbb");
//        hashMap.put(6, "bbbbbb");
//
//        hashMap.put(11, "bbbbbb");
//        hashMap.put(12, "bbbbbb");
//        hashMap.put(3454678, "bbbbbb");
//        hashMap.put(141, "bbbbbb");
//        System.out.println(hashMap.keySet());
////        integerStringMyCustomHashMap.remove(2);
//
//
//        System.out.println(integerStringMyCustomHashMap.get(1));
////        System.out.println(integerStringMyCustomHashMap.containsKey(1));
////        System.out.println(integerStringMyCustomHashMap.containsKey(2));
    }
}
