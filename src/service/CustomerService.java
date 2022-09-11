package service;

import model.Customer;

import java.util.*;

public class CustomerService {
    private static final CustomerService INSTANCE = new CustomerService();
    private final Map<String, Customer> mapOfCustomers = new HashMap<String, Customer>();

    private CustomerService() {}
    public static CustomerService getInstance() {
        return INSTANCE;
    }

    public void addCustomer(String firstName, String lastName, String email) {

        Customer customer = new Customer(firstName, lastName, email);
        mapOfCustomers.put(email, customer);

    }

    public Customer getCustomer(String customerEmail) {
        return mapOfCustomers.get(customerEmail);
    }

    public Collection<Customer> getAllCustomers() {
        return  mapOfCustomers.values();
    }


}
