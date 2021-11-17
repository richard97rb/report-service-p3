package com.microservice.report.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.microservice.report.entities.Report;

public interface IReportRepository extends MongoRepository<Report, ObjectId> {

}
