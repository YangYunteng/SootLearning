import soot.*;
import soot.baf.BafBody;
import soot.baf.EnterMonitorInst;
import soot.baf.ExitMonitorInst;
import soot.baf.Inst;
import soot.jimple.*;
import soot.options.Options;
import soot.util.Chain;

import java.io.File;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class Utils {
    private final static int NopStmtType = 1;
    private final static int IdentityStmtType = 2;
    private final static int AssignStmtType = 3;
    private final static int IfStmtType = 4;
    private final static int GoToStmtType = 5;
    private final static int TableSwitchStmtType = 6;
    private final static int LookUpSwitchStmtType = 7;
    private final static int InvokeStmtType = 8;
    private final static int ReturnStmtType = 9;
    private final static int ReturnVoidStmtType = 10;
    private final static int EnterMonitorStmtType = 11;
    private final static int ExitMonitorStmtType = 12;
    private final static int ThrowStmtType = 13;
    private final static int RetStmtType = 14;
    private final static int BreakpointStmtType = 15;

//    public static void main(String[] args) {
//        initialSoot();
//        SootClass appClass = Scene.v().getSootClass("Circle");
//        SootMethod areaMethod = appClass.getMethod("int area(boolean)");
//        getJimpleBodyInfo(areaMethod);
//        soot.Main.main(args);
//    }

    public void getJimpleBodyInfo(SootMethod sootMethod) {
        JimpleBody jb = (JimpleBody) sootMethod.getActiveBody();
        //Trap Information, exceptions in method
        Chain<Trap> traps = jb.getTraps();
        //locals in method
        Chain<Local> locals = jb.getLocals();
        //units in method
        Chain<Unit> units = jb.getUnits();
        for (Unit unit : jb.getUnits()) {
            Stmt st = (Stmt) unit;
            System.out.println("-------Statement and Type---------");
            System.out.println(st);
            int type = getStmtType(st);
            System.out.println("---------UseBox---------");
            List<ValueBox> useBoxes = unit.getUseBoxes();
            for (ValueBox vb : useBoxes) {
                System.out.println(vb);
            }
            System.out.println("---------DefBox---------");
            List<ValueBox> defBoxes = unit.getDefBoxes();
            for (ValueBox vb : defBoxes) {
                System.out.println(vb);
            }
            System.out.println("---BoxesPointingToThis---");
            List<UnitBox> boxesPointingToThis = unit.getBoxesPointingToThis();
            for (UnitBox unitBox : boxesPointingToThis)
                System.out.println(unitBox);
            System.out.println("--------UnitBoxes--------");
            List<UnitBox> unitBoxes = unit.getUnitBoxes();
            for (UnitBox unitBox : unitBoxes)
                System.out.println(unitBox);
            System.out.println("--------ExistBranch-------");
            //return boolean
            //Returns true if execution after this statement may continue at the following statement.
            System.out.println(unit.fallsThrough());
            //Returns true if execution after this statement does not necessarily continue at the following statement.
            System.out.println(unit.branches());
            System.out.println();
        }
    }

    public int getStmtType(Stmt st) {
        int res = -1;
        if (st instanceof NopStmt) {
            res = NopStmtType;
            System.out.println("NopStmt");
        }

        if (st instanceof IdentityStmt) {
            res = IdentityStmtType;
            System.out.println("IdentityStmt");
        }

        if (st instanceof AssignStmt) {
            res = AssignStmtType;
            System.out.println("AssignStmt");
        }

        if (st instanceof IfStmt) {
            res = IfStmtType;
            System.out.println("IfStmt");
        }

        if (st instanceof GotoStmt) {
            res = GoToStmtType;
            System.out.println("GotoStmt");
        }

        if (st instanceof TableSwitchStmt) {
            res = TableSwitchStmtType;
            System.out.println("TableSwitchStmt");
        }

        if (st instanceof LookupSwitchStmt) {
            res = LookUpSwitchStmtType;
            System.out.println("LookupSwitchStmt");
        }

        if (st instanceof InvokeStmt) {
            res = InvokeStmtType;
            System.out.println("InvokeStmt");
        }

        if (st instanceof ReturnStmt) {
            res = ReturnStmtType;
            System.out.println("ReturnStmt");
        }

        if (st instanceof ReturnVoidStmt) {
            res = ReturnVoidStmtType;
            System.out.println("ReturnVoidStmt");
        }

        if (st instanceof EnterMonitorStmt) {
            res = EnterMonitorStmtType;
            System.out.println("EnterMonitorStmt");
        }

        if (st instanceof ExitMonitorStmt) {
            res = ExitMonitorStmtType;
            System.out.println("ExitMonitorStmt");
        }

        if (st instanceof ThrowStmt) {
            res = ThrowStmtType;
            System.out.println("ThrowStmt");
        }

        if (st instanceof RetStmt) {
            res = RetStmtType;
            System.out.println("RetStmt");
        }

        if (st instanceof BreakpointStmt) {
            res = BreakpointStmtType;
            System.out.println("BreakpointStmt");
        }

        return res;
    }

    //此处已经配置了-pp
    public void initialSoot() {
        G.reset();
        Options.v().set_prepend_classpath(true);
        Options.v().set_allow_phantom_refs(true);
        Options.v().set_output_format(Options.output_format_jimple);
        Options.v().set_process_dir(Collections.singletonList("./assets/Test1"));
        Options.v().set_whole_program(true);
        Options.v().set_output_dir("./output");
        Scene.v().loadNecessaryClasses();
        PackManager.v().runPacks();
//        soot.Main.main(new String[]{});
    }
}
