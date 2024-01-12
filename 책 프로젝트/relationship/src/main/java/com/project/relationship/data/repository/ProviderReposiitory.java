package com.project.relationship.data.repository;

import com.project.relationship.data.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderReposiitory extends JpaRepository<Provider, Long> {
}
