package visa.workshop6.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerApp {
    public static void main( String[] args ) throws IOException{
        //2 parameters, 1 is port number, its in string, convert to int
        int port = 3001;
        if(args.length > 0)
            port = Integer.parseInt(args[0]);
        
        //2nd argument. msut be a fully qualtified path for it to be found
        String cookieFile = args[1];
        System.out.printf( "Server App started at %s\n", port );
        //use ExecutorService to allocate the thread size to 2. initailize it to 2 So that 2 clients can connect

        //instantiate here
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        
        ServerSocket server = new ServerSocket(port);


        //to kill the server, use ctrl c 
        //have to manually kill it cus 2 threads. if one client end it, the other might still wanna use
        //can only close when both ends, hence manually close
        while(true){
            System.out.println("Waiting for client connection");
            Socket sock = server.accept(); 
            System.out.println("Connected ...");
            //CookieClientHandler impelemnts runnerable
            //socket is closed in the runnerable
            //by declaring the variables (sock and cookieFile) in the parentheses,
            //it allows the CookieClientHandler class to use the values of these variabels
            CookieClientHandler thr = new CookieClientHandler(sock, cookieFile);
            threadPool.submit(thr);
            System.out.println("Submitted to threadpool");
        }
        
    }
}