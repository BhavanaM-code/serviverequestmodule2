package com.example.servicerequest;

import com.example.servicerequest.model.NotificationService;
import com.example.servicerequest.repository.ServiceRequestRepository;
import com.example.servicerequest.service.ServiceRequestService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServiceRequestModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRequestModuleApplication.class, args);
	}
	
    @Bean
    public ServiceRequestRepository serviceRequestRepository() {
        return new ServiceRequestRepository();
    }

    @Bean
    public NotificationService notificationService() {
        return new NotificationService();
    }

    @Bean
    public ServiceRequestService serviceRequestService(ServiceRequestRepository repository, NotificationService notificationService) {
        return new ServiceRequestService(repository, notificationService);
    }

}
