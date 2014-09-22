package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;


public class MyScreen extends SurfaceView implements Runnable {

    SurfaceHolder holder;
    int[][] field = null;
    int width;
    int height;
    int scale = 4;
    final int MAX_COLOR = 10;
    int[] palette = {0xFFFF0000, 0xFF800000, 0xFF808000, 0xFF008000, 0xFF00FF00, 0xFF008080, 0xFF0000FF, 0xFF000080, 0xFF800080, 0xFFFFFFFF};
    volatile boolean run = false;
    Thread thread = null;
    int[] pixels = null;


    public MyScreen(Context context) {
        super(context);
        holder = getHolder();
    }

    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH){
        width = w/scale;
        height = h/scale;
        initField();
    }

    @Override
    public void onDraw(Canvas canvas){
        //Paint paint = new Paint();
        //Log.i("TEST","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        /*Bitmap bitmap = Bitmap.createBitmap(pixels, 0, width, width, height, Bitmap.Config.RGB_565);
        canvas.drawBitmap(bitmap, 0, 0, null);
        canvas.drawBitmap(bitmap,new Rect());*/
        canvas.drawBitmap(pixels, 0, width, 0, 0, width, height, false, null);
        canvas.scale(scale, scale);
        /*for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                paint.setColor(palette[field[x][y]]);
                canvas.drawRect(x*scale, y*scale, (x+1)*scale, (y+1)*scale, paint);
            }
        }*/
    }

    public void initField(){
        //field = new int[width][height];
        pixels = new int[width * height];
        Random rnd = new Random();
        for(int x = 0; x < width; x++)
            for(int y = 0; y < height; y++){
                //field[x][y] = rnd.nextInt(MAX_COLOR);
                pixels[x  + y * width] = palette[rnd.nextInt(MAX_COLOR)];
            }
    }

    public void Resume() {
        Log.i("asdasadad","sadadasdadad");
        run = true;
        thread = new Thread(this);
        thread.start();
    }

    public void Pause(){
        run = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while(run){
            if(holder.getSurface().isValid()) {
                Canvas canvas = holder.lockCanvas();
                onDraw(canvas);
                holder.unlockCanvasAndPost(canvas);
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
