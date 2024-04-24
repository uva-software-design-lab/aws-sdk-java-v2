#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;

public class ${handler1ClassName}Test {

    @Test
    public void handleRequest_shouldReturnConstantValue() {
        ${handler1ClassName} function = new ${handler1ClassName}();
        APIGatewayV2HTTPEvent event = APIGatewayV2HTTPEvent.builder().build();
        APIGatewayV2HTTPResponse result = function.handleRequest(event, null);
        assertEquals(200, result.getStatusCode());
    }
}
