package main;

import java.util.Scanner;

public class Main {
    /**
     * This is the main function, it runs the code.
     * @param args Input from command line.
     */
    public static void main(String[] args) {
        Room room = new Room();
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        String input;
        int num;

        do {
            System.out.println("Would you like to generate"
                    + " a room or treasure?");
            System.out.println("(1 for room 2 for treasure)");
            num = sc.nextInt();
            if (num == 1) {
                input = room.getInput("entire room");


                //if the user wants to generate a completely random room
                if (input.equalsIgnoreCase("y")) {
                    room.randomShape();
                    room.randomContents();
                } else if (input.equalsIgnoreCase("n")) {
                    do {
                        input = room.getInput("chamber shape");
                        if (input.equalsIgnoreCase("y")) {
                            room.randomShape();
                        } else if (input.equalsIgnoreCase("n")) {
                            room.setShape();
                        } else {
                            System.out.println("Enter a valid character");
                        }
                    } while (!(input.equalsIgnoreCase("y")
                            || input.equalsIgnoreCase("n")));

                    do {
                        //contents
                        input = room.getInput("chamber contents");
                        if (input.equalsIgnoreCase("y")) {
                            room.randomContents();
                        } else if (input.equalsIgnoreCase("n")) {
                            room.setContents();
                        } else {
                            System.out.println("Enter a valid character");
                        }
                    } while (!(input.equalsIgnoreCase("y")
                            || input.equalsIgnoreCase("n")));

                    if (room.getContentsDescription()
                            .equalsIgnoreCase("monster only")) {
                        room.generateMonster();
                    } else if (room.getContentsDescription()
                            .equalsIgnoreCase("monster and treasure")) {
                        room.generateTreasure();
                        room.generateMonster();
                    }
                } else if (room.getContentsDescription()
                        .equalsIgnoreCase("stairs")) {
                    room.generateStairs();
                } else if (room.getContentsDescription()
                        .equalsIgnoreCase("trap")) {
                    room.generateTrap();
                } else if (room.getContentsDescription()
                        .equalsIgnoreCase("treasure")) {
                    room.generateTreasure();
                }
            } else if (num == 2) {
                room.generateTreasure();
            } else {
                main.valid();
            }
        } while (!(num == 1 || num == 2));

        if (num == 1) {
            room.printRoom();
        } else {
            room.printTreasure();
        }
    }

    /**
     * Prints a line.
     */
    private void valid() {
        System.out.println("Enter a valid number.");
    }
}
