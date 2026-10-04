# SIGA — Atividade de padrões estruturais: Adapter e Facade

**Técnicas de Programação II (TP2) · Aula 10** — CST em Desenvolvimento de Software Multiplataforma · Fatec de Porto Ferreira

Código inicial da atividade prática da Aula 10. Ele compila e executa, mas contém três deslizes de estrutura, corrigidos ao longo das etapas com os padrões Adapter e Facade.

## Estrutura do projeto

```
siga-estruturais/
└── src/
    └── siga/
        ├── Aluno.java                 (entidade de domínio; pronta)
        ├── Matricula.java             (entidade de domínio; pronta)
        ├── AlunoDAO.java              (interface da Aula 7; pronta)
        ├── AlunoDAOMemoria.java       (implementação da Aula 8; pronta)
        ├── MatriculaDAO.java          (interface; pronta)
        ├── MatriculaDAOMemoria.java   (implementação; pronta)
        ├── SecretariaLegadoWS.java    (COMPONENTE EXTERNO — não alterar)
        ├── ValidadorCpf.java          (subsistema de matrícula; pronta)
        ├── ControleVagas.java         (subsistema de matrícula; pronta)
        ├── CalculadoraDesconto.java   (subsistema de matrícula; pronta)
        ├── NotificadorEmail.java      (subsistema de matrícula; pronta)
        ├── ImportadorAlunos.java      (deslizes 1A e 2)
        ├── RelatorioSituacao.java     (deslize 1B)
        ├── TelaMatricula.java         (deslize 3)
        └── Main.java                  (demonstra os três deslizes)
```

## Como compilar e executar

Pré-requisito: JDK 17 ou superior.

```bash
javac -encoding UTF-8 -d bin src/siga/*.java
java -cp bin siga.Main
```

## Os três deslizes propositais

| # | Deslize | Onde | Por que é um problema |
|---|---|---|---|
| 1 | Conversão duplicada e divergente | `ImportadorAlunos` e `RelatorioSituacao` | O formato legado foi convertido duas vezes. Uma versão trata a situação em minúscula e a outra não; a aluna `2024003` aparece inativa no importador e ativa no relatório. |
| 2 | Dependência de classe concreta | `ImportadorAlunos` | O importador recebe `SecretariaLegadoWS` no construtor. Trocar a origem exige mexer nele e viola o DIP. |
| 3 | Subsistema exposto à apresentação | `TelaMatricula` | A tela recebe seis colaboradores e conhece a ordem correta de chamá-los, que é regra de negócio. |

## Etapa 1 — Onde estava a duplicação e por que ela divergia

A conversão do formato legado existia em dois lugares:

1. **`ImportadorAlunos.importar()`** usava `"A".equals(linha[2])`, que diferencia maiúsculas de minúsculas. A situação da aluna `2024003` vem como `"a"`, então ela era gravada como **inativa**.

2. **`RelatorioSituacao.imprimir()`** usava `"A".equalsIgnoreCase(linha[2])`, que ignora maiúsculas/minúsculas. O mesmo dado era exibido como **ativo**.

A mesma regra foi escrita duas vezes; só uma versão recebeu a correção da letra minúscula.

## Sua tarefa

1. Localizar a duplicação da conversão e descrever onde ela diverge.
2. Criar a interface `FonteDeAlunos` e o Adapter `AdaptadorSecretariaLegado`, centralizando a conversão.
3. Refatorar `ImportadorAlunos` e `RelatorioSituacao` para dependerem de `FonteDeAlunos`.
4. Criar a `FachadaMatricula` e deixar a `TelaMatricula` com um único colaborador.
5. Justificar cada padrão, descrevendo o problema antes de nomeá-lo.

## Critério de sucesso

- `2024003` aparece **ativa** no importador e no relatório.
- `ImportadorAlunos` e `RelatorioSituacao` não mencionam `SecretariaLegadoWS`.
- `TelaMatricula` tem um único colaborador.
- `SecretariaLegadoWS` permanece intacta.
- As matrículas e os erros continuam os mesmos de antes.

## Padrão de entrega

Identificadores em português, um arquivo `.java` por classe pública, código formatado, repositório Git com README e commits descritivos.
