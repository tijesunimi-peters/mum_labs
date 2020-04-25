import data.DataBuilder;

public class Application {
   public static void main(String[] args) {
        Generator generator = new Generator();
        generator.run();

        try {
            DataBuilder.setUp();
            CommandLineApp.init();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
   }
}
