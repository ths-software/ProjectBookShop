package com.projectshop.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.projectshop.storage.StorageProperties;


@Configuration
@EnableJpaAuditing
@EnableConfigurationProperties(StorageProperties.class)
public class AuditConfiguration {

}
