import com.company.Server;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.net.Socket;

public class ClientTest {
    BufferedReader reader;
    BufferedWriter writer;

    @BeforeClass
    public static void serverStart() {
        Server server = new Server();
        server.start();
    }

    @Test
    public void client() {
        try {
            Socket socket = new Socket("localhost", 8080);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message = "coffee/1/3/2014/15" + "\n";
            writer.write(message);
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
