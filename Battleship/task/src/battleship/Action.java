package battleship;

import java.util.Scanner;

public class Action {
    Field fld = new Field();
    private int x;
    private int y;
    int player = 0;

    private void inputShot() {
        Scanner sc = new Scanner(System.in);
        try {
            String str = sc.nextLine();
            char[] strToArray = str.toCharArray();
            if (strToArray.length > 2 && strToArray[1] == '1' && strToArray[2] == '0') {
                x = strToArray[0] - 65;
                y = 9;
            } else if (strToArray.length > 2) {
                System.out.println("\nError! You entered the wrong coordinates! Try again:\n");
            } else {
                x = strToArray[0] - 65;
                y = strToArray[1] - 49;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            if (player == 1) {
                playerOneTurn(Main.fieldOne, Main.fieldTwo, Main.fogOfWarTwo);
            } else if (player == 2) {
                playerTwoTurn(Main.fieldOne, Main.fieldTwo, Main.fogOfWarOne);
            }
        }
    }

    public void playerOneTurn(char[][] fieldOne, char[][] fieldTwo, char[][] fogOfWarTwo) {
        fld.showField(fogOfWarTwo);
        System.out.println("---------------------");
        fld.showField(fieldOne);
        player = 1;
        System.out.println("Player " + player + ", it's your turn:");
        takeShot(fieldTwo, fogOfWarTwo);
    }

    public void playerTwoTurn(char[][] fieldOne, char[][] fieldTwo, char[][] fogOfWarOne) {
        fld.showField(fogOfWarOne);
        System.out.println("---------------------");
        fld.showField(fieldTwo);
        player = 2;
        System.out.println("Player " + player + ", it's your turn:");
        takeShot(fieldOne, fogOfWarOne);
    }

    private void takeShot(char[][] field, char[][] fogOfWar) {
        try {
            inputShot();
            if (field[x][y] == '~') {
                field[x][y] = 'M';
                fogOfWar[x][y] = 'M';
                fld.showField(fogOfWar);
                System.out.println("\nYou missed!");
            } else if (field[x][y] == 'O') {
                field[x][y] = 'X';
                fogOfWar[x][y] = 'X';
                fld.showField(fogOfWar);
                if (statusOfGame(field)) {
                    System.out.println("\nYou sank the last ship. You won. Congratulations!");
                } else if (statusOfShip(field) == 1) {
                    System.out.println("\nYou hit a ship!");
                } else {
                    System.out.println("\nYou sank a ship!");
                }
            } else if (field[x][y] == 'X') {
                System.out.println("\nYou are already taken that shot");
                fld.showField(fogOfWar);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("\nError! You entered the wrong coordinates!");
            takeShot(field, fogOfWar);
        }
    }

    private int statusOfShip(char[][] field) {
        int check = 0;
        if (x != 0) {
            if (field[x - 1][y] == 'O') {
                check = 1;
            }
        }
        if (x != 9) {
            if (field[x + 1][y] == 'O') {
                check = 1;
            }
        }
        if (y != 0) {
            if (field[x][y - 1] == 'O') {
                check = 1;
            }
        }
        if (y != 9) {
            if (field[x][y + 1] == 'O') {
                check = 1;
            }
        }
        if (check == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public boolean statusOfGame(char[][] field) {
        int count = 0;
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; j++) {
                if (field[i][j] == 'X') {
                    count += 1;
                }
            }
        }
        return count == 17;
    }
}