package problem3;

import java.util.ArrayList;
import java.util.List;

public class PetShop {
    private List<Customer> customers = new ArrayList<>();
    private List<Pet> pets = new ArrayList<>();
    private List<ServiceEstimate> serviceEstimates = new ArrayList<>();

    public PetShop() {
        
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public List<ServiceEstimate> getServiceEstimates() {
        return serviceEstimates;
    }

    public void setServiceEstimates(List<ServiceEstimate> serviceEstimates) {
        this.serviceEstimates = serviceEstimates;
    }

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }

    public void addPet(Pet pet) {
        this.pets.add(pet);
    }

    public void addServiceEstimate(ServiceEstimate serviceEstimate) {
        this.serviceEstimates.add(serviceEstimate);
    }

    public void showAllCustomers() {
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    public void showAllPets() {
        for (Pet pet : pets) {
            System.out.println(pet);
        }
    }

    public void showAllServiceEstimates() {
        for (ServiceEstimate serviceEstimate : serviceEstimates) {
            System.out.println(serviceEstimate);
        }
    }
}
