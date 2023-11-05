package com.acid.acid.service;

import com.acid.acid.utilities.Utility;
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
import java.util.*;
import java.util.stream.Collectors;

import static com.acid.acid.utilities.Utility.rewardData;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final OutboundItemService outService;
    private final ResourceLoader resourceLoader;

    public List<Customer> findAll(){
        return repository.findAll();
    }

    public Map<String, Map<String, Double>> summaryByEmailAndMonth(){
        var histories = getSaleHistories();
        Map<String, Map<String, Double>> groupingByEmailAndDate = histories.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .filter(rewardData)
                                .collect(Collectors.groupingBy(
                                                i -> Utility.getMonth(i.getDateTime()),
                                                Collectors.summingDouble(Utility::calculatePoint)))));
        var total = histories.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .filter(rewardData)
                                .collect(Collectors.groupingBy(
                                        i -> "total",
                                        Collectors.summingDouble(Utility::calculatePoint)))));
        total.forEach((email, totalMap) -> {
            groupingByEmailAndDate.merge(email, totalMap, (existingData, newTotal) -> {
                existingData.put("TOTAL", newTotal.get("total"));
                return existingData;
            });
        });
        return groupingByEmailAndDate;
    }

    public Map<String, Double> calPointByEmail() {
        var histories = getSaleHistories();
        var reward = histories.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .filter(rewardData)
                                .mapToDouble(Utility::calculatePoint).sum()));
        double total = reward.values().stream().reduce(0.0, Double::sum);
        reward.put("total", total);
        return reward.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(
                   Map.Entry::getKey,
                   Map.Entry::getValue,
                        (x, y) -> x,
                        LinkedHashMap::new));
    }

    public Map<String, List<OutboundItem>> getSaleHistories() {
        return repository.findAll().stream()
                .collect(Collectors.toMap(
                        Customer::getEmail,
                        customer -> outService.findAll().stream()
                                .filter(i -> i.getCustomerId() == customer.getId())
                                .collect(Collectors.toList())));
    }

    @PostConstruct
    public void loadCustomers() {
        repository.saveAll(readCustomers());
    }

    private List<Customer> readCustomers() {
        List<Customer> customers = new ArrayList<>();
        try {
            Resource resource = resourceLoader.getResource("classpath:customers.csv");
            BufferedReader reader = new BufferedReader(new FileReader(resource.getURL().getPath()));
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null){
                String[] fields = line.split(",");
                customers.add(
                        new Customer(Long.valueOf(fields[0]), fields[1], fields[2], fields[3], Date.valueOf(fields[4]))
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
