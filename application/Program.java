package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;



public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter department's name: ");
		String departmentName = sc.nextLine();
		System.out.println("Enter worker data:");
		System.out.print("Name: ");
		String workerName = sc.nextLine();
		System.out.print("Level: ");
		String workerLevel = sc.nextLine();
		System.out.print("Base salary: ");
		double baseSalary = sc.nextDouble();
		//instanciando o trabalhador -> todos estes parâmetros estão no construtor da class "Worker"
		Worker worker = new Worker(workerName, WorkerLevel.valueOf(workerLevel), baseSalary, new Department(departmentName)); /*primeiro parâmetro será o nome do trabalhador.
		O segundo valor vai ser o tipo enumerado com o argumento passado na forma de String(irá ser convertido com "valueOf()" -> com isso, o tipo enum foi instanciado.
		O baseSalary é informado pela própria variável declarada nessa classe, assim como o nome do trabalhador(workerName).
		O último valor será o departamento. Terei que instanciar a classe "Departament" pois o trabalhador está associado com essa classe, então para que o valor do departamento seja passado para a sua própria classe, devo instancia-la e passar como argumento o valor da variável declarada e lida aqui.*/
		
		System.out.print("How many contracts to this worker? ");
		int n = sc.nextInt();
		
		for(int i=1;i <= n; i++) {
			System.out.printf("Enter contract #%d data:\n", i);
			System.out.print("Date (DD/MM/YYYY): ");
			String data1 = sc.next();
			LocalDate contractDate = LocalDate.parse(data1, DateTimeFormatter.ofPattern("dd/MM/yyyy")); /*Vai pegar a string lida na variável 'data1' e vai formatá-la no padrão definido pelos métodos "DateTimeFormatter.ofPattern()". Depois disso irá converter toda essa operação para o 'LocalDate' utilizando o método "parse", que serve para analisar ou converter e a guardará na variável do tipo LocalDate*/
			System.out.print("Value per hour: ");
			double valuePerHour = sc.nextDouble();
			System.out.print("Duration (hours): ");
			int hours = sc.nextInt();
			
			HourContract contract = new HourContract(contractDate, valuePerHour, hours);
			worker.addContract(contract); //faz o contrato ser associado com o trabalhador
		}
		
		System.out.println();
		System.out.print("Enter month and year to calculate income (MM/YYYY): ");
		String monthAndYear = sc.next();
		int month = Integer.parseInt(monthAndYear.substring(0, 2)); /*Essa linha de código vai pegar a string digitada logo acima e vai recortá-la com o método "substring". Os valores passados no método será a posição que começara (no caso zero) e terminará (no caso 2) o recorte da String. (é 2 pois no final o recorte tem que ser de um índice a mais, então vai pegar somente o mês digitado)
		Após isso, converterá a string através do método "parseInt"(que é da classe Integer) para um inteiro */
		int year = Integer.parseInt(monthAndYear.substring(3)); // aqui começará a recortar a partir do íncide 3 da string
		
		System.out.println("Name: " + worker.getName());
		System.out.println("Department: " + worker.getDepartment().getName());
		System.out.println("Income for " + monthAndYear + ": " + String.format("%.2f", worker.income(year, month)));

		sc.close();
	}

}
