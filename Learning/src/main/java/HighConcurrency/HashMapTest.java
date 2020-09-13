package HighConcurrency;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：kailu
 */
public class HashMapTest {

       // static HashMap<String,String> map=new HashMap<String, String>();
       static Map<String,String> map= Collections.synchronizedMap(new HashMap<String,String>());
        public static class AddThread implements Runnable{
            int start=0;
            public AddThread(int start) {
                this.start=start;
            }

            public  void run() {
                for(int i=start;i<10000;i++){

                    map.put(Integer.toString(i),Integer.toBinaryString(i));
                }

            }
        }

        public static void main(String[] args) throws InterruptedException {
            Thread t1=new Thread(new HashMapTest.AddThread(0));
            Thread t2=new Thread(new HashMapTest.AddThread(1));
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println(map.size());

        }
}
