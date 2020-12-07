-- exercise 1
select vendor_id, SUM(invoice_total) as invoice_total_sum
from invoices
group by vendor_id;

-- exercise 2
select vendor_name, sum(payment_total) as payment_total_sum
from invoices i join vendors v on i.vendor_id=v.vendor_id
group by vendor_name
order by payment_total_sum desc;

-- exercise 3 
select vendor_name, count(*) as invoices_count, sum(invoice_total) as invoice_total_sum
from vendors v join invoices i on v.vendor_id=i.vendor_id
group by vendor_name
order by invoices_count desc;

-- exercise 4 
select account_description,count(*) as items_count, sum(line_item_amount) as line_item_amount_sum 
from general_ledger_accounts g join invoice_line_items i on g.account_number=i.account_number
group by account_description
having items_count>1
order by line_item_amount_sum desc ;

-- exercise 5
select account_description,count(*) as items_count, sum(line_item_amount) as line_item_amount_sum 
from general_ledger_accounts g join invoice_line_items ili on g.account_number=ili.account_number
 join invoices i on  ili.invoice_id=i.invoice_id
 where invoice_date between '2018-04-01' and  '2018-06-30' 
 group by account_description
having items_count>1
order by line_item_amount_sum desc ;

-- exercise 6
select account_number , sum(line_item_amount) as line_item_amount_sum
from invoice_line_items ili
group by account_number with rollup;

-- exercise 7
select vendor_name, count(distinct account_number) as accounts_count
from vendors v join invoices i on v.vendor_id = i.vendor_id
join invoice_line_items li on i.invoice_id = li.invoice_id
group by vendor_name
having accounts_count>1;

-- exercise 8
-- grouping used to distinct the roll up result 
-- 1 is with rollup 0 is not 
select IF(GROUPING(terms_id) = 1, 'Totals', terms_id) AS terms_id,
IF(GROUPING(vendor_id) = 1, 'ID Totals', vendor_id) AS vendor_id
,MAX( payment_date) as last_payment_date,
sum(invoice_total-payment_total-credit_total) as balance_due_sum
from invoices
group by terms_id, vendor_id with rollup;
