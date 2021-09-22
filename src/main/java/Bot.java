import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;

public class Bot extends TelegramLongPollingBot {

    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    long chat_id;
    String lastMessage = "";

    public void onUpdateReceived(Update update) {
        update.getUpdateId();
        Message message = update.getMessage();
        String chat_id = String.valueOf(update.getMessage().getChatId());
        int op = 0;

        String last_message = message.getText();

        SendMessage sendMessage = new SendMessage();
        if(last_message!=null){
            if (last_message.equals("Организация по БИН")){
                op=1;
              /*  try {
                    //Спросим бин
                    String json = APIConnector.getJson().toString();
                    String orgInfo = jsonHandler.getOrgInfoViaIIN(json);
                    sendMessage.setChatId(chat_id).setText("Введите БИН");

                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }
            else {
                //  SendMessage sendMessage = new SendMessage()
                //          .setChatId(chat_id)
                //           .setText(getMessage(update.getMessage().getText()));
                sendMessage.setChatId(chat_id).setText(getMessage(update.getMessage().getText()));
            }
        }

        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        sendMessage.setReplyToMessageId(message.getMessageId());

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

        if(msg.equals("Привет") || msg.equals("Меню") || msg.equals("Вернутся назад") || msg.equals("/start")){
            keyboard.clear();
            keyboardFirstRow.add("Организация по БИН");
            keyboardFirstRow.add("Персона по ИИН");
            keyboardSecondRow.add("Поиск по номеру телефона");
            keyboard.add(keyboardFirstRow);
            keyboard.add(keyboardSecondRow);
            replyKeyboardMarkup.setKeyboard(keyboard);
            return "Выберите пункт меню";
        }
        if(msg.equals("Организация по БИН")){
            keyboard.clear();
            keyboardFirstRow.add("Вернутся назад");
            keyboard.add(keyboardFirstRow);
            replyKeyboardMarkup.setKeyboard(keyboard);
            return "Введите БИН";
        }
        if(msg.equals("Персона по ИИН")){
            return "Этот пункт меню не рабочий";
        }
        if(msg.equals("Поиск по номеру телефона")){
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
