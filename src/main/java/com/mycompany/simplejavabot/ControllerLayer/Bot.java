package com.mycompany.simplejavabot.ControllerLayer;

import com.mycompany.simplejavabot.DatabaseLayer.DataBase;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.Date;
import java.util.Vector;
import org.telegram.telegrambots.api.methods.ParseMode;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.generics.LongPollingBot;
import static org.telegram.telegrambots.logging.BotLogger.log;

import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class Bot extends TelegramLongPollingBot {

    public static LongPollingBot getBot() {
        return new Bot();
    }
    DataBase db = null;

    public Bot() {
        super();
        db = new DataBase();
    }

    
    @Override
    public String getBotUsername() {
        
        return "GorbachenkoJavaBot";
        
    }

   
    @Override
    public String getBotToken() {
       
        return "628182648:AAFB744dQB-FJrGw-Bnc7qtjfT7UOISMErU";

    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()
                && update.getMessage().hasText()) {
            //
            String message_text = update.getMessage().getText();
            if (message_text.equals("/start")) 
            {
                long chat_id = update.getMessage().getChatId();
                sendMsg(update.getMessage().
                        getChatId().toString(), "Добро пожаловать в чат управления заказами!");
            }
           else if (message_text.equals("/time")) {
                Date currentDate = new Date();
                String messageSend = currentDate.toString();

                long chat_id = update.getMessage().getChatId();
                sendMsg(update.getMessage().
                        getChatId().toString(), messageSend);
            } else if (message_text.equals("/help")) 
            {
                long chat_id = update.getMessage().getChatId();
                sendMsg(update.getMessage().
                        getChatId().toString(), "команды: /time  /order /product");
            }
            
             else if (message_text.equals("/product")) 
            {
                long chat_id = update.getMessage().getChatId();
                //
                String mymessage = db.getProduct().toString();
                //
                sendMsg(update.getMessage().
                        getChatId().toString(), "список продуктов: " + "\n" + mymessage);
            } else if (message_text.equals("/order"))
            {
                long chat_id = update.getMessage().getChatId();
                //
                String mymessage = db.getOrder().toString();
                //
                sendMsg(update.getMessage().
                        getChatId().toString(), "список заказов: " + "\n" + mymessage);
            } else if (message_text.matches("/order\\d*"))
            {
                String orderId = message_text.replaceFirst("/order", "");
                String mymessage = db.getOrderProducts(orderId).toString();
                sendMsg(update.getMessage().getChatId().toString(), mymessage);
            } else if (message_text.matches("/[a-zA-Z0-9 ]*")) 
            {
                String productName = message_text.replaceFirst("/", "");
                
                Vector<String> productPrice=    db.getProductPrice(productName);
                
                if (productPrice.size()==0) {
                        sendMsg(update.getMessage().getChatId().toString(), "Данного товара нет в базе данных");
                }else
                {
                  String mymessage = productPrice.toString();
                sendMsg(update.getMessage().getChatId().toString(), mymessage);
                }            
                                
            } else {
                String message = update.getMessage().getText();
                sendMsg(update.getMessage().getChatId().toString(), message);
            }
            
        }
    }

   
    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);

        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            //log.log(Level.SEVERE, "Exception: ", e.toString());
        }
    }

}
