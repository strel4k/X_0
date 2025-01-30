import java.util.Scanner;

//создаем класс Field для описания игрового поля игры
public class Field {
    private char[][] board;     //массив для хранения состояния игрового поля
    private static final int SIZE = 3;       // размер игрового поля

    // Конструктор инициализации игрового поля
    public Field() {
        board = new char[SIZE][SIZE];
        for (int i=0; i<SIZE; i++) {
            for (int j=0; j<SIZE; j++) {
                board[i][j] = ' ';      //поле заполняем пробелами
            }
        }
    }

    // создаем метод для вывода текущего состояния игрового поля
    public void printBoard() {
        for (int i=0; i<SIZE; i++) {
            for (int j=0; j<SIZE; j++) {
                System.out.print(board[i][j]); // печатаем ячейку
                if (j < SIZE - 1)
                    System.out.print("|"); //разделитель ячеек
            }
            System.out.println();
            if (i< SIZE - 1)
                System.out.println("-----");  //разделитель строк
        }
    }

    // метод для выполнения хода
    public boolean makeMove(int row, int col, char player) {
        //проверка доступности ячеек
        if (row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == ' ') {
            board[row][col] = player;       //ячейка заполнится символом игрока
            return true;
        }
        return false;       //если ход не корректный то false
    }
    //метод для проверки заполнения всего поля
    public boolean isFull() {
        for (int i=0; i<SIZE; i++) {
            for (int j=0; j<SIZE; j++) {
                if (board[i][j] == ' ') {
                    return false;       //если есть хотя бы одна пустая ячейка, поле не заполнено
                }
            }
        }
        return true;        //поле полностью заполнено
    }

    // проверка выигрыша игрока
    public boolean isWin(char player) {
        //проверка строк и столбцов
        for (int i=0; i<SIZE; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;        //если строка или столбец заполнены одним символом, то игрок выиграл
            }
        }
        //проверка диагоналей
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return true;            //если одна из диагоналей заполнены одним символом, то игрок выиграл
        }

        return false;           //если ни одно из условий не выполнено, то нет победы
    }
}