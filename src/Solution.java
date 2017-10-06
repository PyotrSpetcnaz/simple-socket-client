import java.io.*;
import java.net.*;

public class Solution {
    public static void main(String[] args) throws IOException{

        String host;
        int port;

        if ( args.length != 2 ) {
            host = "192.168.1.194";
            port = 46764;
            System.out.println("binding to port " + host + ":" + port);

        } else {
            host = args[ 0 ];
            port = Integer.parseInt ( args[ 1 ] );
        }

        try (
                Socket echoSocket = new Socket ( host, port );
                PrintWriter out =
                        new PrintWriter ( echoSocket.getOutputStream (), true );
                BufferedReader in =
                        new BufferedReader (
                                new InputStreamReader ( echoSocket.getInputStream () ) );
                BufferedReader stdIn =
                        new BufferedReader (
                                new InputStreamReader ( System.in ) )
        ) {
            Outputer outer = new Outputer(echoSocket);
            Thread output = new Thread(outer);
            output.start();
            System.out.println("Type in some text please.");
            String userInput;
            while ((userInput = in.readLine ()) != null) {
                System.out.println(userInput);

            }
        }
    }
}
