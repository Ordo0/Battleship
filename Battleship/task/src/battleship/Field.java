package battleship;

public class Field {
    public void createField() {
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; j++) {
                Main.fieldOne[i][j] = '~';
                Main.fieldTwo[i][j] = '~';
                Main.fogOfWarOne[i][j] = '~';
                Main.fogOfWarTwo[i][j] = '~';
            }
        }
    }

    public void showField(char[][] field) {
        int size = 10;
        System.out.print(" ");
        for (int i = 1; i < size + 1; ++i) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            int a = 65 + i;
            char b = (char) a;
            System.out.print(b + " ");
            for (int j = 0; j < size; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int takePositionHorizon(char[][] field, int shipLength, int x1, int x2, int y1, int y2) {
        int check = 0;

        if (y1 > y2) {
            int yT1 = y1;
            y1 = y2;
            y2 = yT1;
        }

        int c = 1;
        if (y2 == 9) {
            c = 0;
        }

        int j = 0;
        if (y1 != 0) {
            j = 1;
        }

        if (x1 != 0) {
            for (int i = -j; i < shipLength + c; i++) {
                if (field[x1 - 1][y1 + i] == 'O') {
                    check = 1;
                    break;
                }
            }
        }

        if (x1 != 9) {
            for (int i = j; i < shipLength + c; i++) {
                if (field[x1 + 1][y1 + i] == 'O') {
                    check = 1;
                    break;
                }
            }
        }

        if (field[x1][y1 - j] == 'O' || field[x2][y2 + c] == 'O') {
            check = 1;
        }


        for (int i = 0; i < shipLength; i++) {
            if (field[x1][y1 + i] == 'O') {
                check = 1;
                break;
            }
        }

        if (check == 1) {
            System.out.println("\nError! You placed it too close to another one. Try again:\n");
            return 0;
        } else {
            for (int i = 0; i < shipLength; i++) {
                field[x1][y1 + i] = 'O';
            }
            return 1;
        }
    }

    public int takePositionVert(char[][] field, int shipLength, int x1, int x2, int y1, int y2) {
        int check = 0;
        if (x1 > x2) {
            int xT1 = x1;
            x1 = x2;
            x2 = xT1;
        }
        int c = 1;
        if (x2 == 9) {
            c = 0;
        }

        int j = 0;
        if (x1 != 0) {
            j = 1;
        }

        if (y1 != 0) {
            for (int i = -j; i < shipLength + c; i++) {
                if (field[x1 + i][y1 - 1] == 'O') {
                    check = 1;
                    break;
                }
            }
        }

        if (y1 != 9) {
            for (int i = j; i < shipLength + c; i++) {
                if (field[x1 + i][y1 + 1] == 'O') {
                    check = 1;
                    break;
                }
            }
        }

        if (field[x1 - j][y1] == 'O' || field[x2 + c][y2] == 'O') {
            check = 1;
        }


        for (int i = 0; i < shipLength; i++) {
            if (field[x1 + i][y1] == 'O') {
                check = 1;
                break;
            }
        }

        if (check == 1) {
            System.out.println("\nError! You placed it too close to another one. Try again:\n");
            return 0;
        } else {
            for (int i = 0; i < shipLength; i++) {
                field[x1 + i][y1] = 'O';
            }
            return 1;
        }
    }
}