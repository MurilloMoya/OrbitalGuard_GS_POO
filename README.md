# 🛰️ OrbitalGuard

> Sistema de monitoramento de detritos espaciais desenvolvido em Java com Programação Orientada a Objetos (POO).

## 📖 Sobre o Projeto

Segundo agências espaciais internacionais, o aumento de satélites desativados e estágios de foguetes abandonados em órbita é um dos maiores riscos para futuras missões e operações orbitais.

O **OrbitalGuard** simula uma ferramenta de resposta a esse cenário: permite cadastrar esses objetos, calcular riscos de colisão e reentrada, registrar eventos críticos e gerar um indicador geral de segurança orbital, o IPO — contribuindo para uma gestão orbital mais segura.

Desenvolvido como Global Solution da disciplina de Programação Orientada a Objetos — FIAP, Sistemas de Informação.

---

## 🚀 Funcionalidades

### 🛰️ Cadastro de Objetos Espaciais

Cadastro de **Satélites Desativados** ou **Estágios de Foguete Abandonados**, cada um vinculado a uma organização responsável, com ID NORAD, nome, altitude e velocidade orbital.

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

Registro de ocorrências vinculadas a um objeto (tipo, descrição, data, hora e gravidade de 1 a 5), com identificação automática de eventos críticos (gravidade ≥ 4).

### 📊 Índice de Proteção Orbital (IPO)

Indicador geral calculado a partir do risco médio dos objetos cadastrados, da quantidade de objetos e do número de eventos críticos:

| IPO | Situação |
|---|---|
| ≥ 60 | 🟢 Verde |
| 30–59 | 🟡 Amarelo |
| < 30 | 🔴 Vermelho |

### 📄 Relatórios

Relatório individual por objeto, listagem de eventos e relatório geral consolidando todos os dados do sistema.

---

## 🧠 Conceitos de POO Aplicados

* **Abstração** — `ObjetoEspacial` (classe abstrata) define o comportamento comum a todos os objetos monitorados.
* **Herança** — `SateliteDesativado` e `EstagioFogueteAbandonado` estendem `ObjetoEspacial`.
* **Polimorfismo** — objetos são manipulados por referência da classe base; `instanceof` com pattern matching trata as especializações quando necessário.
* **Interfaces** — `StatusRelatorio` (geração de relatório) e `Manobravel` (capacidade de manobra) padronizam comportamentos entre classes não relacionadas por herança.
* **Encapsulamento** — atributos privados/protegidos com acesso via getters.

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

**Murillo Moya** — Sistemas de Informação, FIAP
