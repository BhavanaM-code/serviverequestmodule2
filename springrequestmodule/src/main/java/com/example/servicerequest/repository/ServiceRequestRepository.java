package com.example.servicerequest.repository;
import com.example.servicerequest.model.ServiceRequest;

import java.util.*;

public class ServiceRequestRepository {
    private final Map<UUID, ServiceRequest> serviceRequests = new HashMap<>();

    public List<ServiceRequest> findAll() {
        return new ArrayList<>(serviceRequests.values());
    }

    public Optional<ServiceRequest> findById(UUID id) {
        return Optional.ofNullable(serviceRequests.get(id));
    }

    public ServiceRequest save(ServiceRequest request) {
        serviceRequests.put(request.getId(), request);
        return request;
    }

    public void deleteById(UUID id) {
        serviceRequests.remove(id);
    }

    public boolean existsById(UUID id) {
        return serviceRequests.containsKey(id);
    }
}
