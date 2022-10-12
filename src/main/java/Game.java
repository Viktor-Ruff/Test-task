import java.util.Scanner;

/**
 * Created by Viktor-Ruff
 * Date: 10.10.2022
 * Time: 18:08
 */
public class Game implements GameImpl {

    private int numberGame = 1;
    private int gameVariant = 0;
    private Scanner scanner = new Scanner(System.in);

    Map map = new Map();
    CheckWin checkWin = new CheckWin();

    public static void main(String[] args) {

        Game game = new Game();

        do {
            game.startGame();
            game.setNumberGame(game.getNumberGame() + 1);
        } while (game.isContinueGame());

    }


    /**
     * Метод игрового процесса
     */
    public void startGame() {

        System.out.println("Выберите вариант игры: \n 1 - Человек против компьютера; 2 - Человек против Человека; 3 - компьютер против компьютера");

        do {
            gameVariant = scanner.nextInt();
        } while (!isCellCorrect(gameVariant));

        System.out.println("Количество победных символов: 3 х 3 - 3; 4 х 4 - 4; 5 х 5 - 5;");

        map.printMap();

        if (gameVariant == gameVariant1) {
            humanVsComputer();
        }

        if (gameVariant == gameVariant2) {
            humanVsHuman();
        }

        if (gameVariant == gameVariant3) {
            computerVsComputer();
        }


        System.out.println("Игра закончена \n");
        System.out.println("Сыграем еще? y - yes/ n - no");
    }


    /**
     * Метод запроса на продолжение игры
     */
    public boolean isContinueGame() {

        String answer;
        answer = scanner.next();

        if (answer.equals("y") || answer.equals("yes")) {
            System.out.println("\n Игра №" + numberGame);
            map = new Map();
            return true;
        } else {
            System.out.println("До скорых встреч!");
        }

        return false;
    }

    /**
     * Метод проверки игры на завершение
     *
     * @param playerSymbol - символ которым играет текущий игрок
     * @return boolean - признак завершения игры
     */
    public boolean isEndGame(char playerSymbol) {

        boolean result = false;
        map.printMap();

        /**
         * Проверяем необходимость следующего хода
         */
        if (checkWin.checkWin(playerSymbol, map)) {
            System.out.println("Победили: " + playerSymbol);
            result = true;
        }

        if (map.isMapFull()) {
            System.out.println("Ничья");
            result = true;
        }

        return result;
    }

    public boolean isCellCorrect(int x) {

        if (x < 1 || x > 3) {
            System.out.println("Введены некорректные дынные!\n");
            return false;
        }

        return true;
    }

    /**
     * Вариация игры №1: Человек против компьютера
     */
    public void humanVsComputer() {

        int firstTern = 0;
        firstTern = (int) (10 * Math.random());

        if (firstTern >= 5) {
            while (true) {
                checkWin.getPlayers().humanTurn(map, DOT_X);
                if (isEndGame(DOT_X)) {
                    break;
                }

                checkWin.getPlayers().computerTurn(map, DOT_O);
                if (isEndGame(DOT_O)) {
                    break;
                }
            }
        } else {
            while (true) {
                checkWin.getPlayers().computerTurn(map, DOT_X);
                if (isEndGame(DOT_X)) {
                    break;
                }

                checkWin.getPlayers().humanTurn(map, DOT_O);
                if (isEndGame(DOT_O)) {
                    break;
                }
            }
        }


    }

    /**
     * Вариация игры №2: Человек против Человека
     */
    public void humanVsHuman() {

        while (true) {
            checkWin.getPlayers().humanTurn(map, DOT_X);
            if (isEndGame(DOT_X)) {
                break;
            }

            checkWin.getPlayers().humanTurn(map, DOT_O);
            if (isEndGame(DOT_O)) {
                break;
            }
        }


    }


    /**
     * Вариация игры №3: компьютер против компьютера
     */
    public void computerVsComputer() {

        while (true) {
            try {
                Thread.sleep(2000 + (int) (500 * Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            checkWin.getPlayers().computerTurn(map, DOT_X);
            if (isEndGame(DOT_X)) {
                break;
            }

            try {
                Thread.sleep(2000 + (int) (500 * Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            checkWin.getPlayers().computerTurn(map, DOT_O);
            if (isEndGame(DOT_O)) {
                break;
            }
        }

    }

    public int getNumberGame() {
        return numberGame;
    }

    public void setNumberGame(int numberGame) {
        this.numberGame = numberGame;
    }
}
