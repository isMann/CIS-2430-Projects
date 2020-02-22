package main;

public final class Main {
    /**
     * This is the main function, it runs the code.
     * @param args Input from command line.
     */
    public static void main(String[] args) {
        Level level;
        do {
            level = new Level();
            int nextPassage = 0;
            level.makeChambers(5);
            try {
                level.loopPassage();
            } catch (IndexOutOfBoundsException e) {
                continue;
            }
        } while (!level.getDescription().contains("Chamber 5"));
        System.out.println(level.getDescription());
    }

    private Main() {
    }
}
