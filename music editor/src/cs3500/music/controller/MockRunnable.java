package cs3500.music.controller;

/**
 * Class for testing a mock runnable.
 */
public class MockRunnable implements IMockRunnable {
  private StringBuilder s;

  /**
   * constructor for a mock runnable.
   */
  public MockRunnable() {
    this.s = new StringBuilder();
  }

  @Override
  public void run() {
    System.out.print("here");
    s.append("M key released!");

  }


  @Override
  public String getString() {
    return s.toString();
  }
}
