package ExerciseM3;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket=new Socket("127.0.0.1", 10000);
        String command=args[0];
        String fileName=null;

        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(outputStream));
        bw.write(command);
        bw.newLine();
        bw.flush();
        if(args.length>1){
            fileName=args[1];
            bw.write(fileName);
            bw.flush();
        }

        socket.shutdownOutput();

        InputStream inputStream = socket.getInputStream();
        BufferedReader br=new BufferedReader(new InputStreamReader(inputStream));
        String str;
        while((str=br.readLine())!=null){
            System.out.println(str);
        }
        socket.close();
        br.close();
    }
}
