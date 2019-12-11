package com.kanivets.shares.repo;

import com.kanivets.shares.models.Share;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface SharesRepository extends JpaRepository<Share, Long>, JpaSpecificationExecutor<Share> {

    Page<Share> findAllByCodeEDRPOU(Long codeEDRPOU, Pageable pageble);

}
