package override;

/**
 * @author dell
 * @version 1.0.0
 * @create 2018-12-04 16:10
 */
public class Child extends Parent {
    public String mame = "Child Class";
    public static Integer age = 24;

    public Child() {
        System.out.println("new Child Class...");
    }

    public void hello() {
        System.out.println("I am Child Class...");
    }
}