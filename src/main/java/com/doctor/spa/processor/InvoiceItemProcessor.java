package com.doctor.spa.processor;

import org.springframework.batch.item.ItemProcessor;

import com.doctor.spa.entity.Invoice;

public class InvoiceItemProcessor implements ItemProcessor<Invoice, Invoice> {

	@Override
	public Invoice process(Invoice item) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
