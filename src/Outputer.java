import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Outputer implements Runnable {

    private Socket clientSocket;

    public Outputer(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            PrintWriter out =
                    new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in =
                    new BufferedReader (
                            new InputStreamReader ( clientSocket.getInputStream () ) );
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String userInput;
            while ((userInput = stdIn.readLine ()) != null) {
                out.println (userInput);
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }
}