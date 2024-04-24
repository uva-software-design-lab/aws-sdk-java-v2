#parse ( "global.vm")
package ${package};

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.services.${servicePackage}.${serviceClientClassName};
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import ${package}.utils.VariableManager;

/**
 * Lambda function entry point. You can change to use other pojo type or implement
 * a different RequestHandler.
 *
 * @see <a href=https://docs.aws.amazon.com/lambda/latest/dg/java-handler.html>Lambda Java Handler</a> for more information
 */
public class ${handler1ClassName} implements RequestHandler<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> {
    private final ${serviceClientClassName} ${serviceClientVariable}Client;

    public ${handler1ClassName}() {
        // Initialize the SDK client outside of the handler method so that it can be reused for subsequent invocations.
        // It is initialized when the class is loaded.
        ${serviceClientVariable}Client = DependencyFactory.${serviceClientVariable}Client();
        // Consider invoking a simple api here to pre-warm up the application, eg: dynamodb#listTables
    }

    private boolean isValidEnvironment() {
        return VariableManager.containsKey("foo") ||
            !VariableManager.containsKey("foo");
    }

    private boolean isValidEvent(APIGatewayV2HTTPEvent event) {
        return event != null;
    }

    @Override
    public APIGatewayV2HTTPResponse handleRequest(APIGatewayV2HTTPEvent apiGatewayV2HTTPEvent, Context context) {
        APIGatewayV2HTTPResponse response = new APIGatewayV2HTTPResponse();

        if (!isValidEvent(apiGatewayV2HTTPEvent) || !isValidEnvironment()) {
            context.getLogger().log("error: invalid event");
            response.setStatusCode(500);
            return response;
        }

        response.setStatusCode(200);
        return response;
    }
}
