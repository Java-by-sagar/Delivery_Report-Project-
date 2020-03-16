
  package com.app.repo;
  
  import org.springframework.data.jpa.repository.JpaRepository;
  
  import com.app.model.Vehicle;
  
  public interface VehicleRepository extends JpaRepository<Vehicle, String> {
  
  }
 