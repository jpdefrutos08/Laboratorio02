package ar.edu.utn.frsf.dam.isi.laboratorio02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnNuevoPedido;
    private Button btnHistorial;
    private Button btnListaProductos;
    private Bundle miBundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        btnHistorial = (Button) findViewById(R.id.btnHistorialPedidos);
        btnHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                //VER DONDE VA ESTO
                //Bundle miBundle = new Bundle();
                miBundle.putInt("NUEVO_PEDIDO",0);
                startActivity(i);

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
        Intent miIntent = new Intent(MainActivity.this, producto_activity.class);

        miIntent.putExtras(miBundle);

        startActivity(miIntent);
    }
}
