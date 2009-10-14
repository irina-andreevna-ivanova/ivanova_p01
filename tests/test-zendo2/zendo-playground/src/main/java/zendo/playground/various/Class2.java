package zendo.playground.various;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Class2 {
  private static class A implements Serializable {
    private int x = 5;
    
    private void writeObject(ObjectOutputStream outputStream) throws IOException {
      System.out.println("In A.writeObject() before default");
      outputStream.defaultWriteObject();
      System.out.println("In A.writeObject() after default");
    }
  }
  
  private static class B extends A {
    private int y = 6;
    
    private void writeObject(ObjectOutputStream outputStream) throws IOException {
      System.out.println("In B.writeObject() before default");
      outputStream.defaultWriteObject();
      System.out.println("In B.writeObject() after default");
    }
  }
  
  public static void main(String[] args) throws Exception {
    B b = new B();
    ObjectOutputStream outputStream = new ObjectOutputStream(new ByteArrayOutputStream());
    outputStream.writeObject(b);
    outputStream.close();
  }
}
