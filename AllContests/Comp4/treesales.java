package AllContests.Comp4;
import java.util.*;

class Employee {
    String name;
    int sales;
    List<Employee> subordinates;

    Employee(String name) {
        this.name = name;
        this.sales = 0;
        this.subordinates = new ArrayList<>();
    }
}

public class treesales {
    private static Map<String, Employee> company;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = Integer.parseInt(scanner.nextLine()); // Number of companies

        for (int i = 1; i <= T; i++) {
            company = new HashMap<>();
            int n = Integer.parseInt(scanner.nextLine()); // Number of operations
            System.out.println("COMPANY " + i);

            for (int j = 0; j < n; j++) {
                String[] parts = scanner.nextLine().split(" ");
                String command = parts[0];

                if (command.equals("ADD")) {
                    String sponsor = parts[1];
                    String newEmployee = parts[2];
                    addEmployee(sponsor, newEmployee);
                } else if (command.equals("SALE")) {
                    String employee = parts[1];
                    int saleAmount = Integer.parseInt(parts[2]);
                    addSale(employee, saleAmount);
                } else if (command.equals("QUERY")) {
                    String employee = parts[1];
                    System.out.println(getTotalSales(employee));
                }
            }
        }
        scanner.close();
    }

    private static void addEmployee(String sponsor, String newEmployee) {
        if (!company.containsKey(sponsor)) {
            company.put(sponsor, new Employee(sponsor));
        }
        Employee sponsorNode = company.get(sponsor);
        Employee newEmpNode = new Employee(newEmployee);
        sponsorNode.subordinates.add(newEmpNode);
        company.put(newEmployee, newEmpNode);
    }

    private static void addSale(String employee, int amount) {
        if (company.containsKey(employee)) {
            company.get(employee).sales += amount;
        }
    }

    private static int getTotalSales(String employee) {
        if (!company.containsKey(employee)) return 0;
        return getSubtreeSales(company.get(employee));
    }

    private static int getSubtreeSales(Employee employee) {
        int total = employee.sales;
        for (Employee subordinate : employee.subordinates) {
            total += getSubtreeSales(subordinate);
        }
        return total;
    }
}
