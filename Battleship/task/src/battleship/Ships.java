package battleship;

import java.util.Scanner;

public class Ships {
    Field fld = new Field();
    private int x1;
    private int x2;
    private int y1;
    private int y2;

    private void input(int shipLength, String shipType) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char[] strToArray = str.toCharArray();
        try {
            if (strToArray[1] == '1' && strToArray[2] == '0') {
                x1 = strToArray[0] - 65;
                x2 = strToArray[4] - 65;
                y1 = 9;

                if (strToArray.length > 6 && strToArray[5] == '1' && strToArray[6] == '0') {
                    y2 = 9;
                } else {
                    y2 = strToArray[5] - 49;
                }
            } else if (strToArray.length > 5 && strToArray[4] == '1' && strToArray[5] == '0') {
                x1 = strToArray[0] - 65;
                x2 = strToArray[3] - 65;
                y1 = strToArray[1] - 49;
                y2 = 9;
            } else {
                x1 = strToArray[0] - 65;
                x2 = strToArray[3] - 65;
                y1 = strToArray[1] - 49;
                y2 = strToArray[4] - 49;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("\nError! You entered the wrong coordinates!");
            System.out.println("\nEnter the coordinates of the " + shipType + " (" + shipLength + " cells):\n");
            input(shipLength, shipType);
        }
    }

    public void placeShip(char[][] field) {
        fld.showField(field);
        System.out.println("\nEnter the coordinates of the Aircraft Carrier (5 cells):\n");
        int shipLength = 5;
        String shipType = "Aircraft Carrier";
        checkPlaceShip(field, shipLength, shipType);
        fld.showField(field);
        System.out.println("\nEnter the coordinates of the Battleship (4 cells):\n");
        shipLength = 4;
        shipType = "Battleship";
        checkPlaceShip(field, shipLength, shipType);
        fld.showField(field);
        System.out.println("\nEnter the coordinates of the Submarine (3 cells):\n");
        shipLength = 3;
        shipType = "Submarine";
        checkPlaceShip(field, shipLength, shipType);
        fld.showField(field);
        System.out.println("\nEnter the coordinates of the Cruiser (3 cells):\n");
        shipLength = 3;
        shipType = "Cruiser";
        checkPlaceShip(field, shipLength, shipType);
        fld.showField(field);
        System.out.println("\nEnter the coordinates of the Destroyer (2 cells):\n");
        shipLength = 2;
        shipType = "Destroyer";
        checkPlaceShip(field, shipLength, shipType);
        fld.showField(field);
        System.out.println("\nPress Enter and pass the move to another player\n" +
                "...");
    }

    private void checkPlaceShip(char[][] field, int shipLength, String shipType) {
        do {
            input(shipLength, shipType);
            int checkShipLength;
            if (x1 != x2 && y1 != y2) {
                System.out.println("\nError! Wrong ship location! Try again:\n");
            } else if (y1 == y2) {
                checkShipLength = Math.abs(x1 - x2) + 1;
                if (checkShipLength != shipLength) {
                    System.out.println("\nError! Wrong length of the " + shipType + "! Try again:\n");
                } else if (fld.takePositionVert(field, shipLength, x1, x2, y1, y2) == 1) {
                    break;
                }
            } else {
                checkShipLength = Math.abs(y1 - y2) + 1;
                if (checkShipLength != shipLength) {
                    System.out.println("\nError! Wrong length of the " + shipType + "! Try again:\n");
                } else if (fld.takePositionHorizon(field, shipLength, x1, x2, y1, y2) == 1) {
                    break;
                }
            }
        } while (true);
    }
}