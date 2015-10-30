package nashorn;

public class Message {
    public String title;
    public String content;

    public void printSelf() {
        System.out.println(title);
        System.out.println(content);
    }

    public static void proIn() {
        System.out.println("Message load");
    }
}
