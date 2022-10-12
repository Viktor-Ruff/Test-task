/**
 * Created by Viktor-Ruff
 * Date: 11.10.2022
 * Time: 17:26
 */
public class CheckWin {

    private int amountSymbolsForWin = 0;

    Players players = new Players();

    /**
     * Метод проверки выигрыша
     *
     * @param playerSymbol - символ введенный пользователем
     * @return boolean - результат проверки
     */
    public boolean checkWin(char playerSymbol, Map map) {

        getAmountSymbolsForWin(map);

        if (checkLine(playerSymbol, map)) {
            return true;
        }

        if (checkColumn(playerSymbol, map)) {
            return true;
        }

        if (checkLeftDiagonal(playerSymbol, map)) {
            return true;
        }

        if (checkRightDiagonal(playerSymbol, map)) {
            return true;
        }

        return false;
    }

    /**
     * Метод вычисления выигрышной комбинации.
     */
    public int getAmountSymbolsForWin(Map map) {

        if (map.getSIZE() == 3) {
            amountSymbolsForWin = 3;

        } else if (map.getSIZE() == 4) {
            amountSymbolsForWin = 4;

        } else {
            amountSymbolsForWin = 5;
        }

        return amountSymbolsForWin;
    }


    /**
     * Метод поиска символов игрока по строке.
     */
    public boolean checkLine(char playerSymbol, Map map) {
        int winPoint = 0;

        for (int i = 0; i < map.getSIZE(); i++) {
            if (map.getMap()[players.getVerticalCoordinate()][i] == playerSymbol) {
                winPoint += 1;
                if (winPoint == amountSymbolsForWin) {
                    return true;
                }
            } else {
                winPoint = 0;
            }
        }

        return false;
    }

    /**
     * Метод поиска символов игрока по столбцу.
     */
    public boolean checkColumn(char playerSymbol, Map map) {
        int winPoint = 0;

        for (int i = 0; i < map.getSIZE(); i++) {
            if (map.getMap()[i][players.getHorizontalCoordinate()] == playerSymbol) {
                winPoint += 1;
                if (winPoint == amountSymbolsForWin) {
                    return true;
                }
            } else {
                winPoint = 0;
            }
        }

        return false;
    }


    /**
     * Метод поиска символов игрока по левой диагонали.
     */
    public boolean checkLeftDiagonal(char playerSymbol, Map map) {
        int winPointDown = 0;
        int winPointUp = 0;


        while (true) {
            if (players.getVerticalCoordinate() + 1 >= map.getSIZE() || players.getHorizontalCoordinate() + 1 >= map.getSIZE() || map.getMap()[players.getVerticalCoordinate() + 1][players.getHorizontalCoordinate() + 1] != playerSymbol) {
                break;
            } else {
                winPointUp++;
                players.setVerticalCoordinate(players.getVerticalCoordinate() + 1);
                players.setHorizontalCoordinate(players.getHorizontalCoordinate() + 1);
            }

            if (winPointUp >= amountSymbolsForWin - 1) {
                return true;
            }
        }

        while (true) {

            if (players.getVerticalCoordinate() - 1 < 0 || players.getHorizontalCoordinate() - 1 < 0 || map.getMap()[players.getVerticalCoordinate() - 1][players.getHorizontalCoordinate() - 1] != playerSymbol) {
                break;
            } else {
                winPointDown++;
                players.setVerticalCoordinate(players.getVerticalCoordinate() - 1);
                players.setHorizontalCoordinate(players.getHorizontalCoordinate() - 1);

            }
            if (winPointDown >= amountSymbolsForWin - 1) {
                return true;
            }
        }

        return false;
    }

    /**
     * Метод поиска символов игрока по правой диагонали.
     */
    public boolean checkRightDiagonal(char playerSymbol, Map map) {
        int winPointDown = 0;
        int winPointUp = 0;

        while (true) {
            if (players.getVerticalCoordinate() + 1 >= map.getSIZE() || players.getHorizontalCoordinate() - 1 < 0 || map.getMap()[players.getVerticalCoordinate() + 1][players.getHorizontalCoordinate() - 1] != playerSymbol) {
                break;
            } else {
                winPointDown++;
                players.setVerticalCoordinate(players.getVerticalCoordinate() + 1);
                players.setHorizontalCoordinate(players.getHorizontalCoordinate() - 1);
            }

            if (winPointDown == amountSymbolsForWin - 1) {
                return true;
            }
        }

        while (true) {

            if (players.getVerticalCoordinate() - 1 < 0 || players.getHorizontalCoordinate() + 1 >= map.getSIZE() || map.getMap()[players.getVerticalCoordinate() - 1][players.getHorizontalCoordinate() + 1] != playerSymbol) {
                break;
            } else {
                winPointUp++;
                players.setVerticalCoordinate(players.getVerticalCoordinate() - 1);
                players.setHorizontalCoordinate(players.getHorizontalCoordinate() + 1);
            }

            if (winPointUp == amountSymbolsForWin - 1) {
                return true;
            }

        }

        return false;
    }

    public Players getPlayers() {
        return players;
    }


}
