package com.acid.acid.service;

import com.acid.acid.entity.Customer;
import com.acid.acid.entity.OutboundItem;
import com.acid.acid.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private List<Customer> customers;

    private final CustomerRepository repository;
    private final OutboundItemService outService;
    private final ResourceLoader resourceLoader;

    public List<Customer> findAll(){
        return repository.findAll();
    }

    /**
     * A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent between $50 and $100 in each transaction.
     * (e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).
     *
     *
     * Given a record of every transaction during a three-month period, calculate the reward points earned for each customer per month and total.
     */

    public void calPoint(){
        Map<String, Integer> pointO100;
        Map<String, Integer>  point50T100;

    }

    @PostConstruct
    public void loadCustomers1() {
        repository.saveAll(readCustomers());
    }
    public List<Customer> readCustomers(){
        customers = new ArrayList<>();
        try {
            Resource resource = resourceLoader.getResource("classpath:customers.csv");
            BufferedReader reader = new BufferedReader(new FileReader(resource.getURL().getPath()));
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null){
                String[] fields = line.split(",");
                customers.add(
                        new Customer(
                            Long.valueOf(fields[0]),
                            fields[1],
                            fields[2],
                            fields[3],
                            Date.valueOf(fields[4])
                        )
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
