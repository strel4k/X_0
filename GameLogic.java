import java.util.Scanner;

//GameLogic - класс с основной бизнес логикой игры
public class GameLogic {
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