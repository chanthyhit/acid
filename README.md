### HOMEWORK
A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent between $50 and $100 in each transaction.
(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).
Given a record of every transaction during a three-month period, calculate the reward points earned for each customer per month and total.


1. All transactions </br>
[localhost:8080/api/report/customers](localhost:8080/api/report/customers)
```javascript
[
    {
        "id": 1,
        "fullName": "John Kevin",
        "gender": "Male",
        "email": "john_kevin@example.com",
        "date": "2023-11-01"
    },
    {
        "id": 2,
        "fullName": "Alice Johnson",
        "gender": "Male",
        "email": "alice_johnson@example.com",
        "date": "2023-11-01"
    },
    ...
]
```
&emsp;&emsp;[localhost:8080/api/report/sold_items](localhost:8080/api/report/sold_items)
```javascript
[
    {
        "id": 1,
        "itemName": "Smart TV",
        "vendor": "Samsung",
        "unitPrice": 599.99,
        "qty": 5,
        "dateTime": "2023/09/01",
        "customerId": 12
    },
    {
        "id": 2,
        "itemName": "Laptop (15 Ultrabook)",
        "vendor": "Dell",
        "unitPrice": 899.99,
        "qty": 6,
        "dateTime": "2023/09/01",
        "customerId": 34
    },
    ...
]
```   
2. Reward points earned for each customer per month and total </br>
   [localhost:8080/api/report/summary_by_email_month](localhost:8080/api/report/summary_by_email_month)
```javascript
{
    "william_davis@example.com": {
        "TOTAL": 365740.0,
        "OCTOBER": 132520.0,
        "SEPTEMBER": 139290.0,
        "NOVEMBER": 93930.0
    },
    "emma_rodriguez@example.com": {
        "TOTAL": 220480.0,
        "OCTOBER": 49270.0,
        "SEPTEMBER": 94220.0,
        "NOVEMBER": 76990.0
    },
    ...
}
```
3. Reward points earned by month and email </br>
[localhost:8080/api/report/summary_by_email](localhost:8080/api/report/summary_by_email)
```javascript
{
    "total": 1.4450545E7,
    "addison_thomas@example.com": 435160.0,
    "lily_smith@example.com": 434250.0,
    "sophia_king@example.com": 430885.0,
    ...
}
```
&emsp;&emsp;[localhost:8080/api/report/summary_by_month](localhost:8080/api/report/summary_by_month)
```javascript
{
    "TOTAL": 1.4450545E7,
    "OCTOBER": 5007975.0,
    "SEPTEMBER": 4881425.0,
    "NOVEMBER": 4561145.0
}
```
3. All transactions by email </br>
[localhost:8080/api/report/history](localhost:8080/api/report/history)
```javascript
{
    "william_davis@example.com": [
        {
            "id": 65,
            "itemName": "Gaming Console (PS5)",
            "vendor": "Sony",
            "unitPrice": 499.99,
            "qty": 2,
            "dateTime": "2023/09/03",
            "customerId": 47
        },
        {
            "id": 235,
            "itemName": "Computer Monitor Stand",
            "vendor": "VIVO",
            "unitPrice": 29.99,
            "qty": 13,
            "dateTime": "2023/09/08",
            "customerId": 47
        },
        ...
    ],
    "emma_rodriguez@example.com": [
        {
            "id": 149,
            "itemName": "Wireless Earbuds (AirPods)",
            "vendor": "Apple",
            "unitPrice": 159.99,
            "qty": 8,
            "dateTime": "2023/09/05",
            "customerId": 16
        },
        ...
    ]
}
```