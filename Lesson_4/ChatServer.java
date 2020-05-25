package Lesson_4;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class ChatServer {
    //Создать лог в файле (показать комментарием, где и как Вы планируете писать сообщение в файловый журнал).
    public static void localLogCopy(String s, String file) throws IOException {
        FileOutputStream logFile = new FileOutputStream(file, true);
        PrintStream newMessage = new PrintStream(logFile);
        newMessage.println(s);
        logFile.close();
    }

    public void start(int port) {
        System.out.println("Server started at port: " + port);
    }

    public void stop() {
        System.out.println("Server stopped");
    }

}
