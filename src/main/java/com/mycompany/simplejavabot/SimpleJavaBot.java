package com.mycompany.simplejavabot;

//импорты
import com.mycompany.simplejavabot.ControllerLayer.Bot;
import com.mycompany.simplejavabot.DatabaseLayer.DataBase;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;



public class SimpleJavaBot {


   public static void main(String[] args) {
    System.out.print("Hello, world from Java Bot!");

   
    ApiContextInitializer.init();
   
    TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
    try 
    {
        
       telegramBotsApi.registerBot(Bot.getBot());
    } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
    

