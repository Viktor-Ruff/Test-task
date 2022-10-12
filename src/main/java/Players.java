import java.util.Random;
import java.util.Scanner;

/**
 * Created by Viktor-Ruff
 * Date: 11.10.2022
 * Time: 16:50
 */
public class Players implements GameImpl {

    private int verticalCoordinate = 0;
    private int horizontalCoordinate = 0;

    private Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public int getVerticalCoordinate() {
        return verticalCoordinate;
    }

    public void setVerticalCoordinate(int verticalCoordinate) {
        this.verticalCoordinate = verticalCoordinate;
    }

    public int getHorizontalCoordinate() {
        return horizontalCoordinate;
    }

    public void setHorizontalCoordinate(int horizontalCoordinate) {
        this.horizontalCoordinate = horizontalCoordinate;
    }

    /**
     * Ход человека
     */
    public void humanTurn(Map map, char playerSymbol) {
        do {
            System.out.println("Введите координаты ячейки, через пробел: ");
            verticalCoordinate = scanner.nextInt() - 1;
            horizontalCoordinate = scanner.nextInt() - 1;
        } while (!isCellValid(verticalCoordinate, horizontalCoordinate, map));


        map.getMap()[verticalCoordinate][horizontalCoordinate] = playerSymbol;
    }

    /**
     * Метод валидации запрашиваемой ячейки на корректность
     *
     * @param x - координата по горизонтали
     * @param y - координата по вертикали
     * @return boolean - признак валидности
     */
    private boolean isCellValid(int y, int x, Map map) {

        if (x < 0 || x >= map.getSIZE() || y < 0 || y >= map.getSIZE() || map.getMap()[y][x] != DOT_EMPTY) {
            System.out.println("Введены некорректные координаты!\n");
            return false;
        }
        return true;
    }


    /**
     * Ход компьютера
     */
    public void computerTurn(Map map, char playerSymbol) {
        if (!smartComputerTurn(map, playerSymbol)) {
            sillyComputerTurn(map, playerSymbol);
        }
    }


    /**
     * Умный ход компьютера
     */
    public boolean smartComputerTurn(Map map, char playerSymbol) {


        boolean moveFound = false;

        for (int i = 0; i < map.getSIZE(); i++) {
            for (int j = 0; j < map.getSIZE(); j++) {
                if (map.getMap()[i][j] == DOT_EMPTY) {

                    verticalCoordinate = i;
                    horizontalCoordinate = j;
                    if (j + 1 < map.getSIZE() && map.getMap()[i][j + 1] == playerSymbol) {
                        moveFound = true;
                        System.out.println("*Компьютер выбрал ячейку: " + (i + 1) + " " + (j + 1));
                        map.getMap()[verticalCoordinate][horizontalCoordinate] = playerSymbol;
                        break;
                    } else if (j - 1 >= 0 && map.getMap()[i][j - 1] == playerSymbol) {
                        moveFound = true;
                        System.out.println("*Компьютер выбрал ячейку: " + (i + 1) + " " + (j + 1));
                        map.getMap()[verticalCoordinate][horizontalCoordinate] = playerSymbol;
                        break;
                    } else if (i - 1 >= 0 && map.getMap()[i - 1][j] == playerSymbol) {
                        moveFound = true;
                        System.out.println("*Компьютер выбрал ячейку: " + (i + 1) + " " + (j + 1));
                        map.getMap()[verticalCoordinate][horizontalCoordinate] = playerSymbol;
                        break;
                    } else if (i + 1 < map.getSIZE() && map.getMap()[i + 1][j] == playerSymbol) {
                        moveFound = true;
                        System.out.println("*Компьютер выбрал ячейку: " + (i + 1) + " " + (j + 1));
                        map.getMap()[verticalCoordinate][horizontalCoordinate] = playerSymbol;
                        break;
                    } else if (i - 1 >= 0 && j - 1 >= 0 && map.getMap()[i - 1][j - 1] == playerSymbol) {
                        moveFound = true;
                        System.out.println("*Компьютер выбрал ячейку: " + (i + 1) + " " + (j + 1));
                        map.getMap()[verticalCoordinate][horizontalCoordinate] = playerSymbol;
                        break;
                    } else if (i + 1 < map.getSIZE() && j + 1 < map.getSIZE() && map.getMap()[i + 1][j + 1] == playerSymbol) {
                        moveFound = true;
                        System.out.println("*Компьютер выбрал ячейку: " + (i + 1) + " " + (j + 1));
                        map.getMap()[verticalCoordinate][horizontalCoordinate] = playerSymbol;
                        break;
                    } else if (i - 1 >= 0 && j + 1 < map.getSIZE() && map.getMap()[i - 1][j + 1] == playerSymbol) {
                        moveFound = true;
                        System.out.println("*Компьютер выбрал ячейку: " + (i + 1) + " " + (j + 1));
                        map.getMap()[verticalCoordinate][horizontalCoordinate] = playerSymbol;
                        break;
                    } else if (i + 1 < map.getSIZE() && j - 1 >= 0 && map.getMap()[i + 1][j - 1] == playerSymbol) {
                        moveFound = true;
                        System.out.println("*Компьютер выбрал ячейку: " + (i + 1) + " " + (j + 1));
                        map.getMap()[verticalCoordinate][horizontalCoordinate] = playerSymbol;
                        break;
                    }
                }
            }
            if (moveFound) {
                break;
            }
        }
        return moveFound;
    }

    /**
     * Рандомный ход компьютера
     */
    private void sillyComputerTurn(Map map, char playerSymbol) {

        do {
            horizontalCoordinate = random.nextInt(map.getSIZE());
            verticalCoordinate = random.nextInt(map.getSIZE());
        } while (!isCellValid(horizontalCoordinate, verticalCoordinate, map));
        System.out.println("Компьютер выбрал ячейку: " + (verticalCoordinate + 1) + " " + (horizontalCoordinate + 1));
        map.getMap()[verticalCoordinate][horizontalCoordinate] = playerSymbol;
    }

}
