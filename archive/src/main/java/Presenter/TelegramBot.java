package Presenter;


import Api.Keys;
import Model.ModelCmd;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TelegramBot extends TelegramLongPollingBot {

    ModelCmd modelCmd = new ModelCmd();


    @Override
    public void onUpdateReceived(Update update) {

        System.out.println("getchatID:" + update.getChannelPost().getChatId().toString());


        if (!update.getMessage().getFrom().getUserName().equals("jdjhddh")) {
            return;
        }

        String inText = "";
        String senderName = "";
        try {  // from chanel

            inText = update.getChannelPost().getText();
            senderName = update.getMessage().getFrom().getFirstName();
        } catch (Throwable t1) {
        }
        try {  // from chat
            inText = update.getMessage().getText();
            senderName = update.getMessage().getFrom().getFirstName();
        } catch (Throwable t2) {
        }

        System.out.println(senderName + ": " + inText);

        inText = transliterate(inText);

        List<String> myList = new ArrayList<String>(Arrays.asList(inText.split(" ")));
        String temp1 = "";

        int k = 0;
        for (String s2 : myList) {
            if (k == 4) {
                k = 0;
                modelCmd.onExecuteCmd("amx_say !r" + senderName + ": " + temp1);
                temp1 = "";
            }
            temp1 += s2 + " ";
            k++;

        }
        modelCmd.onExecuteCmd("amx_say !r" + senderName + ": " + temp1);
    }

    @Override
    public String getBotUsername() {
        return Keys.TELEGRAM_BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return Keys.TELEGRAM_BOT_TOKEN;
    }

    public String transliterate(String message) {
        char[] abcCyr = {' ', 'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        String[] abcLat = {" ", "a", "b", "v", "g", "d", "e", "e", "zh", "z", "i", "y", "k", "l", "m", "n", "o", "p", "r", "s", "t", "u", "f", "h", "c", "4", "sh", "sh", "", "i", "", "e", "u", "ia", "A", "B", "V", "G", "D", "E", "E", "Zh", "Z", "I", "Y", "K", "L", "M", "N", "O", "P", "R", "S", "T", "U", "F", "H", "C", "4", "Sh", "Sh", "", "I", "", "E", "U", "Ia", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        StringBuilder builder = new StringBuilder();
        int add;
        for (int i = 0; i < message.length(); i++) {
            add = -1;
            for (int x = 0; x < abcCyr.length; x++) {
                if (message.charAt(i) == abcCyr[x]) {
                    add = x;
                }
            }
            if (add != -1) {
                builder.append(abcLat[add]);
            } else {
                builder.append(message.charAt(i));
            }

        }
        return builder.toString();
    }
}
