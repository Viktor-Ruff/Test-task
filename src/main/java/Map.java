import java.util.Scanner;

/**
 * Created by Viktor-Ruff
 * Date: 10.10.2022
 * Time: 18:03
 */
public class Map implements GameImpl {

    private char[][] map;
    private int SIZE;
    private final int minSizeMap = 3;
    private final int maxSizeMap = 5;

    private static Scanner scanner = new Scanner(System.in);

    public Map() {
        initMap();
    }

    /**
     * Метод подготовки игрового поля
     */
    public int initMap() {

        do {
            System.out.format("Введите размеры игрового поля (%d...%d)", minSizeMap , maxSizeMap );
            SIZE = scanner.nextInt();

        } while (!isSizeValid(SIZE));

        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }

        return SIZE;
    }


    /**
     * Метод вывода игрового поля на экран
     */
    public void printMap() {

        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    /**
     * Метод валидации вводимого размера поля на корректность
     */
    public boolean isSizeValid(int x) {

        if (x < minSizeMap || x > maxSizeMap) {
            return false;
        }

        return true;
    }


    /**
     * Проверка на 100%-ую заполненность поля
     *
     * @return boolean - признак оптимальности
     */
    public boolean isMapFull() {

        boolean result = true;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY)
                    result = false;
            }
        }

        return result;
    }

    public char[][] getMap() {
        return map;
    }

    public int getSIZE() {
        return SIZE;
    }
}
