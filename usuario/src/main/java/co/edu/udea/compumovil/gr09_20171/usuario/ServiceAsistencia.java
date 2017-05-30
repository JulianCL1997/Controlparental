package co.edu.udea.compumovil.gr09_20171.usuario;

import android.app.Service;

import android.content.Intent;
import android.os.*;
import android.os.Process;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class ServiceAsistencia extends Service {

    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;
    private Timer timer;
    private TimerTask timerTask;

    @Override
    public void onCreate() {


        // To avoid cpu-blocking, we create a background handler to run our service
        HandlerThread thread = new HandlerThread("ControlParental", Process.THREAD_PRIORITY_BACKGROUND);
        // start the new handler thread
        thread.start();

        mServiceLooper = thread.getLooper();
        // start the service using the background handler
        mServiceHandler = new ServiceHandler(mServiceLooper);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, final int startId) {
        Toast.makeText(this, "onStartCommand", Toast.LENGTH_SHORT).show();

        // call a new service handler. The service ID can be used to identify the service
        Toast.makeText(getApplicationContext(), "hey", Toast.LENGTH_SHORT).show();

        Message message = mServiceHandler.obtainMessage();
        message.arg1 = startId;
        mServiceHandler.sendMessage(message);




        return START_STICKY;
    }



    protected void showToast(final String msg){
        //gets the main thread
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                // run this code in the main thread
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // Object responsible for
    private final class ServiceHandler extends Handler {

        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            // Well calling mServiceHandler.sendMessage(message); from onStartCommand,
            // this method will be called.

            // Add your cpu-blocking activity here
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            showToast("Finishing TutorialService, id: " + msg.arg1);
            // the msg.arg1 is the startId used in the onStartCommand, so we can track the running sevice here.
        }
    }

    @Override
    public void onDestroy() {
        Toast.makeText(getApplicationContext(), "Servicio destruido", Toast.LENGTH_SHORT).show();
    }
}
