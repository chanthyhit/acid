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
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.*;
import java.util.function.DoubleFunction;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public Map<String, Double> calPointByEmail() {
        var histories = getSaleHistories();
        var pointG100 = histories.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .filter(p -> (p.getUnitPrice() * p.getQty()) > 100)
                                .map(p -> round((p.getUnitPrice() * p.getQty()) * 2,0))
                                .reduce(0.0, Double::sum)
                ));
        var pointB50T100 = histories.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .filter(p -> (p.getUnitPrice() * p.getQty()) >= 50 && (p.getUnitPrice() * p.getQty()) <= 100)
                                .map(p -> round((p.getUnitPrice() * p.getQty()) * 1,0))
                                .reduce(0.0, Double::sum)
                ));

        Map<String, Double> accumulated = Stream.of(pointG100, pointB50T100)
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(
                   Map.Entry::getKey,
                   Map.Entry::getValue,
                   Double::sum
                ));

        Double total = accumulated.values().stream().reduce(0.0, Double::sum);
        Map<String, Double> totalPoint = new HashMap<>();
        totalPoint.put("total", total);
        accumulated.putAll(totalPoint);

        return accumulated.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(
                   Map.Entry::getKey,
                   Map.Entry::getValue,
                        (x, y) -> x,
                        LinkedHashMap::new
                ));
    }

    public Map<String, List<OutboundItem>> getSaleHistories(){
        return repository.findAll().stream()
                .collect(Collectors.toMap(
                        Customer::getEmail,
                        customer -> outService.findAll().stream()
                                .filter(i -> i.getCustomerId() == customer.getId())
                                .collect(Collectors.toList())
                ));
    }

    @PostConstruct
    public void loadCustomers() {
        repository.saveAll(readCustomers());
    }
    private List<Customer> readCustomers(){
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
    private static double round(double value, int decimalPlaces) {
        if (decimalPlaces < 0) throw new IllegalArgumentException();
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
}
