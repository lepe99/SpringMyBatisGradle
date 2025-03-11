package naver.storage;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "ncp")
@Data
public class NaverConfig {
    private String accessKey;
    private String secretKey;
    private String regionName;
    private String endPoint;
}