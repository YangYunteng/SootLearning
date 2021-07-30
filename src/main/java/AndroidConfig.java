import soot.G;

public class AndroidConfig {
    public static void main(String[] args) {
        System.out.println(System.getenv("ANDROID_PLATFORM"));
    }

    public static void initSoot() {
        G.reset();

    }
}
