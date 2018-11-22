import Presenter.PresenterAllWords;
import Presenter.TelegramBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {

    public static void main(String[] args) {

//        System.out.println("Main.class");

        //Telegram bot that reads data and sends back
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new TelegramBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        System.getenv();

        //Sends data from sql to Telegram group
        PresenterAllWords presenterAllWords = new PresenterAllWords();
        int i = 0;
//        System.out.println("Loop started");
        while (true) {
            i++;
            System.out.println(i);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            presenterAllWords.startRetrofit();
        }
    }
}
