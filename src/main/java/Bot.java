import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

public class Bot extends TelegramLongPollingBot {

    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    long chat_id;
    String lastMessage = "";

    public void onUpdateReceived(Update update) {
        update.getUpdateId();

        String chat_id = String.valueOf(update.getMessage().getChatId());
        SendMessage sendMessage = new SendMessage()
                .setChatId(chat_id)
                .setText(getMessage(update.getMessage().getText()));
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
    public String getMessage(String msg){
        ArrayList<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();

        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        if(msg.equals("Привет") || msg.equals("Меню") || msg.equals("/start")){
            keyboard.clear();
            keyboardFirstRow.add("Подать обращение");
            keyboardFirstRow.add("Информация о боте");
            keyboardSecondRow.add("My issues");
            keyboard.add(keyboardFirstRow);
            keyboard.add(keyboardSecondRow);
            replyKeyboardMarkup.setKeyboard(keyboard);
            return "Выберите пункт меню";
        }

        if(msg.equals("Подать обращение")){
            keyboard.clear();
            keyboardFirstRow.add("Вернутся назад");
            keyboard.add(keyboardFirstRow);
            replyKeyboardMarkup.setKeyboard(keyboard);
            return "Выберите пункт меню";
        }
        if(msg.equals("Информация о боте")){
            return "Этот пункт меню не рабочий";
        }
        if(msg.equals("Ссылки на НПА")){
            return "Этот пункт меню не рабочий";
        }
        return "Если возникли проблемы, воспользуйтесь /start";
    }

    //@Override
    public String getBotUsername() {
        return "@Ashattestbot";
    }

    // @Override
    public String getBotToken() {
        return "1018724880:AAHBuvN22SLE4hRhTjzj9jvtN9oOamrDScY";
    }
}
