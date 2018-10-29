package ar.edu.utn.frsf.dam.isi.laboratorio02.modelo;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import ar.edu.utn.frsf.dam.isi.laboratorio02.MyReceiver;
import ar.edu.utn.frsf.dam.isi.laboratorio02.R;
import ar.edu.utn.frsf.dam.isi.laboratorio02.dao.PedidoRepository;
import ar.edu.utn.frsf.dam.isi.laboratorio02.dao.ProductoRepository;
import ar.edu.utn.frsf.dam.isi.laboratorio02.producto_activity;

public class altaproducto extends AppCompatActivity {

    private RadioButton ingresarTipoEntrega1;
    private RadioButton ingresarTipoEntrega2;
    public TextView direccion;
    public Button btnAgregar;
    public Button btnHacerPedido;
    public Integer cant;
    public Integer cod;
    public Bundle miBun;
    ListView listaProdSeleccionados;
    TextView mostrarTotal;
    String horaIngresada;
    EditText correo;
    EditText horaIng;
    Bundle miBundle = new Bundle();
    Pedido elPedido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altaproducto);

        final Pedido unPedido;
        final PedidoRepository repositorioPedido;
        ProductoRepository repositorioProducto;
        Bundle miBun = this.getIntent().getExtras();

        mostrarTotal = (TextView) findViewById(R.id.idMostrarTotal);
        ingresarTipoEntrega1 = (RadioButton) findViewById(R.id.idRadioLocal);
        ingresarTipoEntrega2 = (RadioButton) findViewById(R.id.idRadioDomicilio);
        btnAgregar = (Button) findViewById(R.id.idBotonAgregarProd);
        correo = (EditText) findViewById(R.id.idIngCorreo);
        direccion = (TextView) findViewById(R.id.idIngDireDeEnvio);
        horaIng = (EditText) findViewById(R.id.idIngHoraSolicitada);
        PedidoRepository reposi;
        Pedido elPedido;
        final MyReceiver miReceiver = new MyReceiver();
        final Intent miIntent= new Intent();

        listaProdSeleccionados = findViewById(R.id.idListaProdAlta);
        repositorioPedido = new PedidoRepository();
        elPedido = new Pedido();

        //SI RECIBO INTENT CON IDPEDIDO, SETEO LOS CAMPOS PARA MOSTRAR UN PEDIDO YA EXISTENTE
        //INTENT QUE VIENE DE ADAPTER EN HISTORIALPRODUCTOS
        final Intent i1 = getIntent();
        int idPedido = 0;
        if (i1.getExtras() != null) {
            idPedido = i1.getExtras().getInt("idPedido");
        }
        if (idPedido > 0) {
            elPedido = repositorioPedido.buscarPorId(idPedido);
            correo.setText(elPedido.getMailContacto());
            direccion.setText(elPedido.getDireccionEnvio());
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            horaIng.setText((String) (elPedido.getFecha()).toString());
            ingresarTipoEntrega1.setChecked(!elPedido.getRetirar());
            ingresarTipoEntrega2.setChecked(elPedido.getRetirar());
        } else {
            elPedido = new Pedido();
        }

        //BOTON AGREGAR. USADO CUANDO SE QUIERE CREAR
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(altaproducto.this, producto_activity.class);
                startActivityForResult(i, 1234);
            }
        });

        btnHacerPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Pedido unPedido = new Pedido();

                /*String[] horaIngresada = editHora.getText().toString().split(":");
                GregorianCalendar hora = new GregorianCalendar();
                int valorHora = Integer.valueOf(horaIngresada[0]);
                int valorMinuto = Integer.valueOf(horaIngresada[1]);
               /if(valorHora<0 || valorHora>23){
                    Toast.makeText(PedidoActivity.this,
                            "La hora ingresada ("+valorHora+" es incorrecta",
                            Toast.LENGTH_LONG).show(); return;
                }
                if(valorMinuto <0 || valorMinuto >59){
                    Toast.makeText(PedidoActivity.this, "Los minutos ("+valorMinuto+" son incorrectos",
                            Toast.LENGTH_LONG).show();
                        return;
                }
                hora.set(Calendar.HOUR_OF_DAY,valorHora);
                hora.set(Calendar.MINUTE,valorMinuto);
                hora.set(Calendar.SECOND,Integer.valueOf(0));
                elPedido.setFecha(hora.getTime());*/

                // setear el resto de los atributos del pedido

                repositorioPedido.guardarPedido(unPedido);
                // lo seteamos a una nueva instancia para el proximo pedido

                Log.d("APP_LAB02", "Pedido " + unPedido.toString());
                //unPedido.setDetalle();
                if (correo.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(altaproducto.this, "Ingrese correo",
                            Toast.LENGTH_LONG).show();
                    unPedido.setMailContacto(correo.toString());
                }
                if (direccion.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(altaproducto.this, "Ingrese dirección",
                            Toast.LENGTH_LONG).show();
                    unPedido.setDireccionEnvio(direccion.toString());
                }
                unPedido.setEstado(Pedido.Estado.REALIZADO);
                repositorioPedido.guardarPedido(unPedido);

                miIntent.putExtra("estado","ESTADO_ACEPTADO");
                miIntent.putExtra("idPedido",unPedido.getId());

                miIntent.setAction(miReceiver.evento);
                sendBroadcast(miIntent);

            }
        });

    }

    public void onClick(View view) {
        if (view.getId() == R.id.idRadioDomicilio) {

            if (ingresarTipoEntrega2.isChecked() == true) {
                direccion.isEnabled();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ProductoRepository repositorioProducto = new ProductoRepository();
        PedidoDetalle detalleProd;
        Producto prod;
        int cod;
        int cant;
        int codProd;
        Double suma;

        if (requestCode == 1234 && resultCode == RESULT_OK) {
            cod = data.getExtras().getInt("idproducto");
            cant = data.getExtras().getInt("cantidad");
            prod = repositorioProducto.buscarPorId(cod);//devolvió un producto
            detalleProd = new PedidoDetalle(cod, prod);
            Pedido unPedido = new Pedido(); //ESTA VACIO?
            detalleProd.setPedido(unPedido);
            suma = cant * prod.getPrecio();
            mostrarTotal.setText("Total pedido: $" + suma);
        }
    }


}
