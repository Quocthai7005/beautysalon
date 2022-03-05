package com.doctor.spa.configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.doctor.spa.entity.Invoice;
import com.doctor.spa.processor.InvoiceItemProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public EntityManagerFactory entityManagerFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public FlatFileItemReader<Invoice> reader() {
		FlatFileItemReader<Invoice> reader = new FlatFileItemReader<Invoice>();
		reader.setResource(new ClassPathResource("sample-data.csv"));
		reader.setName("invoiceItemReader");
		reader.setLineMapper(new LineMapper<Invoice>() {

			@Override
			public Invoice mapLine(String line, int lineNumber) throws Exception {
				return null;
			}
			
		});
		return reader;
	}

	@Bean
	public InvoiceItemProcessor processor() {
		return new InvoiceItemProcessor();
	}

	@Bean
	public JpaItemWriter<Invoice> writer(DataSource dataSource) {
		JpaItemWriter<Invoice> writer = new JpaItemWriter<Invoice>();
		writer.setEntityManagerFactory(entityManagerFactory);
		return writer;
	}
}
