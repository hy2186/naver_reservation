package com.ntscorp.reservation.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.ntscorp.reservation.**.service",
	"com.ntscorp.reservation.**.mapper", "com.ntscorp.reservation.common"})
@Import(DBConfiguration.class)
public class ApplicationConfiguration {
}
