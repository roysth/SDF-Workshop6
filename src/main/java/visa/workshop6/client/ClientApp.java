package visa.workshop6.client;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientApp 
{public static void main(String[] args){
    System.out.println("Cookie Client");
    String[] arr = args[0].split(":");
    boolean stop = false;
    InputStream is = null;
    DataInputStream dis = null;
    Socket sock = null; //client side uses Socket class. Server uses ServerSocket
    OutputStream os = null;

    try {
        //Console for user input
        Console cons = System.console();
        //after you finish one, then ask again, so need a loop
        while(!stop){
            String response = null;
            String input = cons.readLine("Send command to server > ");
            //get the port by changing the string to int, and establish the socket
            sock = new Socket(arr[0], Integer.parseInt(arr[1]));
            is = sock.getInputStream();
            dis = new DataInputStream(is);
            os = sock.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            
            if(input.equals("exit"))
                stop = true;
            dos.writeUTF(input);
            dos.flush();
            
            if(!stop){
                try{
                    //first time it reads, assign to response. second time it reads, then suppress it
                    response = dis.readUTF();
                }catch(EOFException e){
                    // suppress if the reading is called twice.
                }
                
                if(null != response){
                    if("cookie-text".contains(response) || "error,".contains(response)){
                        System.out.println(response);
                        String[] cookieValue = response.split(",");
                        if(response.contains("error,")){
                            System.out.printf("Error from server >> %s\n", cookieValue[1]);
                        }
                        if(response.contains("cookie-text,")){
                            System.out.printf("Cookie from server >> %s\n", cookieValue[1]);
                        }
                    }
                } 
            }
        }
        System.out.println("closing ..");
        is.close();
        os.close();
        sock.close();

    } catch (NumberFormatException e) {
        e.printStackTrace();
    } catch (UnknownHostException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}   
}