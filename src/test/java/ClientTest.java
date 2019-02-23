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
            purchase.date = "02.2018";
            purchase.price = 15;


            String message = gson.toJson(purchase, Purchase.class);
            writer.write("add/" + message + "\n");
            writer.flush();
            System.out.println(reader.readLine());

            //------------------------------------------------------------------------


            purchase.name = "tea";
            purchase.date = "02.2018";
            purchase.price = 10;


            message = gson.toJson(purchase, Purchase.class);
            writer.write("add/" + message + "\n");
            writer.flush();

            System.out.println(reader.readLine());

            //-------------------------------------------------------------------------

            purchase.name = "burger";
            purchase.date = "02.2018";
            purchase.price = 2678;


            message = gson.toJson(purchase, Purchase.class);
            writer.write("add/" + message + "\n");
            writer.flush();

            System.out.println(reader.readLine());

            //-------------------------------------------------------------------------


            writer.write("get/" + "02.2018" + "\n");
            writer.flush();

            System.out.println(reader.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
