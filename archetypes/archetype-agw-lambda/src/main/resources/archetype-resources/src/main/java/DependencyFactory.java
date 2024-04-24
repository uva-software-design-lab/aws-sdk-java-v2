#parse ( "global.vm")

package ${package};

import java.net.URI;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
#if ($region == 'null')
import software.amazon.awssdk.core.SdkSystemSetting;
#end
import software.amazon.awssdk.http.${httpClientPackageName};
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.${servicePackage}.${serviceClientClassName};
import software.amazon.awssdk.services.${servicePackage}.${serviceClientClassName}Builder;
import ${package}.utils.VariableManager;

/**
 * The module containing all dependencies required by the {@link ${handlerClassName}}.
 */
public class DependencyFactory {

    private DependencyFactory() {}

    /**
     * @return an instance of ${serviceClientClassName}
     */
    public static ${serviceClientClassName} ${serviceClientVariable}Client() {
        ${serviceClientClassName}Builder builder = ${serviceClientClassName}.builder()
                       .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
#if ($region == 'null')
                       .region(Region.of(System.getenv(SdkSystemSetting.AWS_REGION.environmentVariable())))
#else
                       .region(Region.${regionEnum})
#end
                       .httpClientBuilder(${httpClientClassName}.builder());

        if(VariableManager.containsKey("overrideEndpoint")) {
            builder.endpointOverride(URI.create(VariableManager.get("overrideEndpoint")));
        }

        return builder.build();
    }
}
