package ar.edu.utn.frsf.dam.isi.laboratorio02;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.app.Service;
import android.os.Message;
import android.os.SystemClock;

import java.util.ArrayList;

import ar.edu.utn.frsf.dam.isi.laboratorio02.dao.PedidoRepository;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Pedido;

public class PrepararPedidoService extends IntentService {

    public PrepararPedidoService() {
        super("PrepararPedidoService");
    }

    @Override protected void onHandleIntent(Intent intent) {
        String name ="";
        PedidoRepository reposi= new PedidoRepository();
        ArrayList arrayProductos;
        final MyReceiver miReceiver = new MyReceiver();

        name = Thread.currentThread().getName();
        //Thread.sleep(20000);
        SystemClock.sleep(20000);
        arrayProductos=new ArrayList(reposi.getLista());
        Intent intentAux=new Intent();
        Pedido ped;

        for (int i=0; i<arrayProductos.size();i++){
        ped = (Pedido) arrayProductos.get(i);

        if (ped.getEstado().equals(Pedido.Estado.ACEPTADO)) {
                ped.setEstado(Pedido.Estado.EN_PREPARACION);
                //envio broadcast
                intentAux.putExtra("estado","ESTADO_EN_PREPARACION");
                intentAux.putExtra("idPedido",ped.getId());

                intentAux.setAction(miReceiver.evento);
                sendBroadcast(intentAux);

         }
        }


    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

