package com.andreslab.canvasdraw;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Paint mTextPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        insertarImagen papel = new insertarImagen(this);
        setContentView(papel);
    }

   private class PapelView extends View {
       public PapelView(Context context){
           super(context);
       }

       protected void onDraw(Canvas canvas){
           super.onDraw(canvas);

           Paint paint = new Paint();
           paint.setColor(Color.GREEN);
           canvas.drawPaint(paint);

           //obtener dimensiones
           int ancho = canvas.getWidth();
           int alto = canvas.getHeight();
           int mitad = canvas.getWidth() / 2;

           paint.setColor(Color.BLACK);

           paint.setTextSize(40);
           paint.setAntiAlias(true);//para afinar detalles
           paint.setTextAlign(Paint.Align.CENTER);
           paint.setTextSkewX(0.2f); //inclinado
           //texto
           canvas.drawText("ancho = "+ ancho + "altura= "+ alto, ancho /2, 100, paint);

           //lineas
           paint.setColor(Color.WHITE);
           canvas.drawLine(0,40,ancho, 40, paint);//inicia en (0,40) y termina en (ancho, 40)
           canvas.drawLine(20,0,20,alto,paint);
       }
   }

   private class DimensionsView extends View{
       public DimensionsView(Context context){
           super(context);
       }

       protected  void onDraw(Canvas canvas){
           super.onDraw(canvas);

           canvas.drawColor(Color.BLACK);
           Paint paint = new Paint();
           paint.setColor(Color.WHITE);
           paint.setTextSize(50);
           paint.setAntiAlias(true);

           //propiedades reales del canvas
           int ancho = getMeasuredWidth();
           int alto = getMeasuredHeight();

           int ancho1 = getRight();
           int alto1 = getBottom();

           canvas.drawText("ancho: "+ancho,50,50,paint);
           canvas.drawText("alto: "+alto,50,120,paint);
           canvas.drawText("ancho1: "+ancho1,50,190,paint);
           canvas.drawText("alto1: "+alto1,50,260,paint);


       }
   }

    private class ShapesView extends View{
        public ShapesView(Context context){
            super(context);
        }

        protected  void onDraw(Canvas canvas){
            super.onDraw(canvas);

            canvas.drawColor(Color.BLACK);
            Paint paint = new Paint();

            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL); //relleno
            paint.setColor(Color.BLUE);

            //circulos
            canvas.drawCircle(50,50,30,paint);
            canvas.drawCircle(150,200,30,paint);

            //cuadrados
            paint.setColor(Color.WHITE);
            canvas.drawRect(300,400,100,250, paint);

            //
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.GREEN);
            paint.setStrokeWidth(10);
            canvas.drawRect(5,5, canvas.getWidth() - 5, canvas.getHeight() - 5, paint);
        }
    }

    private class PathView extends View{
        public PathView(Context context){
            super(context);
        }

        protected  void onDraw(Canvas canvas){
            super.onDraw(canvas);

            canvas.drawColor(Color.GREEN);
            Paint paint = new Paint();
            paint.setAntiAlias(true);

            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(5);

            int ancho = getMeasuredWidth();
            //crear nuevo objeto path y darle atributos
            Path path = new Path();
            path.moveTo(0,0);
            path.lineTo(ancho, 1);

            //definir los espacios de las lineas
            float[] espacio1 = {10,10};
            DashPathEffect estilo = new DashPathEffect(espacio1,1);
            paint.setPathEffect(estilo);

            path.offset(0,30);
            canvas.drawPath(path, paint);


            float[] espacio2 = {10,10, 2,10};
            DashPathEffect estilo2 = new DashPathEffect(espacio2,1);
            paint.setPathEffect(estilo2);
            path.offset(0,30);
            canvas.drawPath(path, paint);
            path.offset(0,60);
        }
    }

    private class curveTextView extends View{
        public curveTextView(Context context){
            super(context);
        }
        protected  void onDraw(Canvas canvas){
            super.onDraw(canvas);

            int ancho;
            float radio, comenzar, posicion;

            canvas.drawColor(Color.GRAY);

            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setTextSize(50);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);

            Path path = new Path();
            ancho = getMeasuredWidth();
            Path.Direction dir = Path.Direction.CW; // direccion de acuerdo con las manecilllas del reloj
            radio = 400;

            path.addCircle(ancho/2, getMeasuredHeight() / 2, radio, dir);
            path.offset(0,0); // smp se pone offset
            canvas.drawPath(path,paint);

            comenzar = 0;
            posicion = -10; //fuera del circulo
            paint.setColor(Color.BLUE);
            //para texto de afuera
            canvas.drawTextOnPath("hola mundo desde el canvas", path, comenzar,posicion, paint);


            //para texto de adentro
            comenzar = 300;
            posicion = 20; //dentro del circulo

            paint.setStyle(Paint.Style.FILL);
            paint.setTextSize(50);
            paint.setColor(Color.MAGENTA);
            canvas.drawTextOnPath("hola texto de adentro",path, comenzar, posicion, paint);


        }
    }

    private class interaccionView extends  View{

        float x = 100;
        float y = 150;
        public interaccionView(Context context){
            super(context);
        }

        protected void onDraw(Canvas canvas){
            Paint paint = new Paint();
            canvas.drawColor(Color.BLACK);

            paint.setAntiAlias(true);
            paint.setColor(Color.RED);

            canvas.drawCircle(x,y,20, paint);

            paint.setColor(Color.WHITE);
            paint.setTextSize(20);
            canvas.drawText("position de x = "+x, 80, 50, paint);
            canvas.drawText("position de y = "+y, 80, 70, paint);
            
        }

        public boolean onTouchEvent(MotionEvent evento){
            if(evento.getAction() == MotionEvent.ACTION_DOWN)
            {
                x = evento.getX();
                y = evento.getY();
                invalidate(); //
            }
            return true;
        }
    }

    private class interaccionView2 extends  View{

        float x = 100;
        float y = 150;
        public interaccionView2(Context context){
            super(context);
        }

        protected void onDraw(Canvas canvas){
            Paint paint = new Paint();
            canvas.drawColor(Color.BLACK);

            paint.setAntiAlias(true);
            paint.setColor(Color.RED);

            canvas.drawCircle(x,y,20, paint);

            paint.setColor(Color.WHITE);
            paint.setTextSize(20);
            canvas.drawText("position de x = "+x, 80, 50, paint);
            canvas.drawText("position de y = "+y, 80, 70, paint);

        }

        public boolean onTouchEvent(MotionEvent evento){
            if(evento.getAction() == MotionEvent.ACTION_MOVE)
            {
                x = evento.getX();
                y = evento.getY();
                invalidate(); //
            }
            return true;
        }
    }

    private class interaccionView3 extends  View{

        float x = 100;
        float y = 150;
        public interaccionView3(Context context){
            super(context);
        }

        protected void onDraw(Canvas canvas){
            Paint paint = new Paint();
            canvas.drawColor(Color.BLACK);

            paint.setAntiAlias(true);
            paint.setColor(Color.RED);

            canvas.drawCircle(x,y,20, paint);

            paint.setColor(Color.WHITE);
            paint.setTextSize(20);
            canvas.drawText("position de x = "+x, 80, 50, paint);
            canvas.drawText("position de y = "+y, 80, 70, paint);

        }

        public boolean onTouchEvent(MotionEvent evento){
            if(evento.getAction() == MotionEvent.ACTION_UP)
            {
                x = evento.getX();
                y = evento.getY();
                invalidate(); //
            }
            return true;
        }
    }

    private class interaccionView_total extends  View{

        float x = 100;
        float y = 150;
        String action = "ninguno";
        public interaccionView_total(Context context){
            super(context);
        }

        protected void onDraw(Canvas canvas){
            Paint paint = new Paint();
            canvas.drawColor(Color.BLACK);

            paint.setAntiAlias(true);
            paint.setColor(Color.RED);

            canvas.drawCircle(x,y,20, paint);

            paint.setColor(Color.WHITE);
            paint.setTextSize(20);
            canvas.drawText("evento ejecutadose: "+action, 80, 50, paint);

        }

        public boolean onTouchEvent(MotionEvent evento){
            if(evento.getAction() == MotionEvent.ACTION_DOWN)
            {
               action = "action down";
            }else if (evento.getAction() == MotionEvent.ACTION_MOVE){
                action = "action move";
            }else if (evento.getAction() == MotionEvent.ACTION_UP){
                action = "action up";
            }

            x = evento.getX();
            y = evento.getY();
            invalidate(); //
            return true;
        }
    }

    private class pintar extends  View{

        float x = 50;
        float y = 50;
        Path path = new Path();
        String action = "ninguno";
        public pintar(Context context){
            super(context);
        }

        protected void onDraw(Canvas canvas){
            Paint paint = new Paint();
            canvas.drawColor(Color.GREEN);

            paint.setAntiAlias(true);
            //paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(5);
            //argb
            paint.setColor(Color.argb(250,110,70,200));


            if(action == "down"){
                path.moveTo(x,y);
            }
            if(action == "move"){
                path.lineTo(x,y); //agregamos una linea;
            }
            canvas.drawPath(path, paint);

        }

        public boolean onTouchEvent(MotionEvent evento){
            x = evento.getX();
            y = evento.getY();
            if(evento.getAction() == MotionEvent.ACTION_DOWN)
            {
                action = "down";

            }
            if(evento.getAction() == MotionEvent.ACTION_MOVE)
            {
                action = "move";

            }
            invalidate(); //
            return true;
        }
    }


    private class arrastrarObjetos extends  View{

        float[] x = {50,130};
        float[] y = {50, 100};
        float[] radio = {20,30};

        Paint paint[] = new Paint[2];
        Paint p;

        int circulo = -1;
        String txt = "mueve algun circulo";

        public arrastrarObjetos(Context context){
            super(context);
            paint[0] = new Paint();
            paint[0].setAntiAlias(true);
            paint[0].setColor(Color.BLACK);


            paint[1] = new Paint();
            paint[1].setAntiAlias(true);
            paint[1].setColor(Color.GREEN);


            p = new Paint();
            p.setAntiAlias(true);
            p.setColor(Color.BLUE);
            p.setTextSize(40);
        }

        protected void onDraw(Canvas canvas){
            canvas.drawColor(Color.argb(255,200,200,150));
            canvas.drawText(txt, 50,30,p);

            for(int i = 0; i < 2; i++){
                canvas.drawCircle(x[i], y[i], radio[i], paint[i]);
            }

        }

        public boolean onTouchEvent(MotionEvent evento){
           float getx = evento.getX();
           float gety = evento.getY();
            int accion = evento.getAction();

            if(accion == MotionEvent.ACTION_DOWN){
                for(int i = 0; i<2; i++){
                    double cenx = getx - x[i];
                    double ceny = gety - y[i];

                    //funcion para saber si se toca el cieculo
                    float distancia = (float)Math.sqrt(cenx * cenx + ceny * ceny);
                    if(distancia <= radio[i]){
                        //circulo fe tocado
                        circulo = i;
                        txt = "el circulo tocado es "+i;
                        invalidate();
                    }

                }
            }

            if(accion == MotionEvent.ACTION_MOVE){
                if (circulo > -1) {
                    x[circulo] = getx;
                    y[circulo] = gety;
                    invalidate();
                }
            }
            return true;
        }
    }

    private class insertarImagen extends View {
        Drawable imagen;
        public insertarImagen(Context context) {
            super(context);
            imagen = context.getResources().getDrawable(R.mipmap.ic_launcher);
        }

        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            canvas.drawColor(Color.GREEN);
            Paint paint = new Paint();
            paint.setAntiAlias(true);


            int ancho_imagen = imagen.getIntrinsicWidth();
            int alto_imagen = imagen.getIntrinsicHeight();

            imagen.setBounds(0,0,ancho_imagen,alto_imagen);
            imagen.draw(canvas);
        }
    }

    private class insertarImagenGrandeEscalandola extends View {
        Drawable imagen;
        public insertarImagenGrandeEscalandola(Context context) {
            super(context);
            imagen = context.getResources().getDrawable(R.drawable.carretera);
        }

        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            canvas.drawColor(Color.GREEN);
            Paint paint = new Paint();
            paint.setAntiAlias(true);

            //dimensioes efectivas del canvas
            int altoCanvas = getBottom();
            int anchoCanvas = getRight();
            float medioCanvas = (float)altoCanvas/anchoCanvas;


            int ancho_imagen = imagen.getIntrinsicWidth();
            int alto_imagen = imagen.getIntrinsicHeight();
            float mdio_imagen = (float)alto_imagen/ancho_imagen;


            imagen.setBounds(0,0,anchoCanvas,altoCanvas);
            imagen.draw(canvas);
        }
    }
}
