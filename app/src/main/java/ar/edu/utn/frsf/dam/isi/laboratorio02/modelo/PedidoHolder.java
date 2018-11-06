package ar.edu.utn.frsf.dam.isi.laboratorio02.modelo;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PedidoHolder {
    public TextView tvMailPedido;
    public TextView tvHoraEntrega;
    public TextView tvCantidadItems;
    public TextView tvPrecio;
    public TextView estado;
    public ImageView tipoEntrega;
    public Button btnCancelar;

    public TextView getTvMailPedido() {
        return tvMailPedido;
    }

    public void setTvMailPedido(TextView tvMailPedido) {
        this.tvMailPedido = tvMailPedido;
    }

    public TextView getTvHoraEntrega() {
        return tvHoraEntrega;
    }

    public void setTvHoraEntrega(TextView tvHoraEntrega) {
        this.tvHoraEntrega = tvHoraEntrega;
    }

    public TextView getTvCantidadItems() {
        return tvCantidadItems;
    }

    public void setTvCantidadItems(TextView tvCantidadItems) {
        this.tvCantidadItems = tvCantidadItems;
    }

    public TextView getTvPrecio() {
        return tvPrecio;
    }

    public void setTvPrecio(TextView tvPrecio) {
        this.tvPrecio = tvPrecio;
    }

    public TextView getEstado() {
        return estado;
    }

    public void setEstado(TextView estado) {
        this.estado = estado;
    }

    public ImageView getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(ImageView tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public Button getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(Button btnCancelar) {
        this.btnCancelar = btnCancelar;
    }
}
