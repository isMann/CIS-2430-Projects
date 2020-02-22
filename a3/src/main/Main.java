package main;

public final class Main {
    /**
     * This is the main function, it runs the code.
     * @param args Input from command line.
     */
    public static void main(String[] args) {
        Level level = new Level();
        level.makeChambers(5);
        level.linkDoors();
        System.out.println(level.getDescription());
    }
    public Main() {
    }
}
