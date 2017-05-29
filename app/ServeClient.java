package app;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

/**
 * Created by Djok on 5/29/2017.
 * With Soul
 */
class ServeClient extends Thread {
    /**
     * Creati o conexiune TCP la portul 6969 si trimiteti
     * un sir de caractere care sa reprezinte coordonatele.
     * EX: "23 45 23 47" care reprezinta ca dorim din sursa (23, 45) sa ajungem in destinatia (23, 47).
     * Dupa care, asteptati un sir de caractere care reprezinta un XML cu drumul.
     */

    private Socket sock = null;

    ServeClient(Socket socket) {
        sock = socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());

            Object cevaCareReprezintaCoordonatele = in.readObject();
            /*
                Din variabila de mai sus, trebuie de parsat coordonatele si transformat in
                coordonate de-a le noastre.
                Adica 2 point source si dest;
             */

            Point source = new Point(69, 69, 96);
            Point dest = new Point(23, 23, 23);
            //apel functie minunata;
            Vector<Point> ans = new MinDistancePath(new Matrix()).execute(source, dest);
            // aici evident trebuie altfel apelata functia de distanta

            //Parsam ans si transformam in ceva (XML ?)
            // ca sa le trimitem inapoi drumul
            out.writeObject(ans);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
