package cn.edu.aust;

import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

/**
 * @author Niu Li
 * @date 2017/1/22
 */
public class ModelMapperTest {
    @Test
    public void testMap() {
        ModelMapper modelMapper = new ModelMapper();
        Order order = new Order();
        Address address = new Address();
        address.setCity("shanghai");
        address.setStreet("pudongxinqu");

        order.setBillingAddress(address);
        Customer customer = new Customer();
        Name name = new Name();
        name.setFirstName("niuli");
        name.setLastName("liniu");
        customer.setName(name);
        order.setCustomer(customer);

        PropertyMap<Order, OrderDTO> orderMap = new PropertyMap<Order, OrderDTO>() {
            protected void configure() {
                map(source.getBillingAddress().getStreet(),destination.billingStreet);
                map(source.billingAddress.getCity(), destination.billingCity);
            }
        };
        modelMapper.addMappings(orderMap);
        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
        System.out.println();
    }
}


// Assume getters and setters on each class
class Order {
    Customer customer;
    Address billingAddress;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }
}

class Customer {
    Name name;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }
}

class Name {
    String firstName;
    String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

class Address {
    String street;
    String city;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

// Assume getters and setters
class OrderDTO {
    String customerFirstName;
    String customerLastName;
    String billingStreet;
    String billingCity;

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getBillingStreet() {
        return billingStreet;
    }

    public void setBillingStreet(String billingStreet) {
        this.billingStreet = billingStreet;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }
}