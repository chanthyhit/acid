package com.acid.acid.controller;

import com.acid.acid.entity.Customer;
import com.acid.acid.entity.OutboundItem;
import com.acid.acid.service.CustomerService;
import com.acid.acid.service.OutboundItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/report")
public class ReportController {

    private final CustomerService cusService;
    private final OutboundItemService outService;

    @GetMapping("/customers")
    public List<Customer> customers(){
        return cusService.findAll();
    }

    @RequestMapping(value = "/sold_items", method = RequestMethod.GET)
    public List<OutboundItem> outboundItems(){
        return outService.findAll();
    }

    @RequestMapping("/history")
    public Map<String, List<OutboundItem>> getSaleHistories(){
        return cusService.getSaleHistories();
    }

    @GetMapping("/summary_by_email")
    public Map<String, Double> summaryByEmail(){
        return cusService.calPointByEmail();
    }

    @GetMapping("/summary_by_month")
    public Map<String, Double> summaryByMonth(){
        return outService.calPointByMonth();
    }
}
