package OrbitalGuard.Classes;

import OrbitalGuard.Interfaces.Manobravel;

public class SateliteAtivo extends ObjetoEspacial implements Manobravel {

    private double combustivelKg;
    private int idadeAnos;

    public SateliteAtivo(int idNorad, String nomeObjeto, double altitudeKm, double velocidadeKmS, Organizacao organizacao, double combustivelKg, int idadeAnos) {
        super(idNorad, nomeObjeto, altitudeKm, velocidadeKmS, organizacao);
        this.combustivelKg = combustivelKg;
        this.idadeAnos = idadeAnos;
    }

    public double calcularAutonomiaOperacional() {
        return combustivelKg / 5;
    }

    @Override
    public double combustivelDisponivel() {
        return combustivelKg;
    }

    public boolean podeManobrar() {
        if (combustivelKg >= 5) {
            return true;
        } else {
            return false;
        }
    }


    public double calcularRiscoColisao() {
        double risco=0;

        risco= 40+(idadeAnos*2)-(combustivelKg*1.5);

        if (risco < 0) {
            risco = 0;
        }

        return risco;
    }


    public String getTipoObjeto() {
        return "Satélite Ativo";
    }
}
