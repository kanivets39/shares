package com.kanivets.shares.repo;

import com.kanivets.shares.models.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SharesRepository extends JpaRepository<Share, Long> {

    List<Share> findAllByCodeEDRPOU(Long codeEDRPOU);

}
