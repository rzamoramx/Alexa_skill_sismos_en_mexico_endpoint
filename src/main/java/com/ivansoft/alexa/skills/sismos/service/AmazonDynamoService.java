package com.ivansoft.alexa.skills.sismos.service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.ivansoft.alexa.skills.sismos.model.AmazonDynamoEarthquakeModel;

public class AmazonDynamoService {
    /**
     * Get an instance of the model of earthquakes by querying in Dynamo DB
     * @return a result of query
     */
    public AmazonDynamoEarthquakeModel getData() {
        DynamoDBMapper dynamoDBMapper = getDynamoDBMapper();

        // Query
        AmazonDynamoEarthquakeModel partitionKey = new AmazonDynamoEarthquakeModel();
        partitionKey.setId(1);
        DynamoDBQueryExpression<AmazonDynamoEarthquakeModel> queryExpression = new DynamoDBQueryExpression<AmazonDynamoEarthquakeModel>()
                .withHashKeyValues(partitionKey);

        // Run query
        PaginatedQueryList<AmazonDynamoEarthquakeModel> result = dynamoDBMapper.query(AmazonDynamoEarthquakeModel.class, queryExpression);
        return result.get(0);
    }

    /**
     * Get DynamoDB Mapper
     * @return a DynamoDBMapper instance
     */
    private DynamoDBMapper getDynamoDBMapper() {
        AmazonDynamoDB amazonDynamoDB =  AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
        //AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.defaultClient();
        return new DynamoDBMapper(amazonDynamoDB);
    }
}
