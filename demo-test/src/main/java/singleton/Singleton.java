package singleton;

/**
 * 单例获取
 *
 * @Author rex.wu
 * @Create 2017-10-26 11:25
 */
public class Singleton {
    private static Singleton singleton;

    /**
     *
     * @return
     */
    public static Singleton getSingletonInstance() {
        if (singleton != null) {
        } else {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            synchronized (Singleton.class) {
                singleton = new Singleton();

//                if (singleton == null) {
//                    singleton = new Singleton();
//                }
            }
        }
        return singleton;
    }

    private Singleton() {
    }
}
