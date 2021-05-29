import java.io.*;
import java.net.*;

public class Server{
    public static void main(String [] args) throws Exception{
        System.out.println("Waiting connection");
        try{
            ServerSocket ss = new ServerSocket(3000);
            int x=0;
            while(true)
            {
                
                Socket ls = ss.accept();
                DataOutputStream client_out = new DataOutputStream(ls.getOutputStream());
                if(x==0)
                {
                    System.out.println("\nConnection established\n");
                    x=x+1;
                }
                BufferedReader client_input = new BufferedReader(new InputStreamReader(ls.getInputStream()));
                BufferedReader server_input = new BufferedReader(new InputStreamReader(System.in));
                
                String client_str, server_str;
                client_str = client_input.readLine();
                System.out.println("Client says: "+ client_str);
                if(client_str.equals("bye"))
                {
                    System.out.println("\nConnection terminated!...");
                    client_out.writeBytes("bye" + "\n");
                    break;
                }
                server_str = server_input.readLine();
                client_out.writeBytes(server_str + "\n");
            }
            ss.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }
}