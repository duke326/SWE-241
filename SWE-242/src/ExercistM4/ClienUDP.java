package ExercistM4;

import java.io.IOException;
import java.net.*;

public class ClienUDP {


    public static void main(String[] args) throws IOException {
        InetAddress ip=InetAddress.getByName("127.0.0.1");
        DatagramSocket ds=new DatagramSocket(10001);
        //distinguish between the "index" and "get "
        String s=args[0];
        DatagramPacket dp=new DatagramPacket(s.getBytes(),s.getBytes().length,InetAddress.getByName("127.0.0.1"),10000 );
        ds.send(dp);
        if(args.length>1){
            String s1=args[1];
            dp=new DatagramPacket(s1.getBytes(), s1.getBytes().length,ip,10000);
            ds.send(dp);
        }



        String str="start";
        int lastSequenceNum=0;
        int sequenceNum=0;
        //boolean lastMessage=false;
        boolean lastMessageFlag=false;
        if(s.equals("index")){
            while(!str.equals("end")){
                byte[] receive=new byte[1024];
                DatagramPacket dpGet=new DatagramPacket(receive, receive.length);
                ds.receive(dpGet);
                int length= dpGet.getLength();
                str=new String(receive, 0, length);
                if(!str.equals("end")){
                    System.out.println(str);
                }


            }
        }
        else if (s.equals("get")){
            //receive the "ok" signal
            byte [] isExist=new byte[1024];
            dp=new DatagramPacket(isExist, isExist.length);
            ds.receive(dp);
            String status=new String(dp.getData(),0,dp.getLength());
            System.out.println(status);

            if(status.equals("OK")){
                while(!lastMessageFlag){
                    byte[] message=new byte[1024];
                    byte[] fileArray=new byte[1021];
                    DatagramPacket received=new DatagramPacket(message, message.length);
                    ds.setSoTimeout(0);
                    ds.receive(received);
                    message=received.getData();
                    sequenceNum=((message[0]&0xff)<<8)+(message[1]&0xff);
                    if ((message[2] & 0xff) == 1) {
                        lastMessageFlag = true;
                    } else {
                        lastMessageFlag = false;
                    }
                    if(sequenceNum==lastSequenceNum+1){
                        lastSequenceNum=sequenceNum;
                        for(int i=3;i< received.getLength();i++){
                            //System.out.println(message[i]);
                            fileArray[i-3]=message[i];
                        }
                        //System.out.println(received.getLength());
                        System.out.print(new String(fileArray,0, fileArray.length).trim());
                        sendAck(lastSequenceNum,ds,ip,10000);
                    }
                    else{
                        System.out.println("pack Lost");
                        sendAck(lastSequenceNum, ds,ip,10000);
                    }

                }
            }







        }
        ds.close();
    }

    //send the ack signal
    public static void sendAck( int lastSequenceNumber, DatagramSocket ds, InetAddress ip, int port) throws IOException {
        byte[] ack=new byte[2];
        //ack packet contains the series number of received package
        ack[0]=(byte)(lastSequenceNumber>>8);
        ack[1]=(byte)lastSequenceNumber;
        DatagramPacket dp=new DatagramPacket(ack, ack.length,ip,port);
        ds.send(dp);
        //System.out.println("ack send");


    }
}
