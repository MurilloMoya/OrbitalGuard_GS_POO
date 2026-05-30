package OrbitalGuard.Classes;

import OrbitalGuard.Interfaces.StatusRelatorio;

public abstract class ObjetoEspacial implements StatusRelatorio {

    protected int idNorad;
    protected String nomeObjeto;
    protected double altitudeKm;
    protected double velocidadeKmS;
    protected Organizacao organizacao;

    public ObjetoEspacial(int idNorad, String nomeObjeto, double altitudeKm, double velocidadeKmS, Organizacao organizacao) {
        this.idNorad = idNorad;
        this.nomeObjeto = nomeObjeto;
        this.altitudeKm = altitudeKm;
        this.velocidadeKmS = velocidadeKmS;
        this.organizacao = organizacao;
    }

    public int getIdNorad() {
        return idNorad;
    }

    public String getNomeObjeto() {
        return nomeObjeto;
    }

    public double getAltitudeKm() {
        return altitudeKm;
    }

    public double getVelocidadeKmS() {
        return velocidadeKmS;
    }

    public Organizacao getOrganizacao() {
        return organizacao;
    }

    public abstract double calcularRiscoColisao();

    public abstract String getTipoObjeto();


    public String gerarRelatorioStatus() {
        return "Nome: " + nomeObjeto +
                "\nNORAD: " + idNorad +
                "\nTipo: " + getTipoObjeto() +
                "\nAltitude: " + altitudeKm +
                "\nVelocidade: " + velocidadeKmS +
                "\nRisco: " + calcularRiscoColisao();
    }
}