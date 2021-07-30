public class TestMain {
    public static void main(String[] args) {
        int start = 1;
        C(start);
    }

    public static void A() {
        //annomationTest
        String name = "A";
        System.out.println("inside");
    }

    public static void B() {
        String name = "B";
        System.out.println("inside");
    }

    public static void C(int name) {
        if (name > 1) {
            A();
        } else {
            B();
        }
    }
}
