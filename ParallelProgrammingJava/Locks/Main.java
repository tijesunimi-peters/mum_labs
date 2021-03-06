package locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
  private static Lock listLock = new ReentrantLock(); ;
  private static Lock stackLock = new ReentrantLock();;
  public static void main(String[] args) {
    LinkedListThread<String> list = new LinkedListThread<String>(listLock, stackLock);
    StackThread<String> stack = new StackThread<String>(listLock, stackLock);
    list.setStack(stack);
    stack.setList(list);

    list.add("also");
    list.add("This");
    list.add("is");
    list.add("life");

    stack.add("great");
    stack.add("going");
    stack.add("is");
    stack.add("Things");

    Thread listThread = new Thread(list);
    Thread stackThread = new Thread(stack);
    listThread.setName("listThread");
    stackThread.setName("stackThread");
    listThread.start();
    stackThread.start();

    try {
      listThread.join();
      stackThread.join();
    } catch(Exception ex) {
      ex.printStackTrace();
    }

    System.out.printf("Stack: %s\n", stack);
    System.out.printf("List: %s\n", list);

  }
}