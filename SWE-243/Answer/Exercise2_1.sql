-- exercise8
select vendor_name, vendor_contact_last_name, vendor_contact_first_name
from vendors
order by vendor_contact_last_name, vendor_contact_first_name;

-- exercise9
select concat(vendor_contact_last_name,', ',vendor_contact_first_name) as full_name
from vendors
where left(vendor_contact_last_name,1)='A' or left(vendor_contact_last_name,1)='B' or 
left(vendor_contact_last_name,1)='C'  or left(vendor_contact_last_name,1)='E' 
order by vendor_contact_last_name, vendor_contact_first_name;


-- exercise10
select invoice_due_date as 'Due date' ,invoice_total as 'Invoice Total', 
invoice_total*0.1 as '10%', invoice_total*1.1 as 'Plus 10%' 
from invoices
where invoice_total>=500 and invoice_total<=1000;

-- Exercise 11
SELECT invoice_number,
       invoice_total,
       payment_total + credit_total AS payment_credit_total,
       invoice_total - payment_total - credit_total AS balance_due
FROM invoices
WHERE invoice_total - payment_total - credit_total > 50
ORDER BY balance_due DESC
LIMIT 5;




-- exercise12
select invoice_number , invoice_date, 
invoice_total-payment_total-credit_total as'balance_due', payment_date 
from invoices
where payment_date is null;

-- exercise13
select date_format(current_date, '%m-%d-%Y') as 'current_date'; 

-- exercise14
select (select 50000 as starting_pricipal)*(select 1.065 as interest) as principalplus_interest
