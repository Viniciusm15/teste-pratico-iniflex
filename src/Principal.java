import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal {

    static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static NumberFormat NUMBER_FORMATTER = NumberFormat.getInstance(new Locale("pt", "BR"));

    public static void main(String[] args) {

        // #region 3.1 Inserir funcionários
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
        // #endregion

        // #region 3.2 Remover João
        funcionarios.removeIf(f -> f.getNome().equals("João"));
        // #endregion

        // #region 3.3 Imprimir todos os funcionários
        imprimirSecao("LISTA DE FUNCIONÁRIOS");
        imprimirFuncionarios(funcionarios);
        // #endregion

        // #region 3.4 Aumento de 10%
        funcionarios.forEach(f -> f.setSalario(f.getSalario().multiply(new BigDecimal("1.10"))));
        imprimirSecao("APÓS AUMENTO DE 10%");
        imprimirFuncionarios(funcionarios);
        // #endregion

        // #region 3.5 e 3.6 Agrupar e imprimir funcionários por função
        var agrupador = Collectors.groupingBy(Funcionario::getFuncao);
        Map<String, List<Funcionario>> porFuncao = funcionarios.stream().collect(agrupador);
        imprimirSecao("FUNCIONÁRIOS AGRUPADOS POR FUNÇÃO");
        porFuncao.forEach((funcao, lista) -> {
            System.out.println("\n  Função: " + funcao);
            System.out.println("  " + "-".repeat(90));
            lista.forEach(f -> System.out.println("    " + formatarFuncionario(f)));
        });
        // #endregion

        // #region 3.8 Aniversariantes em outubro e dezembro
        imprimirSecao("ANIVERSARIANTES EM OUTUBRO E DEZEMBRO");
        funcionarios.stream()
                .filter(f -> {
                    int mes = f.getDataNascimento().getMonthValue();
                    return mes == 10 || mes == 12;
                })
                .forEach(f -> System.out.println("  " + formatarFuncionario(f)));
        // #endregion

        // #region 3.9 Funcionário com maior idade
        imprimirSecao("FUNCIONÁRIO COM MAIOR IDADE");
        Funcionario maisVelho = Collections.min(funcionarios, Comparator.comparing(Pessoa::getDataNascimento));
        int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();
        System.out.printf("  Nome: %-15s Idade: %d anos", maisVelho.getNome(), idade);
        // #endregion

        // #region 3.10 Ordem alfabética
        imprimirSecao("FUNCIONÁRIOS EM ORDEM ALFABÉTICA");
        funcionarios.stream()
                .sorted(Comparator.comparing(Pessoa::getNome))
                .forEach(f -> System.out.println("  " + formatarFuncionario(f)));
        // #endregion

        // #region 3.11 Total dos salários
        imprimirSecao("TOTAL DOS SALÁRIOS");
        BigDecimal totalSalarios = BigDecimal.ZERO;
        for (Funcionario f : funcionarios) {
            totalSalarios = totalSalarios.add(f.getSalario());
        }
        System.out.println("  Total: R$ " + NUMBER_FORMATTER.format(totalSalarios));
        // #endregion

        // #region 3.12 Salários mínimos por funcionário
        imprimirSecao("SALÁRIOS MÍNIMOS POR FUNCIONÁRIO");
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        funcionarios.forEach(f -> {
            BigDecimal quantosSalarios = f.getSalario().divide(salarioMinimo, 2, RoundingMode.FLOOR);
            System.out.printf("  %s - %s salários mínimos%n",
                    f.getNome(), NUMBER_FORMATTER.format(quantosSalarios));
        });
        System.out.println("\n" + "=".repeat(100));
        // #endregion
    }

    static void imprimirFuncionarios(List<Funcionario> lista) {
        lista.forEach(f -> System.out.println("  " + formatarFuncionario(f)));
    }

    static String formatarFuncionario(Funcionario f) {
        return String.format("Nome: %-8s | Nascimento: %s | Salário: R$ %s | Função: %s",
                f.getNome(),
                f.getDataNascimento().format(DATE_FORMATTER),
                NUMBER_FORMATTER.format(f.getSalario()),
                f.getFuncao());
    }

    static void imprimirSecao(String titulo) {
        System.out.println("\n" + "=".repeat(100));
        System.out.println(titulo);
        System.out.println("=".repeat(100));
    }
}