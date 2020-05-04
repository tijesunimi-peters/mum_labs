// StringPatternMatch

import java.util.concurrent.locks.Lock;

public class StringPatternMatch implements Runnable {
  private int start;
  private int end;
  private char[] pattern;
  private char[] characters;
  private final Lock lock;

  public StringPatternMatch(int start, int end, char[] pattern, char[] characters, Lock writerLock) {
    this.start = start;
    this.end = end;
    this.pattern= pattern;
    this.characters = characters;
    this.lock = writerLock;
  }

  @Override
  public void run() {
    for(int i = start; i < end - pattern.length + 1; i++) {
      int count = 0;

      for(int j = 0; j < pattern.length; j++) {
        if(characters[i + j] == pattern[j]) count++;
      }

      if(count == pattern.length) {
        lock.lock();
        StringPatternMatchMain.match++;
        lock.unlock();
      }
    }
  }
}