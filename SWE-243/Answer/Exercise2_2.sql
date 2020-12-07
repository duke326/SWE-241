-- exercise 1
select *
from vendors inner join invoices on vendors.vendor_id=invoices.vendor_id;

-- exercise 2
select vendor_name , invoice_number, invoice_date, invoice_total-payment_total-credit_total as balance_due
from vendors v join invoices i on v.vendor_id=i.vendor_id
where invoice_total-payment_total-credit_total>0
order by v.vendor_name;

-- exercise 3
select vendor_name, default_account_number as default_account, account_description as 'description '
from vendors v join general_ledger_accounts g on v.default_account_number=g.account_number
order by account_description, vendor_name;

-- exercise 4
select vendor_name, invoice_date, invoice_number, invoice_sequence,line_item_amount
from vendors v join invoices i on v.vendor_id=i.vendor_id join invoice_line_items li on i.invoice_id=li.invoice_id
order by vendor_name, invoice_date,invoice_number, invoice_sequence;

-- exercise 5
select v1.vendor_id, v1.vendor_name, concat(v1.vendor_contact_first_name,' ',v1.vendor_contact_last_name) as contact_name
from vendors v1 join vendors v2 on v1.vendor_contact_last_name=v2.vendor_contact_last_name and v1.vendor_id !=v2.vendor_id
order by  v1.vendor_contact_last_name;

-- exercise 6
select  g.account_number, account_description
from general_ledger_accounts g left join invoice_line_items li on g.account_number=li.account_number
where li.invoice_id is null
order by  account_number;

-- exercise 7
select vendor_name, 'Outside CA' as vendor_state 
from vendors
where vendor_state!='CA'
union
select vendor_name, 'CA' as vendor_state 
from vendors
where vendor_state='CA'
order by vendor_name;