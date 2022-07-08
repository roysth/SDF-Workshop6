package visa.workshop6.server;
//2

import java.io.IOException;
import java.net.Socket;

public class CookieClientHandler implements Runnable{

    private Socket sock; 
    private String cookieFile;

    public CookieClientHandler(Socket s, String cookieFile){
        this.sock = s;
        this.cookieFile = cookieFile;
    }
    
    @Override
    public void run(){
        System.out.println("Starting a client thread");
        NetworkIO netIO = null;
        try {
            //takes in the server socket
            netIO = new NetworkIO(sock);
            String req = "";
            String randomCookieResp = "";
            while(true){
                //read whatever is sent from client to server
                req = netIO.read();
                //then print out what the client sent
                System.out.printf("[client] %s\n", req);
                //if client send out exit, then close the whole thing
                if(req.trim().equals("exit"))
                    break;
                //if client sends out "get-cookie", then print out the file name
                if (req.trim().equals("get-cookie")) {
                    System.out.printf("file -> %s\n", this.cookieFile);
                    //randomize to get a cookie based on the cookie file
                    //the method getRandomCookie comes from the Cookie.java 
                    randomCookieResp = Cookie.getRandomCookie(this.cookieFile);
                    //tap back on the network and send back to the client 
                    //uses the , as the delimineater cus the cookie got space eg macademia nuts
                    //this is so as to caprue the whole thing
                    netIO.write("cookie-text,"+ randomCookieResp);
                    break;
                }else{
                    //if receive rubbish input from client eg kjjfsk, then give invalid command
                    netIO.write("error,invalid command");
                    break;
                }
            }
            netIO.close();
            sock.close();
            
            System.out.println("Exiting the thread !");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}