package co.edu.udea.compumovil.gr09_20171.usuario.Models;

/**
 * Created by julian on 24/05/17.
 */

public class Nota {
private String desc;
private String valor;

    public Nota() {
    }

    public Nota(String desc, String valor) {
        this.desc = desc;
        this.valor = valor;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
