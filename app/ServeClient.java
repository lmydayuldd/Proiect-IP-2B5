package app;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Djok on 5/29/2017.
 * With Soul
 */
class ServeClient extends Thread {
    /**
     * Creati o conexiune TCP la portul 6969 si trimiteti
     * un sir de caractere care sa reprezinte coordonatele.
     * EX: "1 2 3 4 5 6" care reprezinta ca dorim din sursa (1, 2, 3) sa ajungem in destinatia (4, 5, 6).
     * 1 - X, 2 - Y, 3 - Etaj. 4 - X, 5 - Y, 6 - Etaj (codificarea).
     * X : 1, Y : 2, FLOOR : 3
     * Dupa care, asteptati un sir de caractere care reprezinta un XML cu drumul.
     */

    private Socket sock = null;

    ServeClient(Socket socket) {
        sock = socket;
    }

    @Override
    public void run() {
        try {
            InputReader in = new InputReader(sock.getInputStream());
            OutputStream out = new BufferedOutputStream(sock.getOutputStream());
            Point source = new Point((int) (10 * in.nextNumber()), (int) (10 * in.nextNumber()), (int) (in.nextNumber()));
            Point dest = new Point((int) (10 * in.nextNumber()), (int) (10 * in.nextNumber()), (int) (in.nextNumber()));


            if (true) {
                // Magic trick, do not try to understand what is happening here ;)
                ArrayList<Point> a = new ArrayList<>();
                a.add(dest);
                a.add(source);
                XmlOutput x = new XmlOutput(a);
                x.createXml("asd.xml");
                return;
            }


            //apel functie minunata;
            ArrayList<Point> ans = new MinDistancePath(new Matrix()).execute(source, dest);
            // aici evident trebuie altfel apelata functia de distanta

            //Parsam ans si transformam in ceva (XML ?)
            // ca sa le trimitem inapoi drumul
            out.write((int) ans.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class InputReader {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        double nextNumber() {
            return Double.parseDouble(next());
        }
    }
}
