import org.telegram.telegrambots.*;
import org.telegram.telegrambots.meta.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class Main {
    public static void main(String args[]) throws TelegramApiRequestException {
        ApiContextInitializer.init();
        TelegramBotsApi telegram = new TelegramBotsApi();
        telegram.registerBot(new Bot());

    }
}