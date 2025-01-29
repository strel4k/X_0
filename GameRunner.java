import java.util.Scanner;

//создаем класс Field для описания игрового поля игры
class Field {
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
    //GameLogic - класс с основной бизнес логикой игры
    class GameLogic {
    private Field field;            //объект игрового поля
    private char currentPlayer;     //объект текущего игрока('X','O')

        //конструктор инициализирует игровое поле и задает первого игрока
        public GameLogic() {
            field = new Field();
            currentPlayer = 'X';        //игра начинается с игрока Х
        }

        //метод для запуска игры
        public void playGame() {
            Scanner scanner = new Scanner(System.in);
            boolean gameRunning = true;

            while (gameRunning) {
                field.printBoard();     //вывод текущего состояния поля
                System.out.println("Игрок " + currentPlayer + " ходи (0-2 строка и колонка)");
                int row = scanner.nextInt();        //считать строку
                int col = scanner.nextInt();        //cчитать столбец

                //проверка корректности хода
                if (field.makeMove(row, col, currentPlayer)) {
                    //проверка на победу текущего игрока
                    if (field.isWin(currentPlayer)) {
                        field.printBoard();
                        System.out.println("Игрок " + currentPlayer + " вин!"); //объявление победителя
                        gameRunning = false;        //игра стопается
                    } else if (field.isFull())  {
                        field.printBoard();
                        System.out.println("Ничья, лалки");      //если поле заполнено - ничья
                        gameRunning = false;        //игра стопается
                    } else {
                        //к следующему игроку
                        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                    }
                } else {
                    System.out.println("Криво пошел - перехаживай");     //сообщение об ошибочном ходе
                }
            }
            scanner.close();        //закрытие сканнера после завершения игры
        }
    }

// главный класс приложения
public class GameRunner {
    public static void main (String args[]) {
    GameLogic gameLogic = new GameLogic();      //создаем объект с логикой
        gameLogic.playGame();                   //запуск игры
    }
}
