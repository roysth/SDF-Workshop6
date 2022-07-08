package visa.workshop6.server;
//FOR IO
//when to use Data AND File inputoutput stream
//USE DATA FOR NETWORK

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class NetworkIO {
    private InputStream is;
    private DataInputStream dis;
    private OutputStream os;
    private DataOutputStream dos;

    //need to construct the Socket - endpoint for communication between two machines
    //instantiating the getInputStream etc
    public NetworkIO(Socket sock) throws IOException{
        is = sock.getInputStream();
        dis = new DataInputStream(is);
        os = sock.getOutputStream();
        dos = new DataOutputStream(os);
    }
    //when you read, need to return something
    //from server side, client give data to server side to read 
    public String read() throws IOException {
       return dis.readUTF();
    }

    //when you write, dont need to return anything, hence can put void
    public void write(String msg) throws IOException {
        dos.writeUTF(msg);
        dos.flush();
     }

     public void close(){
         try{
            dis.close();
            is.close();
            dos.close();
            os.close();
         }catch(IOException e){
             e.printStackTrace();
         }
     }
}