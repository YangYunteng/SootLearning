import soot.Scene;
import soot.SootClass;
import soot.SootMethod;

public class Main {
    public static void main(String[] args) {
        Utils utils = new Utils();
        utils.initialSoot();
        SootClass sootClass = Scene.v().getSootClass("Circle");
        SootMethod sootMethod = sootClass.getMethod("int area(boolean)");
        utils.getJimpleBodyInfo(sootMethod);
    }
}