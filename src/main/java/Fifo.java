import org.flywaydb.core.Flyway;
import validation.InputSplitAndCheck;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Fifo {
    public static void main(String[] args) {

        String url = "", username = "", password = "";
        try {
            FileInputStream fstream = new FileInputStream("./config/hibernate.cfg.xml");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null){

                if (strLine.contains("hibernate.connection.url")) {
                    url = strLine.replaceAll("\"", "")
                            .replaceAll("<property name=hibernate.connection.url>", "")
                            .replaceAll("</property>", "").trim();
                }

                if (strLine.contains("hibernate.connection.username")) {
                    username = strLine.replaceAll("\"", "")
                            .replaceAll("<property name=hibernate.connection.username>", "")
                            .replaceAll("</property>", "").trim();
                }
                if (strLine.contains("hibernate.connection.password")) {
                    password = strLine.replaceAll("\"", "")
                            .replaceAll("<property name=hibernate.connection.password>", "")
                            .replaceAll("</property>", "").trim();
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка, файл с конфигурацией не найден!" + e);
            e.printStackTrace();
        }

        Flyway flyway = Flyway.configure().dataSource(url, username, password).load();

        flyway.migrate();
        flyway.validate();

        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

        InputSplitAndCheck inputSplitAndCheck = new InputSplitAndCheck();
        Scanner inp = new Scanner(System.in);
        String cmd;

        do {
            System.out.print("Введите команду либо q для выхода: ");
            cmd = inp.nextLine();
            if (cmd.trim().toLowerCase().matches("newproduct .*|purchase .*|salesreport .*|demand .*")){
                inputSplitAndCheck.checkAndExec(cmd);
            } else {
                if (!cmd.trim().toLowerCase().matches("q")) System.out.println("Ошибка!");
            }
        } while (!cmd.trim().toUpperCase().equals("Q")) ;
    }
}
