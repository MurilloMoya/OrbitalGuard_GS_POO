package OrbitalGuard.Util;

import OrbitalGuard.Classes.*;
import OrbitalGuard.Interfaces.Manobravel;

import static javax.swing.JOptionPane.*;
import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;
import java.util.ArrayList;

public class Util {

    private ArrayList<ObjetoEspacial> objetos = new ArrayList<>();
    private ArrayList<Evento> eventos = new ArrayList<>();

    public void menu() {
        int opcao;
        String menu;
        do {
            menu  = "OrbitalGuard Brasil\n";
            menu += "[1] Cadastrar objeto\n";
            menu += "[2] Listar objetos\n";
            menu += "[3] Calcular por entidade\n";
            menu += "[4] Registrar evento\n";
            menu += "[5] Listar eventos\n";
            menu += "[6] Calcular IPO\n";
            menu += "[7] Relatório geral\n";
            menu += "[8] Sair\n";

            opcao = parseInt(showInputDialog(menu));

            switch (opcao) {
                case 1 -> cadastrarObjeto();
                case 2 -> listarObjetos();
                case 3 -> calcularPorEntidade();
                case 4 -> registrarEvento();
                case 5 -> listarEventos();
                case 6 -> calcularIPO();
                case 7 -> relatorioGeral();
                case 8 -> showMessageDialog(null, "Sistema encerrado.");
                default -> showMessageDialog(null, "Opção inválida.");
            }

        } while (opcao != 8);
    }

    private void cadastrarObjeto() {
        String nome,nomeOrg,pais,sigla;
        int tipo,idNorad,anosAbandono,idade;
        double altitude,velocidade,massa,combustivel;

        tipo = parseInt(showInputDialog("Escolha o tipo do objeto:\n1 - Satélite Ativo\n2 - Estágio de Foguete Abandonado"));

        while (tipo != 1 && tipo != 2) {
            tipo = parseInt(showInputDialog("Opção inválida!\nDigite 1 ou 2:"));
        }

        idNorad= parseInt(showInputDialog("ID NORAD (número):"));
        nome = showInputDialog("Nome do objeto:");
        altitude = parseDouble(showInputDialog("Altitude (km):"));
        velocidade= parseDouble(showInputDialog("Velocidade (km/s):"));
        nomeOrg= showInputDialog("Nome da organização:");
        pais= showInputDialog("País:");
        sigla= showInputDialog("Sigla da organização:");

        Organizacao org = new Organizacao(nomeOrg, pais, sigla);

        if (tipo == 1) {
            combustivel= parseDouble(showInputDialog("Combustível (kg):"));
            idade= parseInt(showInputDialog("Idade (anos):"));

            SateliteAtivo satelite = new SateliteAtivo(idNorad, nome, altitude, velocidade, org, combustivel, idade);
            objetos.add(satelite);

            showMessageDialog(null, "Satélite ativo cadastrado com sucesso!");

        } else {
            massa= parseDouble(showInputDialog("Massa (kg):"));
            anosAbandono = parseInt(showInputDialog("Anos abandonado:"));

            EstagioFogueteAbandonado estagio = new EstagioFogueteAbandonado(idNorad, nome, altitude, velocidade, org, anosAbandono, massa);
            objetos.add(estagio);

            showMessageDialog(null, "Estágio de foguete cadastrado com sucesso!");
        }
    }

    private void listarObjetos() {
        String lista = "";
        int num = 1;

        for (ObjetoEspacial obj : objetos) {
            lista += "---- Objeto " + num + " ----\n";
            lista += "Tipo: " + obj.getTipoObjeto() + "\n";
            lista += "Nome: " + obj.getNomeObjeto() + "\n";
            lista += "ID NORAD: " + obj.getIdNorad() + "\n";
            lista += "Altitude: " + obj.getAltitudeKm() + " km\n";
            lista += "Risco colisão: " + String.format("%.2f", obj.calcularRiscoColisao()) + "\n\n";
            num++;
        }

        if (num == 1) {
            showMessageDialog(null, "Nenhum objeto cadastrado.");
        } else {
            showMessageDialog(null, lista);
        }
    }

