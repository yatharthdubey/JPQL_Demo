import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Employee.Employee;

public class BasicQuery {

	public static void main(String[] args) {


		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Employee_Details");
		EntityManager em = emf.createEntityManager();
		Query query1 = em.createQuery("select MAX(e.salary) from Employee e");
		Integer max_sal = (Integer) query1.getSingleResult();
		System.out.println("Max Salary is "+max_sal);
		
		Query query2 = em.createQuery("select e from Employee e");
		List<Employee> list1 = query2.getResultList();
		System.out.println("\n\nAll Employees\n\n");
		for(Employee e:list1) {
			System.out.println(e);
		}
		
		Query query3 = em.createQuery("select e from Employee e where e.salary BETWEEN 40000 and 60000");
		List<Employee> list2 = query3.getResultList();
		System.out.println("\n\nEmployee with salary between 40000 abd 60000\n\n");
		for(Employee e:list2) {
			System.out.println(e);
		}
		
		Query query4 = em.createQuery("select e from Employee e where salary in(select MAX(e1.salary) from Employee e1 group by e1.dept)");
		List<Employee> list3 = query4.getResultList();
		System.out.println("\n\nMax Salary According to Deptartment\n\n");
		for(Employee e:list3) {
			System.out.println(e.getId()+" "+e.getName());
		}
		em.close();
		emf.close();
	}

}
