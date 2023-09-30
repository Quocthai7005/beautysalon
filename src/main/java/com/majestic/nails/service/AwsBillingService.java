package com.majestic.nails.service;

import com.amazonaws.services.costexplorer.model.GetCostAndUsageResult;

public interface AwsBillingService {

	GetCostAndUsageResult getCEWithDimension(String startDate, String endDate);
}
