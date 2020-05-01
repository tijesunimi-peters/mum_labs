import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
// import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ParallelStack {
  private Integer[] internalStack;
  private int top = 9;
  private int currentPos;
  private final ReentrantReadWriteLock stackLock = new ReentrantReadWriteLock(true);
  private final Lock readLock = stackLock.readLock();
  private final Lock writeLock = stackLock.writeLock();

  public ParallelStack() {
    this.internalStack = new Integer[10];
    currentPos = 9;
  }

  private void resize() {
    final Integer[] newStack = new Integer[internalStack.length * 2];
    System.arraycopy(internalStack, 0, newStack, top + 1, internalStack.length);
    currentPos = internalStack.length - 1;
    internalStack = newStack;
    top = internalStack.length - 1;
  }

  public void push(final int value) {
    try {
      if(writeLock.tryLock(1000, TimeUnit.MILLISECONDS)) {
        if (currentPos < 0)
          resize();
        internalStack[currentPos] = value;
        currentPos--;
        writeLock.unlock();
      }
    } catch(Exception ex) {
      ex.printStackTrace();
    } finally {
    }
  }

  public Integer pop() {
    boolean lockedRead = false;
    boolean lockedWrite = false;
    try {
      lockedRead = readLock.tryLock(1000, TimeUnit.MILLISECONDS);
      lockedWrite = writeLock.tryLock(1000, TimeUnit.MILLISECONDS);
      if(lockedRead && lockedWrite) {
        if (top == currentPos)
          return null;
        Integer value = internalStack[top];
        top--;
        return value;
      }
    } catch(Exception ex) {
      ex.printStackTrace();
    } finally {
      if(lockedRead) readLock.unlock();
      if(lockedWrite) writeLock.unlock();
    }
    return null;
  }

  public void clear() {
    try {
      if(writeLock.tryLock(1000, TimeUnit.MILLISECONDS)) {
        internalStack = new Integer[10];
        top = currentPos = 9;
        writeLock.unlock();
      };
    } catch(Exception ex) {
      ex.printStackTrace();
    } finally {
    }
  }

  public int size() {
    return top - currentPos;
  }

  @Override
  public String toString() {
    int limit = top - currentPos;
    Integer[] values = new Integer[limit];
    System.arraycopy(internalStack, currentPos + 1, values, 0, limit);
    return Arrays.toString(values);
  }

  public static void main(final String[] args) {
    final ParallelStack stack = new ParallelStack();
    stack.push(1);
    stack.push(4);
    stack.push(5);
    stack.push(2);
    stack.push(3);
    stack.push(7);
    stack.push(7);
    stack.push(7);
    stack.push(89);
    stack.push(71);
    stack.push(65);
    stack.push(70);

    System.out.println(stack);
    // System.out.println(stack.pop());
    // System.out.println(stack.pop());
    // System.out.println(stack.pop());
    // System.out.println(stack.pop());
    // System.out.println(stack.pop());
    // System.out.println(stack.pop());
    // System.out.println(stack.pop());
    // System.out.println(stack.pop());
    // System.out.println(stack.pop());
    // System.out.println(stack.pop());
    // System.out.println(stack.pop());
    // System.out.println(stack.pop());
    // System.out.println(stack.pop());
    // System.out.println(stack.pop());
    // System.out.println(stack.pop());
    // System.out.println(stack.pop());

    // stack.clear();
    // System.out.println(stack);
    // stack.push(70);
    // stack.push(65);
    // stack.push(71);
    // stack.push(89);
    // stack.push(7);
    // stack.push(7);
    // stack.push(7);
    // stack.push(3);
    // stack.push(2);
    // stack.push(5);
    // stack.push(4);
    // System.out.println(stack);
  }

}