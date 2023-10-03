package entities;

import java.util.ArrayList;
import java.util.List;

import entities.enums.WorkerLevel;

public class Worker {
	
	private String name;
	private WorkerLevel level; //declaração da variável tipo Enum
	private Double baseSalary;
	
	
	//composição de objetos:
	/*Como no diagrama UML a classe Worker essa associada com pelo menos um departamento, se instancia o objeto uma vez: */
	private Department department;
	/*Como no diagrama UML a classe Worker essa associada com vários contratos, se cria uma lista intanciado ela com os horas do contrato(HourContract).A lista é criada pois serão mais de um objetos que serão usados na classe Worker(trabalhador): */
	private List<HourContract> contracts = new ArrayList<>(); //lista do tipo HourContract
	
	public Worker() {
	}
	/*criei um construtor sem os atributos do tipo lista. Quando eu tiver uma composição "tem muitos", que no caso é uma lista, eu nao incluo a lista no construtor, eu simplesmente instancio a lista vazia*/
	public Worker(String name, WorkerLevel level, Double baseSalary, Department department) {
		this.name = name;
		this.level = level;
		this.baseSalary = baseSalary;
		this.department = department;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public WorkerLevel getLevel() {
		return level;
	}
	public void setLevel(WorkerLevel level) {
		this.level = level;
	}
	public Double getBaseSalary() {
		return baseSalary;
	}
	public void setBaseSalary(Double baseSalary) {
		this.baseSalary = baseSalary;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public List<HourContract> getContracts() {
		return contracts;
	}
	/*Esse método abaixo foi comentado pois eu só posso adicionar ou remover contratos através dos métodos "addContract" e "removeContract". Isso porque no inicio do programa eu inicializo a lista vazia e depois adiciono ou removo contratos atráves dos métodos citados. O método set abaixo, ele pega uma outra lista e atribui ela a variável da lista, criando assim uma nova lista*/
	/*public void setContracts(List<HourContract> contracts) {
		this.contracts = contracts;
	/*}

	/*método para adicionar um contrato ao trabalhador. Pega da lista um contrato, joga no parâmetro e adiciona um contrato ao trabalhador */
	public void addContract(HourContract contract) {
		this.contracts.add(contract);
	}
	
	/*método para remover um contrato assopciado a lista de contratos do trabalhador*/
	public void removeContract(HourContract contract) {
		this.contracts.remove(contract);
	}
	//método para saber a renda total do trabalhador baseado na soma dos contratos e do salário base
	public double income(int year, int month) {
		double sum = baseSalary;
		for(HourContract c : contracts) {//para cada Contrato "c" da lista de contratos faça:
			/*Variáveis criada para comparação; se o ano e mês inseridos nos parâmetros for igual ao valor das variáveis abaixo(que pegam a data contida na lista e armazenam na variável), entrará na condição*/
			int c_year = c.getDate().getYear();
			int c_month = c.getDate().getMonthValue();
			
			if(year == c_year && month == c_month) {
				sum += c.totalValue();
			}
		}
		return sum;
	}
}
