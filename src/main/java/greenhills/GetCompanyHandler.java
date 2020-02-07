package greenhills;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import greenhills.dto.Company;
import greenhills.dto.GetCompanyRequest;

import java.util.HashMap;
import java.util.Map;

public class GetCompanyHandler implements RequestHandler<GetCompanyRequest, Company> {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final AmazonDynamoDB AMAZON_DYNAMO_DB = AmazonDynamoDBClientBuilder.defaultClient();
    private static final String ERROR_UNKNOWN_COMPANY = "ERROR: UNKNOWN COMPANY";

    @Override
    public Company handleRequest(GetCompanyRequest input, Context context) {
        HashMap<String, AttributeValue> key = new HashMap<String,AttributeValue>(){{put(ID, new AttributeValue().withN(input.getId().toString()));}};
        String companyName ;
        context.getLogger().log("table name is " + System.getenv("TABLE_NAME"));

        GetItemRequest getItemRequest = new GetItemRequest()
                .withKey(key)
                .withTableName(System.getenv("TABLE_NAME"));

        try {
            Map<String,AttributeValue> returnedItem = AMAZON_DYNAMO_DB.getItem(getItemRequest).getItem();
            context.getLogger().log("returned item is " + returnedItem);
            companyName = (returnedItem == null) ? ERROR_UNKNOWN_COMPANY : returnedItem.get(NAME).getS();

        } catch (AmazonServiceException e) {
            context.getLogger().log("Exception on DB query" + e.getErrorMessage());
            companyName = ERROR_UNKNOWN_COMPANY;
        }

        return Company.builder().id(input.getId()).name(companyName).build();
    }
}
