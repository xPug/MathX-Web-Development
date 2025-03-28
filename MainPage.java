// ``` java 

// Java implementation
// Backend functions for Main Page for website

public class MainPage {
  // dimensions for website; will change later to customize sizes
  public static final int WINDOW_WIDTH = 1920;
  public static final int WINDOW_HEIGHT = 1080;
  
  
  public MainPage() {
    super();
  }

  public void init() {
    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
  }

  // initialize windows and graphics here
  public void run() {
    System.out.println("Website Running");

  }

  public static void main(String[] args) {
    new MainPage().start();
  }
} 
