package HighConcurrency;

import java.util.concurrent.TimeUnit;

/**
 * 无Volatile情况，运行结果：
 * 1.修改Flag为：true
 * 2.
 *
 *
 * @author ：kailu
 */
public class VolatileTest {
    final static int MAX=5;
    static  int init_value=0;
    public static void main(String[] args){
        new Thread(() ->
        {
            //程序能进入到if判断中，怀疑是localValue赋值进工作副本从主内存中读取一次，再在工作副本中创建init_value时又从主内存读取，但此时主内存中的值已被线程2修改。
            int localValue =init_value;
            while(localValue<MAX){
                if(init_value!=localValue){
                    System.out.println("The init_value is updated "+init_value);
                    localValue=init_value;
                }
                //一旦后面加了输出，不加volatile,程序也不会死循环，怀疑时输出语句中的锁导致的，待研究
            }
        }
                ,"Reader").start();

        new Thread(()->
        {
            int localValue =init_value;
            while (localValue<MAX){
                System.out.println("The init_value will be changed to "+(++localValue));
                init_value=localValue;
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            },"Updater").start();
    }

}
