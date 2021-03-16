package concurrentinpractice.six;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LifecycleWebServer {

    private final ExecutorService executorService = Executors.newFixedThreadPool(100);

    public void start() throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (!executorService.isShutdown()){
            final Socket conn = socket.accept();

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    //processRequest
                }
            });
        }
    }

    public void stop(){
        executorService.shutdown();
    }


}
