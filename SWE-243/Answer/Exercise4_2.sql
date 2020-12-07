select distinct vendor_name
from vendors
where vendor_id in (select vendor_id from invoices)
order by vendor_name;

-- exericse 2
select invoice_number, invoice_total 
from invoices
where payment_total >(select avg(payment_total) from invoices where payment_total>0);

-- exercise 3 
select account_number,account_description
from general_ledger_accounts gl
where not exists(select * from invoice_line_items where gl.account_number=account_number)
order by account_number;

-- exercise 4 
select vendor_name, i.invoice_id,invoice_sequence, line_item_amount
from invoices i join vendors v on v.vendor_id=i.vendor_id
join invoice_line_items li on li.invoice_id=i.invoice_id
where i.invoice_id in (select distinct invoice_id from invoice_line_items where invoice_sequence>1)
ORDER BY vendor_name, i.invoice_id, invoice_sequence;

-- exercise 5 
select sum(invoice_max) as sum_of_max
from (
-- first query
select vendor_id, max(invoice_total) as invoice_max
from invoices 
where invoice_total-credit_total-payment_total>0
group by vendor_id) t;

-- exercise 6
select vendor_name, vendor_city, vendor_state
from vendors
where concat(vendor_state, vendor_city) not in (
select concat(vendor_state, vendor_city) as vendor_city_state
from vendors
group by vendor_city_state
having count(*)>1
)
order by vendor_state, vendor_city;

-- exercise 7
select vendor_name, invoice_number, invoice_date, invoice_total
from vendors v join invoices i on i.vendor_id=v.vendor_id
where (select min(invoice_date)
from invoices
where vendor_id=i.vendor_id)
group by vendor_name;