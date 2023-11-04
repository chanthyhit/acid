### HOMEWORK
A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent between $50 and $100 in each transaction.
(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).
Given a record of every transaction during a three-month period, calculate the reward points earned for each customer per month and total.

1. All transactions (raw data) </br>
[localhost:8080/api/report/customers](localhost:8080/api/report/customers)
```javascript
[
    {
        "id": 1,
        "fullName": "Chanthy Hit",
        "gender": "Male",
        "email": "chanthy_hit@example.com",
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
&emsp;&emsp;[localhost:8080/api/report/sold_items](localhost:8080/api/report/sold_items) 👍findAll and save </br>
```javascript
[
    {
        "id": 2731,
        "itemName": "Smart TV",
        "vendor": "Samsung",
        "unitPrice": 120.0,
        "qty": 1,
        "dateTime": "2023/09/01",
        "customerId": 1
    },
    {
        "id": 2730,
        "itemName": "Home Security Camera",
        "vendor": "Arlo",
        "unitPrice": 129.99,
        "qty": 3,
        "dateTime": "2023/11/30",
        "customerId": 36
    },
    ...
]
```   
2. Reward points earned for each customer per month and total </br>
   [localhost:8080/api/report/summary_by_email_month](localhost:8080/api/report/summary_by_email_month)
```javascript
{
    "chanthy_hit@example.com": {
        "TOTAL": 90.0,
        "SEPTEMBER": 90.0
    },
    "abigail_brown@example.com": {
        "TOTAL": 84110.0,
        "OCTOBER": 37600.0,
        "SEPTEMBER": 24850.0,
        "NOVEMBER": 21660.0
    },
    ...
}
```
3. Reward points earned by email and month </br>
[localhost:8080/api/report/summary_by_email](localhost:8080/api/report/summary_by_email)
```javascript
{
    "total": 4910950.0,
    "chanthy_hit@example.com": 90.0,
    "alexander_lewis@example.com": 142700.0,
    ...
}
```
&emsp;&emsp;[localhost:8080/api/report/summary_by_month](localhost:8080/api/report/summary_by_month)
```javascript
{
    "total": 4910950.0,
    "OCTOBER": 1701520.0,
    "SEPTEMBER": 1623535.0,
    "NOVEMBER": 1585895.0
}
```
3. All transactions by email </br>
[localhost:8080/api/report/history](localhost:8080/api/report/history)
```javascript
{
    "chanthy_hit@example.com": [
        {
            "id": 2731,
            "itemName": "Smart TV",
            "vendor": "Samsung",
            "unitPrice": 120.0,
            "qty": 1,
            "dateTime": "2023/09/01",
            "customerId": 1
        }
    ],
    ...
}
```