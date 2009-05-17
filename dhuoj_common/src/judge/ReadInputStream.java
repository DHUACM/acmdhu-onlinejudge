package judge;

import java.io.*;

public class ReadInputStream extends Thread {

    public ReadInputStream(InputStream input) {
        in = input;
        content = "";
    }

    @Override
    public void run() {
        StringBuffer buffer = new StringBuffer();
        try {
            in = new BufferedInputStream(in);
            do {
                int n;
                if ((n = in.read()) == -1) {
                    break;
                }
                if (n != 13) {
                    buffer.append((char) n);
                }
            } while (true);
            in.close();
        } catch (IOException IOE) {
            System.out.println(IOE.toString());
        }
        content = buffer.toString();
    }

    public String getMessage() {
        return content;
    }
    private InputStream in;
    private String content;
}
