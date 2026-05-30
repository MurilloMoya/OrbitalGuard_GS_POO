package OrbitalGuard.Classes;

public class CalculadoraIPO {

    public static double calcularIPO(double riscoMedio, int quantidade, int eventosCriticos) {
        double ipo;
        ipo = 100 - (quantidade * 0.3 + riscoMedio * 0.5 + eventosCriticos * 0.2);
        // evita valor negativo
        if (ipo < 0) {
            ipo = 0;
        }
        // evita passar de 100 (opcional, mas mais realista)
        if (ipo > 100) {
            ipo = 100;
        }

        return ipo;
    }


    public static String classificarIPO(double ipo) {
        if (ipo >= 70) {
            return "Verde";
        }
        else if (ipo >= 40) {
            return "Amarelo";
        }
        else {
            return "Vermelho";
        }
    }
}
