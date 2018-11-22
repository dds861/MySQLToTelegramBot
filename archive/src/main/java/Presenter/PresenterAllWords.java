package Presenter;


import Api.APIService;
import Api.Keys;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.*;

public class PresenterAllWords {

    public void startRetrofit() {
//        System.out.println("////////////////////////////////////////////");
//        System.out.println("startRetrofit() started");

        try {
            String url = Keys.MYARENA_URL_ANDROID;

            Retrofit retrofit;

            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
//            System.out.println("Retrofit Builder()");

            APIService service = retrofit.create(APIService.class);
//            System.out.println("APIService");
            Call<List<UsersAllWords>> call = service.getAllWords();
//            System.out.println("call.enqueue");
            call.enqueue(new Callback<List<UsersAllWords>>() {
                @Override
                public void onResponse(Call<List<UsersAllWords>> call, Response<List<UsersAllWords>> response) {
//                    System.out.println("onResponse() started");
                    List<UsersAllWords> newList = response.body();

                    sortArrayList(newList);
//                    System.out.println("onResponse() finished");
                }

                @Override
                public void onFailure(Call<List<UsersAllWords>> call, Throwable t) {

                }
            });
        } catch (Exception e) {

        }

//        System.out.println("startRetrofit() finished");
    }

    private void sortArrayList(List<UsersAllWords> newList) {
//        System.out.println("sortArrayList() started");

        List<String> newChatList = new ArrayList<>();
        newChatList = getList(newList);
//        System.out.println("newChatList installed");

        if (oldChatList.containsAll(newChatList)) {
//            System.out.println("sortArrayList() returned");
            return;
        }

        //reverses list
        ArrayList<String> newListReversed = new ArrayList<>();
        for (int i = 0; i < newChatList.size(); i++) {
            newListReversed.add(newChatList.get(newChatList.size() - i - 1));
        }

        //returns reversed list to original
        newChatList = new ArrayList<String>(newListReversed);


        //removes all identical data from new list
        newChatList.removeAll(oldChatList);

        oldChatList.clear();
        //adds from new list to old list
        oldChatList.addAll(newListReversed);

        String s1 = "";
        for (String s2 : newChatList) {
            s1 += s2;
        }


        System.out.println("Message length" + s1.length());
        if (!(s1.equals("")) && s1.length() < 4096) {

            onSendToTelegram(s1);
        }

//        System.out.println("sortArrayList() finished");
    }


    private List<String> oldChatList = new ArrayList<>();

    private List<String> getList(List<UsersAllWords> newList) {
//        System.out.println("getList() started");
        List<String> newChatList = new ArrayList<>();
        //adds data to list
        for (UsersAllWords usersAllWords : newList) {

            //cutting date and leaving time, cutting first 10 characters
            String evenTime = usersAllWords.getEventTime().substring(10);

            //if nickname greater than 10 characters, make it shorter
            String lastName;
            int maxNameLength = 10;
            if (usersAllWords.getLastName().length() > maxNameLength) {
                lastName = usersAllWords.getLastName().substring(0, Math.min(usersAllWords.getLastName().length(), maxNameLength)) + "...";
            } else {
                lastName = usersAllWords.getLastName();
            }

            //getting message
            String messageToSend = usersAllWords.getMessage();

            // encoding to UTF-8, as url cannot read symbols e.g. symbol plus "+" will become "%2B"
            try {
                messageToSend = URLEncoder.encode(messageToSend, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            //adding to list the message to send
            newChatList.add(evenTime
                    + " - "
                    + lastName
                    + ": "
                    + messageToSend
                    + "%0D%0A");
        }
//        System.out.println("getList() returned");
        return newChatList;
    }

    private void onSendToTelegram(String message) {
//        System.out.println("onSendToTelegram() started");

        //The url to send message to Telegram
        String urlString = Keys.TELEGRAM_URL;

        //Your Token
        String apiToken = Keys.TELEGRAM_BOT_TOKEN;

        //Your chatId
        String chatId = Keys.TELEGRAM_CHANNEL_CHAT_ID;


        //Inserting your parameters to String
        urlString = String.format(urlString, apiToken, chatId, message);


        //Start sending data
        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = new BufferedInputStream(conn.getInputStream());
//            System.out.println("Message Sent to Telegram");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
