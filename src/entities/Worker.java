package entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import entities.enums.WorkerLevel;

public class Worker {
	
	private String name;
	private WorkerLevel level;
	private Double baseSalary;
	
	private Department department;
	private List<HourContract> contracts = new ArrayList<>();
	
	public Worker() {
		
	}

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
	
	public void addContract(HourContract contract) {
		contracts.add(contract);
	}
	
	public void removeContract(HourContract contract) {
		contracts.remove(contract);
	}
	
	// income: calcular o ganho do trabalhador a partir do salário base somado a cada
	// contratohorista do mesmo ano e mês
	public double income(int year, int month) {
		double sum = baseSalary;
		Calendar cal = Calendar.getInstance(); // Criado Calendar para obter ano e mês
		for (HourContract c : contracts) {
			cal.setTime(c.getDate()); 		   // Puxa a data do contrato e a define como sendo data do calendário
			int c_year = cal.get(Calendar.YEAR);
			int c_month = cal.get(Calendar.MONTH) + 1;
			
			// Conferir, agora, se o mês e ano do contrato são iguais aos dos parâmetros
			if(year == c_year && month == c_month) {
				sum += c.totalValue();
			}
		}
		return sum;
	}

}
