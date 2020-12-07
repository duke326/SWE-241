-- exercise 1
insert into terms (terms_id, terms_description, terms_due_days)
values(6,'Net due 120 days',120); 

-- test
select terms_description, terms_due_days from terms where terms_id=6;

-- exercise 2
update terms
set terms_description='Net due 125 days',
    terms_due_days=125;
    
-- test
select terms_description , terms_due_days from terms where terms_id=6;

-- exercise 3
delete from terms
where terms_id=6;

-- exercise 4
insert into invoices
values(default,32,'AX-014-027',"2018-8-1",434.58
		,0.00 ,0.00,2,'2018-8-31',null);
        
-- test
select invoice_id from invoices where invoice_number='AX-014-027';

-- exercise 5
insert into invoice_line_items(invoice_id,invoice_sequence, account_number,line_item_amount, line_item_description)
values(115,1,160,180.23,'Hard drive'),
		(115,2,527,254.35,'Exchange Server update');

-- exercise 6
update invoices
set credit_total=0.1*invoice_total,
	payment_total=invoice_total-credit_total
where invoice_id=115;

-- exercise 7
update vendors 
set default_account_number=403
where vendor_id=44;

-- exercise 8
update invoices
set terms_id=2
where vendor_id in(
				select  vendor_id
                from vendors
                where default_terms_id=2
);


-- exercise 9

delete from invoice_line_items
where invoice_id=115;

delete from invoices
where invoice_id=115;