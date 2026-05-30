package OrbitalGuard.Classes;

import OrbitalGuard.Interfaces.StatusRelatorio;

public class Evento implements StatusRelatorio {

    private String tipo;
    private String descricao;
    private String dataHora;
    private int idNoradObjeto;
    private int gravidade;

    public Evento(String tipo, String descricao, String dataHora, int idNoradObjeto, int gravidade) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.idNoradObjeto = idNoradObjeto;
        this.gravidade = gravidade;
    }

    //OLHAR
    public boolean isCritico() {
        if (gravidade >= 4){
            return true;
        }else {
            return false;
        }
    }

    public int getGravidade() {
        return gravidade;
    }


    public String gerarRelatorioStatus() {
        return "Evento: " + tipo +
                "\nDescrição: " + descricao +
                "\nData: " + dataHora +
                "\nNORAD: " + idNoradObjeto +
                "\nGravidade: " + gravidade;
    }


    public String toString() {
        return gerarRelatorioStatus();
    }
}
