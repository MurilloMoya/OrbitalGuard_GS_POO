package OrbitalGuard.Classes;

import OrbitalGuard.Interfaces.Manobravel;

public class EstagioFogueteAbandonado extends ObjetoEspacial implements Manobravel {

    private double massaKg;
    private double tamanhoM2;

    public EstagioFogueteAbandonado(int idNorad, String nomeObjeto, double altitudeKm, double velocidadeKmS, Organizacao organizacao, double massaKg, double tamanhoM2) {
        super(idNorad, nomeObjeto, altitudeKm, velocidadeKmS, organizacao);
        this.massaKg = massaKg;
        this.tamanhoM2 = tamanhoM2;
    }

    public double calcularRiscoReentrada() {
        double riscoReentrada;

        riscoReentrada= (massaKg / 10) + (tamanhoM2 * 2);

        if (riscoReentrada > 100) {
            riscoReentrada = 100;
        }

        return riscoReentrada;
    }
    public double calcularRiscoColisao() {
        double riscoColisao;

        riscoColisao = (massaKg / 10) + (tamanhoM2 * 4) + 30;

        if (riscoColisao > 100) {
            riscoColisao = 100;
        }
        return riscoColisao;
    }

    public double combustivelDisponivel() {
        double combustivel = 50 - (tamanhoM2 * 2);
        if (combustivel < 0) {
            combustivel = 0;
        }
        return combustivel;
    }

    public String podeManobrar() {
        if (combustivelDisponivel() > 5) {
            return "Sim, pode realizar manobra";
        } else {
            return "Não, combustível insuficiente";
        }
    }

    public String getTipoObjeto() {
        return "Estágio de Foguete Abandonado";
    }
}