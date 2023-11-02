package com.acid.acid.service;

import com.acid.acid.utilities.Utility;
import com.acid.acid.entity.OutboundItem;
import com.acid.acid.repository.OutboundItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class OutboundItemService {

    private final OutboundItemRepository repository;

    List<OutboundItem> list;

    public Map<String, Double> calPointByMonth(){
        var histories = findAll();
        var pointG100 = histories.stream()
                .filter(i -> (i.getQty() * i.getUnitPrice()) > 100)
                .collect(Collectors.groupingBy(
                        i -> Utility.getMonth(i.getDateTime()),
                        Collectors.reducing(
                                0.0,
                                i -> Utility.round((i.getQty() * i.getUnitPrice()) * 2,0),
                                Double::sum
                        ))
                );
        var pointB50T100 = histories.stream()
                .filter(i -> (i.getUnitPrice() * i.getQty()) >= 50 && (i.getUnitPrice() * i.getQty()) <= 100)
                .collect(Collectors.groupingBy(
                        i -> Utility.getMonth(i.getDateTime()),
                        Collectors.reducing(
                                0.0,
                                i -> Utility.round((i.getUnitPrice() * i.getQty()) * 1, 0),
                                Double::sum
                        ))
                );
        Map<String, Double> accumulated = Stream.of(pointG100, pointB50T100)
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        Double::sum
                ));

        Double total = accumulated.values().stream().reduce(0.0, (x, y) -> x + y);
        Map<String, Double> totalPoint = new HashMap<>();
        totalPoint.put("total".toUpperCase(), total);
        accumulated.putAll(totalPoint);

        return accumulated;
    }

    public List<OutboundItem> findAll(){
        return repository.findAll();
    }

    @PostConstruct
    public void loadutboundItem() {
        repository.saveAll(readOutboundItem());
    }
    public List<OutboundItem> readOutboundItem(){
        LocalDate startDate = LocalDate.of(2023, 9, 1);
        LocalDate endDate = LocalDate.of(2023, 11, 30);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String timeStamp;
        list = new ArrayList<>();
        long id = 0l;
        while (!startDate.isAfter(endDate)) {
            timeStamp = startDate.format(dateFormatter);
            list.add(new OutboundItem(id, "Smart TV", "Samsung", 599.99, getQty(), timeStamp, getCusId()));
            list.add(new OutboundItem(id, "Smart TV", "Samsung", 599.99, getQty(), timeStamp, getCusId()));
            list.add(new OutboundItem(id, "Laptop (15 Ultrabook)", "Dell", 899.99, getQty(), timeStamp, getCusId()));
            list.add(new OutboundItem(id, "Smartphone (iPhone 13)", "Apple", 799.99, getQty(), timeStamp, getCusId()));
            list.add(new OutboundItem(id, "Wireless Headphones", "Sony", 129.99, getQty(), timeStamp, getCusId()));
            list.add(new OutboundItem(id, "Gaming Console (PS5)", "Sony", 499.99, getQty(), timeStamp, getCusId()));
            list.add(new OutboundItem(id, "Home Theater System", "LG", 399.99, getQty(), timeStamp, getCusId()));
            list.add(new OutboundItem(id, "Smartwatch (Galaxy Watch)", "Samsung", 249.99, getQty(), timeStamp, getCusId()));
            list.add(new OutboundItem(id, "Tablet (iPad Air)", "Apple", 499.99, getQty(), timeStamp, getCusId()));
            list.add(new OutboundItem(id, "Bluetooth Speaker", "JBL", 69.99, getQty(), timeStamp, getCusId()));
            list.add(new OutboundItem(id, "DSLR Camera (Canon EOS)", "Canon", 899.99, getQty(), timeStamp, getCusId()));
            list.add(new OutboundItem(id, "Drone (DJI Mavic Air 2)", "DJI", 799.99, getQty(), timeStamp, getCusId()));
            list.add(new OutboundItem(id, "Gaming Laptop (ROG Zephyrus)", "ASUS", 1499.99, getQty(), timeStamp, getCusId()));
            list.add(new OutboundItem(id, "Noise-Canceling Headphones", "Bose", 299.99, getQty(), timeStamp, getCusId()));
            list.add(new OutboundItem(id, "4K Ultra HD Blu-ray Player", "Panasonic", 149.99, getQty(), timeStamp, getCusId()));
            list.add(new OutboundItem(id, "E-Reader (Kindle Paperwhite)", "Amazon", 129.99, getQty(), timeStamp, getCusId()));
            list.add(new OutboundItem(id, "Smart Speaker (Amazon Echo)", "Amazon", 79.99, getQty(), timeStamp, getCusId()));
            list.add(new OutboundItem(id, "Wireless Mouse", "Logitech", 29.99, getQty(), timeStamp, getCusId()));
            list.add(new OutboundItem(id, "VR Headset (Oculus Rift)", "Oculus", 399.99, getQty(), timeStamp, getCusId()));
            list.add(new OutboundItem(id, "Fitness Tracker (Fitbit)", "Fitbit", 129.99, getQty(), timeStamp, getCusId()));
            list.add(new OutboundItem(id, "Monitor (27 4K Display)", "LG", 349.99, getQty(), timeStamp, getCusId()));
            list.add(new OutboundItem(id, "Wireless Router (TP-Link)", "TP-Link", 79.99, getQty(), timeStamp, getCusId()));
            list.add(new OutboundItem(id, "Car GPS Navigation System", "Garmin", 149.99, getQty(), timeStamp, getCusId()));
            list.add(new OutboundItem(id, "External Hard Drive (1TB)", "Western Digital", 79.99, getQty(), timeStamp, getCusId()));
            list.add(new OutboundItem(id, "Game Controller (Xbox)", "Microsoft", 49.99, getQty(), timeStamp, getCusId()));
            list.add(new OutboundItem(id, "Computer Monitor Stand", "VIVO", 29.99, getQty(), timeStamp, getCusId()));
            list.add(new OutboundItem(id, "Wireless Keyboard", "Microsoft", 39.99, getQty(), timeStamp, getCusId()));
            list.add(new OutboundItem(id, "Mini Projector", "Anker", 199.99, getQty(), timeStamp, getCusId()));
            list.add(new OutboundItem(id, "USB-C Hub", "Anker", 24.99, getQty(), timeStamp, getCusId()));
            list.add(new OutboundItem(id, "Wireless Earbuds (AirPods)", "Apple", 159.99, getQty(), timeStamp, getCusId()));
            list.add(new OutboundItem(id, "Home Security Camera", "Arlo", 129.99, getQty(), timeStamp, getCusId()));
            startDate = startDate.plusDays(1);
        }
        return list;
    }

    private int getQty(){
        return new Random().nextInt(15) + 1;
    }
    private long getCusId(){
        long min = 2822;
        long max = 2822 + 51;
        return (long) Math.floor(Math.random() * (max - min + 1) + min);
    }
}
