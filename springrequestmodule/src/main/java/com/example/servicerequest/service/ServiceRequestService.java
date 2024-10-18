package com.example.servicerequest.service;

import com.example.servicerequest.model.CurrentStatus;
import com.example.servicerequest.model.NotificationService;
import com.example.servicerequest.model.ServiceRequest;
import com.example.servicerequest.repository.ServiceRequestRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ServiceRequestService {
    private final ServiceRequestRepository repository;
    private final NotificationService notificationService;

    public ServiceRequestService(ServiceRequestRepository repository, NotificationService notificationService) {
        this.repository = repository;
        this.notificationService = notificationService;
    }

    public List<ServiceRequest> getAllServiceRequests() {
        return repository.findAll();
    }

    public Optional<ServiceRequest> getServiceRequestById(UUID id) {
        return repository.findById(id);
    }

    public ServiceRequest createServiceRequest(String buildingCode, String description, String createdBy) {
        ServiceRequest request = new ServiceRequest(buildingCode, description, createdBy);
        return repository.save(request);
    }

    public Optional<ServiceRequest> updateServiceRequest(UUID id, ServiceRequest updatedRequest) {
        if (repository.existsById(id)) {
            ServiceRequest existingRequest = repository.findById(id).get();

            // Update fields
            existingRequest.setBuildingCode(updatedRequest.getBuildingCode());
            existingRequest.setDescription(updatedRequest.getDescription());
            existingRequest.setCurrentStatus(updatedRequest.getCurrentStatus());
            existingRequest.setLastModifiedBy(updatedRequest.getLastModifiedBy());
            existingRequest.setLastModifiedDate(LocalDateTime.now());

            if (existingRequest.getCurrentStatus() == CurrentStatus.Complete) {
                notificationService.sendNotification(existingRequest.getCreatedBy(), "Your service request has been completed.");
            }

            return Optional.of(repository.save(existingRequest));
        }
        return Optional.empty();
    }

    public boolean deleteServiceRequest(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
