package OrbitalGuard.Classes;

import OrbitalGuard.Interfaces.StatusRelatorio;

public class Evento implements StatusRelatorio {

    private String tipo;
    private String descricao;
    private String data;
    private String hora;
    private int idNoradObjeto;
    private int gravidade;

    public Evento(String data, String descricao, int gravidade, String hora, int idNoradObjeto, String tipo) {
        this.data = data;
        this.descricao = descricao;
        this.gravidade = gravidade;
        this.hora = hora;
        this.idNoradObjeto = idNoradObjeto;
        this.tipo = tipo;
    }

    public boolean isCritico() {
        if (gravidade >= 4) {
            return true;
        } else {
            return false;
        }
    }

    public int getGravidade() {
        return gravidade;
    }

    public String gerarRelatorioStatus() {
        String aux = "";
        aux += "Evento: " + tipo + "\n";
        aux += "Descrição: " + descricao + "\n";
        aux += "Data: " + data + "\n";
        aux += "Hora: " + hora + "\n";
        aux += "NORAD: " + idNoradObjeto + "\n";
        aux += "Gravidade: " + gravidade;
        return aux;
    }
}