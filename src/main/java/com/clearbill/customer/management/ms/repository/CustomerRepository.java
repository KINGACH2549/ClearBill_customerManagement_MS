package com.clearbill.customer.management.ms.repository;

import com.clearbill.customer.management.ms.entity.Customer;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CouchbaseRepository<Customer, String> {


    @Query("#{#n1ql.selectEntity} WHERE email = $1")
    public Optional<Customer> findByEmail(String email);

    @Query("SELECT  META(`customer`).id AS __id,name,profilePicture,createdOn FROM customer WHERE email= $1")
    public Optional<Customer> findCustomerByEmailForInternalServices(String email);

    public Optional<Customer> findByContact(String contact);

    @Query("SELECT  META(`customer`).id AS __id,name,profilePicture,createdOn FROM customer WHERE META(`customer`).id = $1 OR email= $2")
    public List<Customer> findCustomersByEmailOrId(String customerId , String email);
}
