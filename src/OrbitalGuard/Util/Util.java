package OrbitalGuard.Util;

import OrbitalGuard.Classes.*;
import OrbitalGuard.Interfaces.Manobravel;

import static javax.swing.JOptionPane.*;
import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;
import java.time.LocalTime;
import java.util.ArrayList;

public class Util {

    private ArrayList<ObjetoEspacial> objetos = new ArrayList<>();
    private ArrayList<Evento> eventos = new ArrayList<>();

    public void menu() {
        int opcao = 0;
        String menu;
        do {
            menu  = "OrbitalGuard\n";
            menu += "[1] Cadastrar objeto\n";
            menu += "[2] Listar objetos\n";
            menu += "[3] Calcular por entidade\n";
            menu += "[4] Registrar evento\n";
            menu += "[5] Listar eventos\n";
            menu += "[6] Calcular IPO\n";
            menu += "[7] Relatório geral\n";
            menu += "[8] Sair\n";

            try {
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
            } catch (NumberFormatException e) {
                showMessageDialog(null, "A opção deve ser um número\n" + e);
            }

        } while (opcao != 8);
    }

    private void cadastrarObjeto() {
        String nome, nomeOrg, pais;
        int tipo, idNorad, anosInativo;
        double altitude, velocidade, massa, pesoKg, tamanhoM2;

        tipo = parseInt(showInputDialog("Escolha o tipo do objeto:\n1 - Satélite Desativado\n2 - Estágio de Foguete Abandonado"));

        while (tipo != 1 && tipo != 2) {
            tipo = parseInt(showInputDialog("Opção inválida!\nDigite 1 ou 2:"));
        }

        idNorad = parseInt(showInputDialog("ID NORAD (número):"));
        nome = showInputDialog("Nome do objeto:");
        altitude = parseDouble(showInputDialog("Altitude (km):"));
        velocidade = parseDouble(showInputDialog("Velocidade (km/s):"));
        nomeOrg = showInputDialog("Nome da organização:");
        pais = showInputDialog("País:");

        Organizacao org = new Organizacao(nomeOrg, pais);

        if (tipo == 1) {
            anosInativo = parseInt(showInputDialog("Anos inativo:"));
            pesoKg = parseDouble(showInputDialog("Peso do satélite (kg):"));

            SateliteDesativado satelite = new SateliteDesativado(idNorad, nome, altitude, velocidade, org, anosInativo, pesoKg);
            objetos.add(satelite);

            showMessageDialog(null, "Satélite desativado cadastrado com sucesso!");
        } else {
            massa = parseDouble(showInputDialog("Massa (kg):"));
            tamanhoM2 = parseDouble(showInputDialog("Tamanho (m²):"));

            EstagioFogueteAbandonado estagio = new EstagioFogueteAbandonado(idNorad, nome, altitude, velocidade, org, massa, tamanhoM2);
            objetos.add(estagio);

            showMessageDialog(null, "Estágio de foguete cadastrado com sucesso!");
        }
    }

    private void listarObjetos() {
        String lista = "";
        int num = 1;
        double riscoExibicao;

        for (ObjetoEspacial obj : objetos) {
            riscoExibicao = obj.calcularRiscoColisao();
            lista += "---- Objeto " + num + " ----\n";
            lista += "Tipo: " + obj.getTipoObjeto() + "\n";
            lista += "Nome: " + obj.getNomeObjeto() + "\n";
            lista += "ID NORAD: " + obj.getIdNorad() + "\n";
            lista += "Altitude: " + obj.getAltitudeKm() + " km\n";
            lista += "Risco colisão: " + riscoExibicao + "%\n\n";
            num++;
        }

        if (num == 1) {
            showMessageDialog(null, "Nenhum objeto cadastrado.");
        } else {
            showMessageDialog(null, lista);
        }
    }

