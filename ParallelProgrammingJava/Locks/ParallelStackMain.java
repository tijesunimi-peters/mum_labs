package locks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParallelStackMain {
  public static void main(String[] args) {
    ParallelStack stack = new ParallelStack();
    List<Thread> threads = new ArrayList<Thread>();

    for(int i = 0; i < 100; i++) {
      Thread thread = new Thread(new ParallelStackThread(stack));
      thread.setName("Th<>"+i);
      threads.add(thread);
    }

    
    Iterator<Thread> iterator = threads.iterator();
    
    while(iterator.hasNext()) {
      Thread thread = iterator.next();
      thread.start();
    }

    try {
      iterator = threads.iterator();
      while(iterator.hasNext()) {
        iterator.next().join();
      }
    } catch(Exception ex) {
      ex.printStackTrace();
    }

    System.out.printf("Final: %s, size: %d\n", stack, stack.size());
  }
}