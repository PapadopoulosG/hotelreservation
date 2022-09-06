package service;

import model.Customer;

import java.util.*;

public class CustomerService {
    public static CustomerService INSTANCE = new CustomerService();
    private final CustomerService customerService = CustomerService.getInstance();
    // List<Customer> listOfCustomers = new ArrayList<Customer>();
    Map<String, Customer> mapOfCustomers = new HashMap<String, Customer>();


    public static CustomerService getInstance() {
        return INSTANCE;
    }

    public void addCustomer(String firstName, String lastName, String email) {

        Customer customer = new Customer(firstName, lastName, email);
        // listOfCustomers.add(customer);
        mapOfCustomers.put(email, customer);

    }

    public Customer getCustomer(String customerEmail) {
        //edo na ginetai anazitisi gia to email kai return customer otan to bro
        return mapOfCustomers.get(customerEmail);
    }

    public Collection<Customer> getAllCustomers() {

        return  mapOfCustomers.values();
    }


}
