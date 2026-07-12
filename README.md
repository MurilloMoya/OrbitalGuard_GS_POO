# 🛰️ OrbitalGuard

> Sistema em Java para monitoramento de detritos espaciais, desenvolvido com Programação Orientada a Objetos (POO).

## 📖 Sobre o Projeto

Satélites desativados e estágios de foguete abandonados continuam em órbita por décadas, e cada novo objeto inativo aumenta o risco de colisão para missões futuras.

O **OrbitalGuard** cadastra esses objetos, calcula o risco de colisão e reentrada de cada um, registra eventos ocorridos e consolida tudo em um indicador único de segurança orbital, o IPO.

Desenvolvido como Global Solution da disciplina de Programação Orientada a Objetos, no curso de Sistemas de Informação da FIAP.

---

## 🚀 Funcionalidades

### 🛰️ Cadastro de Objetos Espaciais

Cadastro de satélites desativados ou estágios de foguete abandonados, cada um vinculado a uma organização responsável, com ID NORAD, nome, altitude e velocidade orbital.

### ⚠️ Cálculo de Risco

**🛰️ Satélite Desativado**

| Cálculo | O que mede |
|---|---|
| Risco de colisão | Aumenta com o tempo de inatividade e o peso |
| Vida útil restante | Diminui conforme os anos de inatividade e o peso |
| Combustível disponível | Reduz com o tempo inativo, definindo se ainda pode manobrar |

**🚀 Estágio de Foguete Abandonado**

| Cálculo | O que mede |
|---|---|
| Risco de colisão | Aumenta com a massa e o tamanho |
| Risco de reentrada | Baseado na massa e no tamanho do objeto |
| Combustível disponível | Reduz conforme o tamanho, definindo se ainda pode manobrar |

### 📋 Registro de Eventos

Registro de ocorrências vinculadas a um objeto: tipo, descrição, data, hora e gravidade (1 a 5). Eventos com gravidade 4 ou 5 são marcados automaticamente como críticos.

### 📊 Índice de Proteção Orbital (IPO)

Indicador geral, calculado a partir do risco médio dos objetos cadastrados, da quantidade de objetos e do número de eventos críticos:

| IPO | Situação |
|---|---|
| ≥ 60 | 🟢 Verde |
| 30–59 | 🟡 Amarelo |
| < 30 | 🔴 Vermelho |

### 📄 Relatórios

Relatório individual por objeto, listagem de eventos e um relatório geral com todos os dados do sistema.

---

## 🧠 Conceitos de POO Aplicados

* **Abstração** — `ObjetoEspacial` é uma classe abstrata e define o comportamento comum a todos os objetos monitorados.
* **Herança** — `SateliteDesativado` e `EstagioFogueteAbandonado` estendem `ObjetoEspacial`.
* **Polimorfismo** — os objetos são manipulados por referência da classe base; `instanceof` com pattern matching trata as especializações quando necessário.
* **Interfaces** — `StatusRelatorio` e `Manobravel` padronizam comportamentos entre classes que não têm relação de herança entre si.
* **Encapsulamento** — atributos privados ou protegidos, acessados por getters.

---

## 📂 Estrutura do Projeto

```text
src/OrbitalGuard/
├── Classes/
│   ├── ObjetoEspacial.java
│   ├── SateliteDesativado.java
│   ├── EstagioFogueteAbandonado.java
│   ├── Evento.java
│   ├── Organizacao.java
│   └── CalculadoraIPO.java
├── Interfaces/
│   ├── Manobravel.java
│   └── StatusRelatorio.java
├── Util/
│   └── Util.java
└── Main/
    └── Main.java
```

## 💻 Tecnologias

* Java 17
* Swing (JOptionPane)

---

## 👤 Autor

**Murillo Moya Martins** — Sistemas de Informação, FIAP
