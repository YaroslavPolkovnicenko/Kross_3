package com.company.Threads;

import com.company.Comparators.NameComparator;
import com.company.Events.MyEvent;
import com.company.Events.MyEventProducer;
import com.company.Events.MyListener;
import com.company.Employees.Staffer;
import com.company.Employees.Employee;
import com.company.Employees.EmployeeType;
import com.company.Employees.Worker;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

public class FactoryThread implements Runnable{

    private Logger log = Logger.getLogger(ReadThread.class.getName());
    private ArrayList<Employee> list;
    public static MyEventProducer eventProducer = new MyEventProducer();

    public FactoryThread() {
        list = new ArrayList<>();
    }

    public void printList() {

        System.out.println("Список:");

        for(Employee l : list) {
            System.out.println(l.getFamily() + " " + l.getAge() + " " + l.getExperience() + " " + l.getType());
        }
    }

    public void create(String line) {

        System.out.println("Работает FactoryThread");
        int index = 0;
        Scanner scanner = new Scanner(line);

        scanner.useDelimiter(";");

        Employee employee = null;
        EmployeeType type = null;

        while (scanner.hasNext()) {
            String data = scanner.next();

            switch (index) {

                case 0:

                    if (data.equals("staffer")) {
                        employee = new Staffer();
                        type = EmployeeType.staffer;


                    } else {
                        employee = new Worker();
                        type = EmployeeType.worker;
                    }

                    employee.setType(type);
                    break;

                case 1:

                    employee.setFamily(data);
                    break;

                case 2:

                    try {

                        employee.setAge(Integer.parseInt(data));

                    } catch (NumberFormatException e) {

                        employee.setAge(-1);
                        log.info(e.getMessage());
                    }

                    break;

                case 3:

                    try {
                        employee.setExperience(Double.parseDouble(data));

                    } catch (NumberFormatException e) {

                        employee.setExperience(-1.0);
                        log.info(e.getMessage());
                    }
                    break;

                case 4:

                    switch (type) {

                        case worker:

                            ((Worker) employee).setSalary(Double.parseDouble(data));
                            break;

                        case staffer:

                            ((Staffer) employee).setPrize(Integer.parseInt(data));
                            break;
                    }
                    break;
            }
            index++;
        }
        list.add(employee);
        System.out.println("\n Запись " + line + " добавлена в список. \n");
    }

    public void sortByName() {
        list.sort(new NameComparator());
    }

    @Override
    public void run() {

        eventProducer.addMyListener(new MyListener() {

            @Override
            public void myHappend(MyEvent myEvent) {

                String line = EntryQueue.getFirst();
                create(line);
            }
        });
    }
}