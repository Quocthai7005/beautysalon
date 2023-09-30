package com.majestic.nails.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.costexplorer.AWSCostExplorer;
import com.amazonaws.services.costexplorer.AWSCostExplorerClientBuilder;
import com.amazonaws.services.costexplorer.model.DateInterval;
import com.amazonaws.services.costexplorer.model.Dimension;
import com.amazonaws.services.costexplorer.model.DimensionValues;
import com.amazonaws.services.costexplorer.model.Expression;
import com.amazonaws.services.costexplorer.model.GetCostAndUsageRequest;
import com.amazonaws.services.costexplorer.model.GetCostAndUsageResult;
import com.amazonaws.services.costexplorer.model.Granularity;
import com.majestic.nails.service.AwsBillingService;

@Service
public class AwsBillingServiceImpl implements AwsBillingService {

	@Value("${aws.iam.accessKey}")
	private String accessKey;

	@Value("${aws.iam.secretKey}")
	private String secretKey;

	@Override
	public GetCostAndUsageResult getCEWithDimension(String startDate, String endDate) {

		AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);

		Expression expression = new Expression();
		DimensionValues dimensions = new DimensionValues();
		dimensions.withKey(Dimension.SERVICE);
		dimensions.withValues("Amazon Macie");

		expression.withDimensions(dimensions);

		final GetCostAndUsageRequest awsCERequest = new GetCostAndUsageRequest()
				.withTimePeriod(new DateInterval().withStart(startDate).withEnd(endDate))
				.withGranularity(Granularity.MONTHLY);

		GetCostAndUsageResult result = null;
		try {
			AWSCostExplorer ce = AWSCostExplorerClientBuilder.standard()
					.withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
			result = ce.getCostAndUsage(awsCERequest);
		} catch (final Exception e) {
			System.out.println(e);
		}
		return result;
	}
}
