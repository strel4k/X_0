import java.util.Scanner;

// главный класс приложения
public class GameRunner {
    public static void main (String args[]) {
    GameLogic gameLogic = new GameLogic();      //создаем объект с логикой
        gameLogic.playGame();                   //запуск игры
    }
}
