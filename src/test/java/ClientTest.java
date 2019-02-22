import com.company.Server;
import com.company.model.Purchase;
import com.google.gson.Gson;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.net.Socket;
import java.util.Date;

public class ClientTest {
    BufferedReader reader;
    BufferedWriter writer;
    Gson gson;

    @BeforeClass
    public static void serverStart() {
        Server server = new Server();
        server.start();
    }

    @Test
    public void client() {
        Gson gson = new Gson();

        try {
            Socket socket = new Socket("localhost", 8080);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Purchase purchase = new Purchase();
            purchase.name = "coffee";
            purchase.date = new Date();
            purchase.price = 15;

            System.out.println(purchase);

            String message = gson.toJson(purchase, Purchase.class);
            writer.write(message + "\n");
            writer.flush();

            System.out.println(reader.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
