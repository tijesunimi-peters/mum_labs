
public class ParallelStackThread implements Runnable {
  private ParallelStack stack;

  public ParallelStackThread(ParallelStack stack) {
    this.stack = stack;
  }

  @Override
  public void run() {
    int count = 1;
    while(count <= 10) {
      int index = ((int) Math.ceil(Math.random() * 10)) % 2;
      if(index == 1) {
        stack.push((int) (count + Math.random() * 10));
      } else {
        stack.pop();
      }
      count++;
    }
  }
}