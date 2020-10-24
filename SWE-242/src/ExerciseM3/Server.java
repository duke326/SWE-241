package ExerciseM3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        String source=args[0];
        File file=new File(source);
        if(!file.exists()){
            file.mkdir();
        }

        ServerSocket ss=new ServerSocket(10000);
        Socket accept = null;
        boolean flag=true;
        while (flag) {
            accept = ss.accept();
            InputStream inputStream = accept.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(inputStream));
            String str;
            OutputStream ow=accept.getOutputStream();
            BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(ow));

            //write the data back to the socket
            while((str=br.readLine())!=null){
                if(str.equals("index")){
                    //System.out.println(str);
                    File []allFile=file.listFiles();
                    for(File temp:allFile){
                        bw.write(temp.getName());
                        bw.newLine();
                        bw.flush();

                    }
                }
                else if(str.equals("get")){

                    File [] allFile=file.listFiles();
                    String fileName=br.readLine();
                    //System.out.println(fileName);
                    for(int i=0;i< allFile.length;i++){
                        if(allFile[i].getName().equals(fileName)){
                            bw.write("Ok");
                            bw.newLine();
                            BufferedReader fileBr=new BufferedReader(new FileReader(allFile[i]));
                            String temp;
                            while((temp= fileBr.readLine())!=null){
                                bw.write(temp);
                                bw.newLine();
                                bw.flush();
                            }
                            break;
                        }
                        //cannt find the file
                        else if(i==allFile.length-1&&!allFile[i].getName().equals(fileName)){
                            bw.write("Error");
                            bw.newLine();
                            bw.flush();
                            flag=false;

                        }
                    }
                }
                else {
                    bw.write("Check your command");
                    bw.newLine();
                    bw.flush();
                }
            }


            bw.close();
        }
        accept.close();


    }

}
