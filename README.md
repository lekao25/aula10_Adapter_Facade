# SIGA — Atividade de padrões estruturais: Adapter e Facade

**Técnicas de Programação II (TP2) · Aula 10** — CST em Desenvolvimento de Software Multiplataforma · Fatec de Porto Ferreira

Solução da atividade da Aula 10: três deslizes estruturais corrigidos com Adapter e Facade, sem mudar o comportamento externo do sistema.

## Estrutura do projeto

```
siga-estruturais/
└── src/
    └── siga/
        ├── Aluno.java                     (entidade de domínio; pronta)
        ├── Matricula.java                 (entidade de domínio; pronta)
        ├── AlunoDAO.java                  (interface da Aula 7; pronta)
        ├── AlunoDAOMemoria.java           (implementação da Aula 8; pronta)
        ├── MatriculaDAO.java              (interface; pronta)
        ├── MatriculaDAOMemoria.java       (implementação; pronta)
        ├── SecretariaLegadoWS.java        (COMPONENTE EXTERNO — não alterado)
        ├── ValidadorCpf.java              (subsistema de matrícula; pronta)
        ├── ControleVagas.java             (subsistema de matrícula; pronta)
        ├── CalculadoraDesconto.java       (subsistema de matrícula; pronta)
        ├── NotificadorEmail.java          (subsistema de matrícula; pronta)
        ├── FonteDeAlunos.java             (NOVA — abstração da fonte de alunos)
        ├── AdaptadorSecretariaLegado.java (NOVA — Adapter do serviço legado)
        ├── ImportadorAlunos.java          (refatorado)
        ├── RelatorioSituacao.java         (refatorado)
        ├── FachadaMatricula.java          (NOVA — Facade do subsistema de matrícula)
        ├── TelaMatricula.java             (refatorada — um único colaborador)
        └── Main.java                      (atualizado)
```

## Como compilar e executar

Pré-requisito: JDK 17 ou superior.

```bash
javac -encoding UTF-8 -d bin src/siga/*.java
java -cp bin siga.Main
```

## Etapa 1 — Onde Estava a Duplicação e Por Que Ela Divergia

A conversão do formato legado existia em dois lugares:

1. **`ImportadorAlunos.importar()`** usava `"A".equals(linha[2])`, que diferencia maiúsculas de minúsculas. A situação da aluna `2024003` vem como `"a"`, então ela era gravada como **inativa**.

2. **`RelatorioSituacao.imprimir()`** usava `"A".equalsIgnoreCase(linha[2])`, que ignora maiúsculas/minúsculas. O mesmo dado era exibido como **ativo**.

A mesma regra foi escrita duas vezes; só uma versão recebeu a correção da letra minúscula.

## Etapa 2 — Adapter

A interface `FonteDeAlunos` (método `List<Aluno> listar()`) passou a representar a fonte de alunos. `AdaptadorSecretariaLegado` a implementa por composição, recebendo `SecretariaLegadoWS` no construtor e convertendo a matriz de texto em `List<Aluno>`. A conversão — incluindo a regra da letra minúscula (`equalsIgnoreCase`) — agora existe em um único lugar.

## Etapa 3 — Clientes Dependem Da Abstração

`ImportadorAlunos` recebe `FonteDeAlunos` em vez de `SecretariaLegadoWS`; `RelatorioSituacao` consome a mesma fonte. A classe `SecretariaLegadoWS` passou a aparecer apenas dentro do Adapter.

## Etapa 4 — Facade

`FachadaMatricula` concentra a orquestração no método `matricular(matricula, cpf, codigoTurma)`: validar CPF, buscar aluno, verificar vaga, calcular desconto, gravar, reservar vaga e notificar. `TelaMatricula` passou a receber um único colaborador e contém apenas a chamada e o tratamento de erro.

## Etapa 5 — Justificativa Dos Padrões

**Adapter.** O serviço legado devolve uma matriz de texto que não corresponde ao formato do domínio (`List<Aluno>` com `ativo` como `boolean`) e não pode ser alterado. O Adapter concentrou a tradução do formato externo, evitando espalhar esse conhecimento e eliminando a divergência.

**Facade.** A tela conhecia as seis classes do subsistema e a ordem correta de chamá-las, que é regra de negócio. A fachada passou a encapsular essa orquestração, deixando a tela com uma única dependência.

## Critério De Sucesso

- `2024003` aparece **ativa** no importador e no relatório.
- `ImportadorAlunos` e `RelatorioSituacao` não mencionam `SecretariaLegadoWS`.
- `TelaMatricula` tem um único colaborador.
- `SecretariaLegadoWS` permanece intacta.
- As matrículas e os erros continuam os mesmos de antes.

## Padrão De Entrega

Identificadores em português, um arquivo `.java` por classe pública, código formatado, repositório Git com README e commits descritivos.
