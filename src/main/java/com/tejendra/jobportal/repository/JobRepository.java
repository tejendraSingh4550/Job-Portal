package com.tejendra.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tejendra.jobportal.model.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
}