package design.pattern.factory;

/**
 * @author dell
 * @version 1.0.0
 * @create 2018-10-19 13:52
 */
public class SelfSessionFactory {

    public static SelfSession createSession() {


        return new SelfSession();
    }


    public static void main(String[] args) {
        System.out.println(new SelfSession());
        System.out.println(SelfSessionFactory.createSession());
    }
}