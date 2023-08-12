package com.alphaware.documentmaster.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alphaware.documentmaster.entity.FileEntity;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {

	Optional<FileEntity>findByFileUuid(String uuid);
}
