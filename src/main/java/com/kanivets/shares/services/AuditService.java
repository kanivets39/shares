package com.kanivets.shares.services;

import com.kanivets.shares.exeptions.NoEntityException;
import com.kanivets.shares.models.Share;
import com.kanivets.shares.repo.SharesRepository;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class AuditService {


    public static List getHistoryById(Long id, SharesRepository sharesRepository, EntityManager entityManager) throws NoEntityException {
        List readerFactory;
        if (sharesRepository.existsById(id)) {
            readerFactory = AuditReaderFactory
                    .get(entityManager)
                    .createQuery()
                    .forRevisionsOfEntity(Share.class, false, true)
                    .add(AuditEntity.property("id").eq(id))
                    .getResultList();
        } else throw new NoEntityException("share", id);
        return readerFactory;
    }

    static List getHistoryOfAll(Integer pageNo, Integer pageSize, String sortBy, EntityManager entityManager) {
        List readerFactory;
        readerFactory = AuditReaderFactory
                .get(entityManager)
                .createQuery()
                .forRevisionsOfEntity(Share.class, false, true)
                .addOrder(AuditEntity.property(sortBy).desc())
                .setFirstResult(pageNo)
                .setMaxResults(pageSize)
                .getResultList();

        return readerFactory;
    }

    private AuditService() {
    }

}
