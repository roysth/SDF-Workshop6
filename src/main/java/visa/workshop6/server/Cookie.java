package visa.workshop6.server;

//scan the file and put into a Linkedlist
//then create a method to get a random cookie. this will be used in the other classes

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Cookie {

    //getRandomCookie method is being used in CookieClientHandler
    public static String getRandomCookie (String path){
        File cookieFile =  new File(path);
        List<String> cookies = new LinkedList<>();
        String randomCookie = "";
        Scanner scanner;
        try {
            scanner = new Scanner(cookieFile);
            while (scanner.hasNextLine()) {
                cookies.add(scanner.nextLine());
            }
            scanner.close();
            
            System.out.println(cookies);
            Random rand = new Random();
            randomCookie = cookies.get(rand.nextInt(cookies.size()));
            System.out.println(randomCookie);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        return randomCookie;
    }
}