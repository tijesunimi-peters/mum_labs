import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class LinkedListThread<T> implements Runnable {
  private LinkedList<T> list;
  private StackThread<T> stack;
  Lock listLock;
  Lock stackLock;

  public LinkedListThread(Lock listLock, Lock stackLock) {
    list = new LinkedList<T>();
    this.listLock = listLock;
    this.stackLock = stackLock;
  }

  public void setStack(StackThread<T> stack) {
    this.stack = stack;
  }

  // public Lock getLock() {
  // return listLock;
  // }

  public T pop() {
    return list.pop();
  }

  public boolean add(T e) {
    boolean lockedList = false;
    boolean lockedStack = false;
    try {
      lockedList = listLock.tryLock(1000, TimeUnit.MILLISECONDS);
      lockedStack = stackLock.tryLock(1000, TimeUnit.MILLISECONDS);
      if (lockedList && lockedStack) {
        System.out.println("\nGot lock in List...");
        list.add(e);
        System.out.printf("\nList popped %s into Stack %s\n", e, stack);
        listLock.unlock();
        stackLock.unlock();
        System.out.println("\n\n");
        return true;
      } else {
        if (!lockedList)
          System.out.println("List: could not get list lock");
        else
          listLock.unlock();
        if (!lockedStack)
          System.out.println("List: could not get stack lock");
        else
          stackLock.unlock();
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      System.out.println("List thread trying again...");
    }

    return false;
  }

  @Override
  public String toString() {
    return list.toString();
  }

  @Override
  public void run() {
    System.out.println("List working....");
    // Iterator<T> iterator = list.iterator();
    int listIndex = 0;

    while (listIndex < list.size()) {
      System.out.print("In the LIST while loop...........");
      T popped = list.pop();
      if(stack.add(popped)) listIndex++;
    }

  }

}