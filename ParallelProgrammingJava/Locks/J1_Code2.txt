import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
// import java.util.concurrent.locks.ReentrantLock;

public class StackThread<T> implements Runnable {
  private LinkedList<T> stack;
  private LinkedListThread<T> list;
  private Lock stackLock;
  private Lock listLock;

  public StackThread(Lock listLock, Lock stackLock) {
    stack = new LinkedList<T>();
    this.stackLock = stackLock;
    this.listLock = listLock;
  }

  public void setList(LinkedListThread<T> list) {
    this.list = list;
  }

  // public Lock getLock() {
  // return stackLock;
  // }

  public boolean add(T var) {
    boolean lockedStack = false;
    boolean lockedList = false;

    try {
      lockedStack = stackLock.tryLock(1000, TimeUnit.MILLISECONDS);
      lockedList = listLock.tryLock(1000, TimeUnit.MILLISECONDS);
      if (lockedStack && lockedList) {
        System.out.println("Got lock in stack...");
        stack.push(var);
        System.out.printf("Stack popped %s into List %s\n", var, list);
        stackLock.unlock();
        listLock.unlock();
        System.out.println("\n\n");
        return true;
      } else {
        if (!lockedList)
          System.out.println("Stack: could not get list lock");
        else
          listLock.unlock();
        if (!lockedStack)
          System.out.println("Stack: could not get stack lock");
        else
          stackLock.unlock();
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      System.out.println("Stack thread trying again...");
    }

    return false;
  }

  public T pop() {
    return stack.pop();
  }

  @Override
  public String toString() {
    return stack.toString();
  }

  @Override
  public void run() {
    // Iterator<T> iterator = stack.iterator();

    int index = 0;
    while (index < stack.size()) {
      System.out.println("Stack working...");
      System.out.println("In the stack while loop.....................");
      T popped = stack.pop();
      if(list.add(popped)) index++;
    }
  }
}