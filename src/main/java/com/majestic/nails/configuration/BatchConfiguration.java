package com.majestic.nails.configuration;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.majestic.nails.entity.Invoice;
import com.majestic.nails.processor.InvoiceItemProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

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
	public JpaItemWriter<Invoice> writer(EntityManagerFactory entityManagerFactory) {
		JpaItemWriter<Invoice> writer = new JpaItemWriter<>();
		writer.setEntityManagerFactory(entityManagerFactory);
		return writer;
	}

	@Bean
	public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
		return jobBuilderFactory.get("importInvoiceJob").incrementer(new RunIdIncrementer()).listener(listener).flow(step1)
				.end().build();
	}

	@Bean
	public Step step1(JpaItemWriter<Invoice> writer) {
		return stepBuilderFactory.get("step1").<Invoice, Invoice>chunk(10).reader(reader()).processor(processor())
				.writer(writer).build();
	}
}
