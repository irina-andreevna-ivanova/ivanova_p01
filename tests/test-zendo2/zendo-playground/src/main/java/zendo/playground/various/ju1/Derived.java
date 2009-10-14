package zendo.playground.various.ju1;
public class Derived extends Base {

    @Override
    public void g() {
        System.out.println("Inside g() in Derived class");
    }

    public static void main(String... args){
        Base ob = new Derived();
        ob.f();
    }
}
