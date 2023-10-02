
//imports necessary for the code
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.io.ObjectOutputStream;


public class LSRoutingServerx {
    //initilisation of variables
    static int graph[][];
    static int nh[][];
    static int rtab[][];
    static int v;
    static int e;

    //main function
    public static void main(String args[]) throws Exception {
    	DatagramSocket ds1=new DatagramSocket(9997);
      System.out.println("Socket with port 9997 created");
        while (true) {
            byte[] in_data=new byte[1024];
            DatagramPacket dp1=new DatagramPacket(in_data,in_data.length);
            ds1.receive(dp1);
            String s=new String(dp1.getData());
            //String dummyString=""+s.charAt(0);
		String dummyString="";
		for(int i=0;i<s.length();i++)
		{
			if(s.charAt(i)>='0' && s.charAt(i)<='9')
			dummyString+=s.charAt(i);
			else break;
		}	
            v = Integer.parseInt(dummyString);
            byte[] in_data1=new byte[1024];
            DatagramPacket dp2=new DatagramPacket(in_data1,in_data1.length);
            ds1.receive(dp2);
            String s1=new String(dp2.getData());
		String dummy="";
		for(int i=0;i<s1.length();i++)
		{
			if(s1.charAt(i)>='0' && s1.charAt(i)<='9')
			dummyString+=s1.charAt(i);
			else break;
		}
            dummyString=""+s1.charAt(0);
            e = Integer.parseInt(dummyString);
            System.out.println(v);
            System.out.println(e);
            graph = new int[v][v];
            nh = new int[v][v];
            rtab = new int[v][v];
            InetAddress inet;
            inet=InetAddress.getByName("localhost");
	          byte[] incomingData = new byte[1024];
	  		  DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
	  		  ds1.receive(incomingPacket);
	  		  byte[] data1 = incomingPacket.getData();
	  		  ByteArrayInputStream in = new ByteArrayInputStream(data1);
	  		  ObjectInputStream is = new ObjectInputStream(in);
              graph = (int[][]) is.readObject();
              
              String cal="";
              for(int i=0;i<graph.length;i++)
              {
            	  cal+=routingcal(i);
              }
              byte arr[]=new byte[2048];
      		  arr=cal.getBytes();
              dp1=new DatagramPacket(arr,arr.length,inet,9994);	
    		  ds1.send(dp1);
              

//              ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//    		  ObjectOutputStream os = new ObjectOutputStream(outputStream);
//    		  os.writeObject(rtab);
//    		  byte[] data = outputStream.toByteArray();
//    		  DatagramPacket sendPacket = new DatagramPacket(data, data.length,inet, 9997);
//    		  ds1.send(sendPacket);
//    		  
//    		  ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
//    		  ObjectOutputStream os1 = new ObjectOutputStream(outputStream);
//    		  os1.writeObject(nh);
//    		  byte[] data2 = outputStream.toByteArray();
//    		  DatagramPacket sendPacket1 = new DatagramPacket(data2, data2.length,inet, 9997);
//    		  ds1.send(sendPacket1);
        
        }

    }

    //function to display tables
    static void display() {
        System.out.println();
        System.out.println();
    }

    //BFA Algorithm
    public static String routingcal(int source) {
        StringBuilder sb = new StringBuilder();
        int count = graph.length;

        boolean[] visitedVertex = new boolean[count];
        int[] distance = new int[count];
        for (int i = 0; i < count; i++) {
          visitedVertex[i] = false;
          distance[i] = Integer.MAX_VALUE;
        }
        distance[source] = 0;
        for (int i = 0; i < count; i++) {
          int u = findMinDistance(distance, visitedVertex);
          visitedVertex[u] = true;

          for (int v = 0; v < count; v++) {
            if (
              !visitedVertex[v] &&
              graph[u][v] != 0 &&
              (distance[u] + graph[u][v] < distance[v])
            ) {
              distance[v] = distance[u] + graph[u][v];
            }
          }
        }
        for (int i = 0; i < distance.length; i++) {
            if (i == 0) {
              sb.append(String.format("ROUTING TABLE FOR NODE %s\n\n", source));
              sb.append("Source Node    Destination Node    Least Cost\n");
            }
            sb.append(
              String.format("     %s                          %s                                %s\n",source,i,distance[i])
            );
            if (i == distance.length - 1) {
              return sb.toString();
            }
          }

          return sb.toString();
    }
    private static int findMinDistance(int[] distance, boolean[] visitedVertex) {
        int minDistance = Integer.MAX_VALUE;
        int minDistanceVertex = -1;
        for (int i = 0; i < distance.length; i++) {
          if (!visitedVertex[i] && distance[i] < minDistance) {
            minDistance = distance[i];
            minDistanceVertex = i;
          }
        }
        return minDistanceVertex;
      }
}

