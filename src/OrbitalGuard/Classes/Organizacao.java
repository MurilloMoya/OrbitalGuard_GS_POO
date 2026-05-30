package OrbitalGuard.Classes;

public class Organizacao {

    private String nome;
    private String pais;
    private String sigla;

    public Organizacao(String nome, String pais, String sigla) {
        this.nome = nome;
        this.pais = pais;
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public String toString() {
        return nome + " (" + sigla + ") - " + pais;
    }
}