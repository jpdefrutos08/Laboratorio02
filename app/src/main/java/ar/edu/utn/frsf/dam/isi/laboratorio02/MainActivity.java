package ar.edu.utn.frsf.dam.isi.laboratorio02;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.HistorialProductos;

public class MainActivity extends AppCompatActivity {

    private Button btnNuevoPedido;
    private Button btnHistorial;
    private Button btnListaProductos;
    private Bundle miBundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();

        btnNuevoPedido = (Button) findViewById(R.id.btnMainNuevoPedido);
        btnNuevoPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                //VER DONDE VA ESTO
                //Bundle miBundle = new Bundle();
                miBundle.putInt("NUEVO_PEDIDO",1);
                startActivity(i);
            }
        });

        btnHistorial = (Button) findViewById(R.id.btnHistorialPedidos);//BOTON HISTORIAL
        btnHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                miBundle.putInt("NUEVO_PEDIDO",0);
                Intent miIntent2 = new Intent(MainActivity.this, HistorialProductos.class);
                startActivity(miIntent2);

            }
        });

        btnListaProductos = (Button) findViewById(R.id.btnListaProductos);
        btnListaProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                //VER DONDE VA ESTO
                //Bundle miBundle = new Bundle();
                miBundle.putInt("NUEVO_PEDIDO",0);
                startActivity(i);
            }
        });


    }

    public void onClick(View view) {
        //PRIMER PTO QUE SALTA A PRODUCTO_ACTIVITY DONDE BUSCA LOS PRODUCTOS
        //Intent miIntent = new Intent(MainActivity.this, producto_activity.class);
        //miIntent.putExtras(miBundle);
        //startActivity(miIntent);

        //PUNTO 3. INTENT Q VA A ACTIVITY_ALTAPRODUCTO
        Intent miIntent = new Intent(MainActivity.this, altaproducto.class);
        startActivity(miIntent);
    }

    private void createNotificationChannel(){
        // Crear el canal de notificaciones pero solo para API 26 io superior
        // dado que NotificationChannel es una clase nueva que no está incluida
        // en las librerías de soporte qeu brindan compatibilidad hacía atrás

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.canal_estado_nombre);
            String description = getString(R.string.canal_estado_descr);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("CANAL01", name, importance);
            channel.setDescription(description);

            // Registrar el canal en el sistema
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

            //REVISAR. SERÍA PARA CLICK SOBRE NOTIFICACIÓN Y Q VAYA A ALTAPRODUCTO
            Intent intent = new Intent(MainActivity.this, altaproducto.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0,intent, 0);

        }
    }

}
