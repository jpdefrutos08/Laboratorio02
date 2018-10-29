package ar.edu.utn.frsf.dam.isi.laboratorio02;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;

import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.HistorialProductos;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Pedido;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.PedidoDetalle;

public class PedidoAdapter extends BaseAdapter{

    private Context ctx;
    private List<Pedido> datos;

    public PedidoAdapter(Context context,List<Pedido> objects) {
       // super(context, 0, objects);
        this.ctx = context;
        this.datos = objects;
    }

    public Context getCtx() {
        return ctx;
    }
    public void setCtx(Context ctx) {
        this.ctx = ctx;
    }
    public List<Pedido> getDatos() {
        return datos;
    }
    public void setDatos(List<Pedido> datos) {
        this.datos = datos;
    }

    @Override
    public int getCount() {
        return datos.size();
    }
    @Override
    public Object getItem(int position) {
        return datos.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                LayoutInflater layoutInflater = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.fila_historial,null);

                TextView contacto = (TextView) convertView.findViewById(R.id.idMostrarContacto);
                TextView fechaEntrega = (TextView) convertView.findViewById(R.id.idMostrarFechaEntrega);
                TextView item = (TextView) convertView.findViewById(R.id.idMostrarItems);
                TextView mostrarPagar = (TextView) convertView.findViewById(R.id.idMostrarPagar);
                TextView unEstado = (TextView) convertView.findViewById(R.id.idMostrarEstado);
                Button btnCancelar = (Button) convertView.findViewById(R.id.idBotonCancelar);
                Button btnVerDetalle = (Button) convertView.findViewById(R.id.idBtnVerDetalle);
                Intent intent;

                contacto.setText(datos.get(position).getMailContacto());
                fechaEntrega.setText(datos.get(position).getFecha().toString());
                item.setText("Items:" +((PedidoDetalle)(datos.get(position).getDetalle())).getCantidad() +
                        ((PedidoDetalle)(datos.get(position).getDetalle())).getId());

                Integer cantPro= ((PedidoDetalle)(datos.get(position).getDetalle())).getCantidad();
                Double precioProd = (Double) (((PedidoDetalle)(datos.get(position).getDetalle())).getProducto()).getPrecio();
                mostrarPagar.setText("PAGAR:" +cantPro*precioProd);
                unEstado.setText(datos.get(position).getEstado().toString());

                switch (datos.get(position).getEstado()){
                    case LISTO:
                       unEstado.setTextColor(Color.DKGRAY);
                        break;
                    case ENTREGADO:
                        unEstado.setTextColor(Color.BLUE);
                        break;
                    case CANCELADO:
                    case RECHAZADO:
                        unEstado.setTextColor(Color.RED);
                        break;
                    case ACEPTADO:
                        unEstado.setTextColor(Color.GREEN);
                        break;
                    case EN_PREPARACION:
                        unEstado.setTextColor(Color.MAGENTA);
                        break;
                    case REALIZADO:
                        unEstado.setTextColor(Color.BLUE);
                        break;
                }
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int indice = (int) view.getTag();
                        Pedido pedidoSeleccionado = datos.get(indice);
                        if( pedidoSeleccionado.getEstado().equals(Pedido.Estado.REALIZADO)||
                                pedidoSeleccionado.getEstado().equals(Pedido.Estado.ACEPTADO)||
                                pedidoSeleccionado.getEstado().equals(Pedido.Estado.EN_PREPARACION)){

                            pedidoSeleccionado.setEstado(Pedido.Estado.CANCELADO);
                            PedidoAdapter.this.notifyDataSetChanged();
                        }
                    }
                };

                btnVerDetalle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int indice = (int) view.getTag();
                        int idPedido= ((PedidoDetalle)(datos.get(indice).getDetalle())).getId();
                        Intent miIntent = new Intent(ctx, altaproducto.class);
                        Bundle miBundle = new Bundle();
                        miBundle.putInt("idPedido",idPedido);
                        ctx.startActivity(miIntent, miBundle);

                    }
                });


            return convertView;
            }
        return null;
    }







}
