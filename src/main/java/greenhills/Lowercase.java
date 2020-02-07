package greenhills;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import greenhills.dto.RequestData;
import greenhills.dto.ResponseData;

public class Lowercase implements RequestHandler<RequestData, ResponseData>{
    public ResponseData handleRequest(RequestData request, Context context) {
        return ResponseData.builder().lowercaseName("DEV-" + request.getName().toLowerCase()).build();
    }
}
