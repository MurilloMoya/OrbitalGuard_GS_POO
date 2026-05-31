package OrbitalGuard.Classes;

import OrbitalGuard.Interfaces.Manobravel;

public class SateliteDesativado extends ObjetoEspacial implements Manobravel {

    private int anosInativo;
    private double pesoKg;

    public SateliteDesativado(int idNorad, String nomeObjeto, double altitudeKm, double velocidadeKmS, Organizacao organizacao, int anosInativo, double pesoKg) {
        super(idNorad, nomeObjeto, altitudeKm, velocidadeKmS, organizacao);
        this.anosInativo = anosInativo;
        this.pesoKg = pesoKg;
    }

    public double calcularVidaUtil() {
        double vidaUtil = 100 - (anosInativo * 5) - (pesoKg * 0.05);
        if (vidaUtil < 0) {
            vidaUtil = 0;
        }
        return vidaUtil;
    }


    public double combustivelDisponivel() {
        double combustivel = 100 - (anosInativo * 15) - (pesoKg * 0.02);
        if (combustivel < 0) {
            combustivel = 0;
        }
        return combustivel;
    }

    public String podeManobrar() {
        if (combustivelDisponivel() > 10) {
            return "Sim, pode realizar manobra";
        } else {
            return "Não, combustível insuficiente";
        }
    }

    public double calcularRiscoColisao() {
        double risco = 40 + (anosInativo * 3) + (pesoKg * 0.1);
        if (risco > 100) {
            risco = 100;
        }
        return risco;
    }

    public String getTipoObjeto() {
        return "Satélite Desativado";
    }
}