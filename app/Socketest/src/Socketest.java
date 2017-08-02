import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Socketest {

    public static void main(String[] args) throws IOException {

    	Socketest sockettest = new Socketest();

    }

    IndicadorFrame indicadorFrame;

    public Socketest() throws IOException {

    	indicadorFrame = new IndicadorFrame();

    	ServerSocket serverSocket = new ServerSocket(4444);
        try {

        	while(true) {
        		Servicio servicio = new Servicio(serverSocket.accept());
        		//System.out.println("nuevo cliente");
        		servicio.start();
        	}



        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }




        serverSocket.close();
    }


    public class Servicio extends Thread {
    	Socket socket;
    	public Servicio(Socket socket) {
    		this.socket = socket;
    	}

    	public void run() {
    		try {
    		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    		String inputLine;
            //int i = 0;
            while ((inputLine = in.readLine()) != null) {
            	System.out.println(inputLine);

            	double valor1 = (double)Double.valueOf(inputLine).doubleValue();
            	
            	int valor = (int)valor1;

            	indicadorFrame.setValor(valor);
            }
            //out.close();
            in.close();
            socket.close();
    		} catch (IOException e) {
    			System.err.println(e.getMessage());
    		}
    	}
    }
}
