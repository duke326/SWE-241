package ExercistM4;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class ServerUDP {

    public static void main(String[] args) throws IOException {
        String source=args[0];
        InetAddress ip=InetAddress.getByName("127.0.0.1");
        File file=new File(source);
        //File file=new File(source);
        if(!file.exists()){
            file.mkdir();
        }

        DatagramSocket ds=new DatagramSocket(10000);
        String status="ok";
        while (!status.equals("Error")) {
            byte [] pack=new byte[1024];
            DatagramPacket dp=new DatagramPacket(pack, pack.length);
            ds.receive(dp);
            //byte[] data=dp.getData();
            int length=dp.getLength();
            String data=new String(pack, 0,length);
            System.out.println(data);
            if(data.equals("index")){
                File [] fileList=file.listFiles();
                for(File tempFile:fileList){
                    //byte []index=new byte[1024];
                    String s=tempFile.getName();
                    //System.out.println(s);
                    dp=new DatagramPacket(s.getBytes(), s.getBytes().length, ip,10001);
                    ds.send(dp);
                }
                dp=new DatagramPacket("end".getBytes(), "end".getBytes().length, ip,10001);

                ds.send(dp);
            }
            else if (data.equals("get")){
                //receive the book name
                byte[] temp=new byte[1024];
                dp=new DatagramPacket(temp,temp.length);
                ds.receive(dp);
                String bookName=new String(dp.getData(), 0,dp.getLength());
                System.out.println(bookName);
                File[] fileList=file.listFiles();
                for(int i=0;i< fileList.length;i++){//i: the file in the fileList
                    if(fileList[i].getName().equals(bookName)){
                        //send ok
                        dp=new DatagramPacket("OK".getBytes(),"OK".getBytes().length,ip,10001 );
                        ds.send(dp);
                        //read the file
                        InputStream is=new FileInputStream(fileList[i].getAbsolutePath());
                        //create a big array put it into stream
                        byte []fileArray=new byte[(int)fileList[i].length()];
                        is.read(fileArray);

                        int sequenceNum=0;
                        //ack package
                        int ackNum=0;
                        boolean lastMessageFlag=false;
                        //prepare for the singal
                        for(int j=0;j< fileArray.length;j+=1021){// j : the length of the original fileArray input from local file
                            sequenceNum+=1;
                            byte message[]=new byte[1024];
                            message[0]=(byte)(sequenceNum>>8);
                            message[1]=(byte)(sequenceNum);
                            if(j+1021>= fileArray.length){
                                lastMessageFlag=true;
                                message[2]=(byte)(1);
                            }
                            else{
                                lastMessageFlag=false;
                                message[2]=(byte)(0);
                            }
                            int arrayLength=0;
                            //copy from the original array
                            if(!lastMessageFlag){
                                for(int k=0;k<=1020;k++){//k: write into the message array send to the datagram
                                 message[k+3]=fileArray[k+j];
                                    arrayLength++;
                                }
                            }
                            else if(lastMessageFlag){
                                for(int k=0;k<fileArray.length-j;k++){
                                    message[k+3]=fileArray[k+j];
                                    arrayLength++;
                                }
                            }
                            DatagramPacket sendMessage=new DatagramPacket(message, arrayLength+3,ip,10001);
                            ds.send(sendMessage);
                            System.out.println("Sent: Sequence number = " + sequenceNum + ", Flag = " + lastMessageFlag);
                            boolean ackReceiveCorrect=false;
                            boolean ackPacketReceived=false;
                            while(!ackReceiveCorrect){
                                byte []ackArray=new byte[2];
                                DatagramPacket ackDatagram=new DatagramPacket(ackArray,ackArray.length);
                                try{
                                    //ds.setSoTimeout(50);
                                    ds.receive(ackDatagram);
                                    ackNum=((ackArray[0]& 0xff)<<8)+(ackArray[1]& 0xff);
                                    ackPacketReceived=true;
                                }catch (SocketTimeoutException e){
                                    System.out.println("time out");
                                    ackPacketReceived=false;
                                }
                                //if right, then finish
                                if((ackNum==sequenceNum)&&ackPacketReceived){
                                    ackReceiveCorrect=true;
                                    System.out.println("received correct");
                                    break;
                                }
                                else{
                                    //else the system will send it again
                                    ds.send(sendMessage);
                                    System.out.println("resending"+sequenceNum);
                                }
                            }
                        }
                        is.close();
                        break;
                    }
                    else if (i== fileList.length-1&&!fileList[i].getName().equals(bookName)){
                        dp=new DatagramPacket("Error".getBytes(),"Error".getBytes().length,ip,10001);
                        status="Error";
                        ds.send(dp);
                    }
                }
            }
        }
    }
}
