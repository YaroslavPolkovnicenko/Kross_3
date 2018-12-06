package com.company.Comparators;

import com.company.Employees.Employee;

import java.util.Comparator;

public class NameComparator implements Comparator<Employee> {

    @Override
    public int compare(Employee l1, Employee l2) {
        return l1.getFamily().compareTo(l2.getFamily());
    }
}