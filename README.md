# Projeto Java – Gerenciamento de Funcionários

## Estrutura
```
projeto-java/
└── src/
    ├── Pessoa.java
    ├── Funcionario.java
    └── Principal.java
```

## Como compilar e executar

### Pré-requisito
Java 8 ou superior instalado. Verifique com:
```bash
java -version
```

### Compilar
```bash
cd projeto-java/src
javac Pessoa.java Funcionario.java Principal.java
```

### Executar
```bash
java Principal
```

## O que o programa faz
| Item | Ação |
|------|------|
| 3.1  | Insere 10 funcionários na lista |
| 3.2  | Remove o funcionário "João" |
| 3.3  | Imprime todos com data no formato dd/MM/yyyy e salário formatado (ponto milhar, vírgula decimal) |
| 3.4  | Aplica 10% de aumento nos salários |
| 3.5  | Agrupa funcionários por função em um Map |
| 3.6  | Imprime funcionários agrupados por função |
| 3.8  | Imprime aniversariantes de outubro (10) e dezembro (12) |
| 3.9  | Imprime o funcionário mais velho (nome + idade) |
| 3.10 | Imprime funcionários em ordem alfabética |
| 3.11 | Imprime a soma total dos salários |
| 3.12 | Imprime quantos salários mínimos (R$1212,00) cada um recebe |
