// Main class
import pkg.ModifierTest;

class Child extends ModifierTest {
    void callParentProtected() {
        System.out.println("call my parent's protected method");
        super.messageProtected();
    }
}

public class Main {
    public static void main(String[] args) {
        ModifierTest modifierTest = new ModifierTest();
        modifierTest.messageOutside();

        Child child = new Child();
        child.callParentProtected();

    }
}

// ---------------------------------------------------------------------- ("-" * 70)

// pkg package 내 ModifierTest 클래스
package pkg;

public class ModifierTest {
    private void messageInside() {
        System.out.println("This is a private modifier");
    }

    public void messageOutside() {
        System.out.println("This is a public modifier");
        messageInside();
    }

    protected  void messageProtected() {
        System.out.println("This is a protected modifier");
    }
}
