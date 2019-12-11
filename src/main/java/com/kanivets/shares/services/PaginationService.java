package com.kanivets.shares.services;

import com.kanivets.shares.models.Share;
import com.kanivets.shares.repo.SharesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaginationService {

    public static List<Share> getAllShares(Integer pageNo, Integer pageSize, String sortBy, SharesRepository sharesRepository)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Share> pagedResult = sharesRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    public static List getAllSharesWithAudit(Integer pageNo, Integer pageSize, String sortBy, EntityManager entityManager)
    {

        return AuditService.getHistoryOfAll( pageNo, pageSize, sortBy, entityManager);
    }


    public static List<Share> getAllSharesByEDRPOU(Integer pageNo, Integer pageSize, String sortBy, SharesRepository sharesRepository, Long codeEDRPOU)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Share> pagedResult = sharesRepository.findAllByCodeEDRPOU(codeEDRPOU, paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

}
