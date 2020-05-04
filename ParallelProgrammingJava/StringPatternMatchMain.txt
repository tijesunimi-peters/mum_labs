// StringPatternMatch Main

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StringPatternMatchMain {
  private static int n = 20000000;
  private static int m = 6;
  public static int match = 0;
  private static char[] characters;
  private static char[] pattern;
  private static List<Thread> threads = new ArrayList<Thread>();
  private static final Lock writerLock = new ReentrantLock();

  public static void generateChars() {
    characters = new char[n];
    pattern = new char[m];
    Random rnd = new Random();
    
    for(int i = 0; i < n; i++) {
      characters[i] = (char) (rnd.nextInt(26) + 'a');
      if(i < m) pattern[i] = (char) (rnd.nextInt(26) + 'a');
    }
  }

  public static void main(String[] args) {
    generateChars();

    int cores = Runtime.getRuntime().availableProcessors();
    int chunk = n/cores;

    for(int i = 0; i < cores; i++) {
      int start = i == 0 ? i * chunk : (i * chunk) + 1;
      int end = (i + 1) * chunk;
      StringPatternMatch stringPatternMatch = new StringPatternMatch(start, end, pattern, characters, writerLock);
      Thread thread = new Thread(stringPatternMatch);
      threads.add(thread);
    }

    for(Thread thread: threads) {
      thread.start();
    }

    try {
      for(Thread thread: threads) thread.join();
    } catch(Exception ex) {
      ex.printStackTrace();
    }

    System.out.println("Cores: " + cores);
    System.out.println("Pattern: " + Arrays.toString(pattern));
    System.out.println("Matches: " + match);
  }
}