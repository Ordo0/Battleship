package battleship;

public class Main {
    static final int sizeOfField = 10;
    static char[][] fieldOne = new char[sizeOfField][sizeOfField];

    static char[][] fieldTwo = new char[sizeOfField][sizeOfField];

    static char[][] fogOfWarOne = new char[sizeOfField][sizeOfField];

    static char[][] fogOfWarTwo = new char[sizeOfField][sizeOfField];

    public static void main(String[] args) {
        Ships ships = new Ships();
        Action action = new Action();
        Field fld = new Field();

        fld.createField();

        System.out.println("\nPlayer 1, place your ships on the game field\n");
        ships.placeShip(fieldOne);
        System.out.println("\nPlayer 2, place your ships on the game field\n");
        ships.placeShip(fieldTwo);

        do {
            action.playerOneTurn(fieldOne, fieldTwo, fogOfWarTwo);
            if (action.statusOfGame(fieldTwo)) {
                break;
            }
            System.out.println("Press Enter and pass the move to another player\n" +
                    "...");
            action.playerTwoTurn(fieldOne, fieldTwo, fogOfWarOne);
            if (action.statusOfGame(fieldOne)) {
                break;
            }
            System.out.println("Press Enter and pass the move to another player\n" +
                    "...");
        } while (true);
    }
}