    private void calcularPorEntidade() {

        int id = parseInt(showInputDialog("Digite o ID NORAD:"));

        for (ObjetoEspacial obj : objetos) {
            if (obj.getIdNorad() == id) {

                String info = obj.gerarRelatorioStatus();

                if (obj instanceof SateliteAtivo sat) {
                    info += "\n\nAutonomia operacional: " + String.format("%.2f", sat.calcularAutonomiaOperacional());
                } else if (obj instanceof EstagioFogueteAbandonado est) {
                    info += "\n\nRisco de reentrada: " + String.format("%.2f", est.calcularRiscoReentrada());
                }

                if (obj instanceof Manobravel m) {
                    info += "\n\n--- Capacidade de Manobra ---";
                    info += "\nCombustível: " + m.combustivelDisponivel() + " kg";
                    info += "\nPode manobrar: " + m.podeManobrar();
                }

                showMessageDialog(null, info);
                return;
            }
        }

        showMessageDialog(null, "Objeto com NORAD " + id + " não encontrado.");
    }

    private void registrarEvento() {

        int id = parseInt(showInputDialog("Digite o ID NORAD do objeto:"));

        for (ObjetoEspacial obj : objetos) {

            if (obj.getIdNorad() == id) {

                String tipo = showInputDialog("Tipo do evento:");
                String descricao = showInputDialog("Descrição:");
                String data = showInputDialog("Data/Hora:");
                int gravidade = parseInt(showInputDialog("Gravidade (1 a 5):"));

                if (gravidade < 1 || gravidade > 5) {
                    showMessageDialog(null, "Gravidade inválida.");
                    return;
                }

                Evento evento = new Evento(tipo, descricao, data, id, gravidade);
                eventos.add(evento);

                showMessageDialog(null, "Evento registrado com sucesso.");
                return;
            }
        }

        showMessageDialog(null, "Objeto não encontrado.");
    }

    private void listarEventos() {
        String lista = "";
        int contador = 1;

        for (Evento ev : eventos) {
            lista += "---- Evento " + contador + " ----\n";
            lista += ev.toString() + "\n\n";
            contador++;
        }

        if (contador == 1) {
            showMessageDialog(null, "Nenhum evento registrado.");
        } else {
            showMessageDialog(null, lista);
        }
    }

    private void calcularIPO() {

        int quantidade = 0;
        double somaRisco = 0;
        int eventosCriticos = 0;
        double riscoMedio = 0;

        for (ObjetoEspacial obj : objetos) {
            somaRisco += obj.calcularRiscoColisao();
            quantidade++;
        }

        for (Evento ev : eventos) {
            if (ev.isCritico()) {
                eventosCriticos++;
            }
        }

        if (quantidade > 0) {
            riscoMedio = somaRisco / quantidade;
        }

        double ipo = CalculadoraIPO.calcularIPO(riscoMedio, quantidade, eventosCriticos);
        String cor = CalculadoraIPO.classificarIPO(ipo);

        showMessageDialog(null,
                "IPO: " + String.format("%.1f", ipo) +
                        "\nClassificação: " + cor +
                        "\nObjetos: " + quantidade +
                        "\nEventos críticos: " + eventosCriticos
        );
    }

    private void relatorioGeral() {
        String relatorio = "";
        int totalObjetos = 0;
        int satelites = 0;
        int estagiosAbandonados = 0;
        int totalEventos = 0;
        int eventosCriticos = 0;
        double riscoMedio = 0;
        double somaRisco = 0;

        for (ObjetoEspacial obj : objetos) {
            somaRisco += obj.calcularRiscoColisao();

            if (obj instanceof SateliteAtivo) {
                satelites++;
            }

            if (obj instanceof EstagioFogueteAbandonado) {
                estagiosAbandonados++;
            }
            totalObjetos++;
        }

        for (Evento ev : eventos) {

            totalEventos++;

            if (ev.isCritico()) {
                eventosCriticos++;
            }
        }



        if (totalObjetos > 0) {
            riscoMedio = somaRisco / totalObjetos;
        }

        double ipo = CalculadoraIPO.calcularIPO(riscoMedio, totalObjetos, eventosCriticos);


        relatorio += "===== RELATÓRIO GERAL =====\n";
        relatorio += "Total de objetos: " + totalObjetos + "\n";
        relatorio += "Satélites ativos: " + satelites + "\n";
        relatorio += "Estágios abandonados: " + estagiosAbandonados + "\n";
        relatorio += "Eventos registrados: " + totalEventos + "\n";
        relatorio += "IPO: " + String.format("%.1f", ipo) + "\n";
        relatorio += "Classificação: " + CalculadoraIPO.classificarIPO(ipo);

        showMessageDialog(null, relatorio);
    }
}