    private void calcularPorEntidade() {
        String info;
        int id = parseInt(showInputDialog("Digite o ID NORAD:"));

        for (ObjetoEspacial obj : objetos) {
            if (obj.getIdNorad() == id) {

                info = obj.gerarRelatorioStatus();

                if (obj instanceof SateliteDesativado sat) {
                    info += "\n\n📊 ANÁLISE DO SATÉLITE\n";
                    info += "━━━━━━━━━━━━━━━━━━━━\n";
                    info += "Vida útil estimada restante: " + sat.calcularVidaUtil() + "%\n";
                    info += "━━━━━━━━━━━━━━━━━━━━";
                } else if (obj instanceof EstagioFogueteAbandonado est) {
                    info += "\n\n📊 ANÁLISE DO FOGUETE\n";
                    info += "━━━━━━━━━━━━━━━━━━━━\n";
                    info += "Risco de reentrada: " + est.calcularRiscoReentrada() + "%\n";
                    info += "━━━━━━━━━━━━━━━━━━━━";
                }

                if (obj instanceof Manobravel m) {
                    info += "\n\n🚀 CAPACIDADE DE MANOBRA\n";
                    info += "━━━━━━━━━━━━━━━━━━━━\n";
                    info += "Combustível: " + m.combustivelDisponivel() + " kg\n";
                    info += "Status: " + m.podeManobrar();
                }

                showMessageDialog(null, info);
                return;
            }
        }

        showMessageDialog(null, "Objeto com NORAD " + id + " não encontrado.");
    }

    private void registrarEvento() {
        String tipo, descricao, data, hora="";
        int id, gravidade;
        boolean horaValida = false;

        id = parseInt(showInputDialog("Digite o ID NORAD do objeto:"));

        for (ObjetoEspacial obj : objetos) {

            if (obj.getIdNorad() == id) {

                tipo = showInputDialog("Tipo do evento:");
                descricao = showInputDialog("Descrição:");
                data = showInputDialog("Data (ex: 15/01/2026):");

                while (!horaValida) {
                    hora = showInputDialog("Hora (ex: 10:30):");
                    try {
                        LocalTime.parse(hora);
                        horaValida = true;
                    } catch (Exception e) {
                        showMessageDialog(null, "Hora inválida! Use o formato 10:30");
                    }
                }

                gravidade = parseInt(showInputDialog("Gravidade (1 a 5):"));

                if (gravidade < 1 || gravidade > 5) {
                    showMessageDialog(null, "Gravidade inválida.");
                    return;
                }

                Evento evento = new Evento(data, descricao, gravidade, hora, id, tipo);
                eventos.add(evento);

                showMessageDialog(null, "Evento registrado com sucesso.");
                return;
            }
        }

        showMessageDialog(null, "Objeto não encontrado.");
    }

    private void listarEventos() {
        String lista = "";
        int cont = 1;

        for (Evento ev : eventos) {
            lista += "---- Evento " + cont + " ----\n";
            lista += ev.gerarRelatorioStatus() + "\n\n";
            cont++;
        }

        if (cont == 1) {
            showMessageDialog(null, "Nenhum evento registrado.");
        } else {
            showMessageDialog(null, lista);
        }
    }

    private void calcularIPO() {
        int quantidade = 0;
        double somaRisco = 0, riscoMedio = 0, ipo;
        int eventosCriticos = 0;
        String cor;

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

        ipo = CalculadoraIPO.calcularIPO(riscoMedio, quantidade, eventosCriticos);
        cor = CalculadoraIPO.classificarIPO(ipo);

        showMessageDialog(null,
                "IPO: " + ipo +
                        "\nClassificação: " + cor +
                        "\nObjetos: " + quantidade +
                        "\nEventos críticos: " + eventosCriticos
        );
    }

    private void relatorioGeral() {
        String relatorio = "";
        int totalObjetos = 0, satelites = 0, estagiosAbandonados = 0, totalEventos = 0, eventosCriticos = 0;
        double riscoMedio = 0, somaRisco = 0,ipo;

        for (ObjetoEspacial obj : objetos) {
            somaRisco += obj.calcularRiscoColisao();

            if (obj instanceof SateliteDesativado) {
                satelites++;
            } else if (obj instanceof EstagioFogueteAbandonado) {
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

        ipo = CalculadoraIPO.calcularIPO(riscoMedio, totalObjetos, eventosCriticos);

        relatorio += "===== RELATÓRIO GERAL =====\n";
        relatorio += "Total de objetos: " + totalObjetos + "\n";
        relatorio += "Satélites desativados: " + satelites + "\n";
        relatorio += "Estágios abandonados: " + estagiosAbandonados + "\n";
        relatorio += "Eventos registrados: " + totalEventos + "\n";
        relatorio += "Eventos críticos: " + eventosCriticos + "\n";
        relatorio += "IPO: " + ipo + "\n";
        relatorio += "Classificação: " + CalculadoraIPO.classificarIPO(ipo);

        showMessageDialog(null, relatorio);
    }
}