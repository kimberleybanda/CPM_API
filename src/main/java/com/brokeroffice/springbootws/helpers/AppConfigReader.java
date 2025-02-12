package com.brokeroffice.springbootws.helpers;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "myapp")

@Data
@NoArgsConstructor

public class AppConfigReader {

   private String integrationId;
   private String integrationKey;
   private String paynowReturnUrl;

   private String  filePath;
}
