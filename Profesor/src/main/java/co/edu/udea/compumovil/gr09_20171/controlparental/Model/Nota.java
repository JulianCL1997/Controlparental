package co.edu.udea.compumovil.gr09_20171.controlparental.Model;

import java.io.Serializable;

/**
 * Created by julian on 24/05/17.
 */

public class Nota implements Serializable{

    private String desc;
    private float valor;

    public Nota() {
    }

    public Nota(String desc, float valor) {
        this.desc = desc;
        this.valor = valor;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
