package com.egamorim.edish.application.repository;

import com.egamorim.edish.application.domain.MenuItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MenuItemRepository extends MongoRepository<MenuItem, String> {
}
