package base;

/**
 * @Author dell
 * @Create 2017-11-09 13:40
 */
public class RefTest {


    public static void main(String[] args) {
        // 原始类型只是值传递
//        int a = 5;
//        System.out.println(a);
//        incr(a);
//        System.out.println(a);
//
//        System.out.println("--------------");
//
//        // 引用类型值地址传递，在其他处改变了引用，则原来的值也改变了
//        StringBuffer sb = new StringBuffer();
//        sb.append("1");
//        System.out.println(sb.toString());
//        append(sb);
//        System.out.println(sb.toString());

        Integer a1 = 1;
        Integer b1 = 1;
        System.out.println(a1 == b1);

        String a2 = "abc";
        String b2 = "abc";
        System.out.println(a2 == b2);

    }

    private static void incr(int num) {
        num++;
    }

    private static void append(StringBuffer sb) {
        sb.append("2");
        sb = null;


    }
}
