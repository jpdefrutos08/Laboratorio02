package ar.edu.utn.frsf.dam.isi.laboratorio02.modelo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import ar.edu.utn.frsf.dam.isi.laboratorio02.PedidoAdapter;
import ar.edu.utn.frsf.dam.isi.laboratorio02.R;
import ar.edu.utn.frsf.dam.isi.laboratorio02.dao.PedidoRepository;

public class HistorialProductos extends AppCompatActivity {
    PedidoRepository reposi;

    private PedidoAdapter adapter;
    PedidoHolder pedidoH;
    Pedido pediAux;
    String correo;
    String mail;
    Long hora;
    String cant;
    Double precio;
    Enum estadoAux;
    private ListView listaHistorial;
    ArrayList listaPedidos;
    TextView textAux;
    Button btnCancelarPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_productos);

        ArrayList<PedidoHolder> arrayPedidos = new ArrayList<>();
        PedidoHolder ped = new PedidoHolder();


        listaHistorial = (ListView) findViewById(R.id.idListaHistorial);
        reposi = new PedidoRepository();
        listaPedidos = new ArrayList(reposi.getLista()); //traigo los Pedidos del repo como un array
        adapter = new PedidoAdapter(getApplicationContext(), listaPedidos);

        listaHistorial.setAdapter(adapter);

        //DONDE RECORRE TODA LA LISTA CON FOR PARA IR GENERANDO?

      /*for (int i=0; i<reposi.getLista().size();i++){
          //OBTENGO VALORES DE CADA PEDIDO
            correo = ((Pedido)listaPedidos.get(i)).getMailContacto();
            hora =  (Long) ((Pedido)listaPedidos.get(i)).getFecha().getTime();
            precio = (((PedidoDetalle)(((Pedido)listaPedidos.get(i)).getDetalle())).getProducto()).getPrecio();
            estadoAux = ((Pedido)listaPedidos.get(i)).getEstado();
            //CARGO VALORES EN UN OBJ PEDIDOHOLDER
            ped.tvMailPedido.setText(correo);
            ped.tvHoraEntrega.setText(hora.toString());
            ped.tvPrecio.setText(precio.toString());
            ped.estado.setText(estadoAux.toString());
            arrayPedidos.add(ped); //AGREGO A LA LISTA DE PEDIDOSHOLDER
      }
      */
    }
}


