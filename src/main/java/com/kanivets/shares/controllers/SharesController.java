package com.kanivets.shares.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.kanivets.shares.exeptions.NoEntityException;
import com.kanivets.shares.models.Share;
import com.kanivets.shares.repo.SharesRepository;
import com.kanivets.shares.services.serializers.PublicSharesSerializer;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Slf4j
@RestController
public class SharesController {

    @Autowired
    private SharesRepository sharesRepository;



    @PostMapping("/shares")
    public ResponseEntity<Share> createOrSaveRequest(@Validated @RequestBody Share newShare) {

        sharesRepository.save(newShare);
        log.info("Share was saved");
        return new ResponseEntity<>(newShare, HttpStatus.OK);
    }


    @GetMapping("/shares/private/{id}")
    public String getShareById(@PathVariable Long id) throws NoEntityException, JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module =
                new SimpleModule("PublicSharesSerializer", new Version(1, 0, 0, null, null, null));
        module.addSerializer(Share.class, new PublicSharesSerializer());
        mapper.registerModule(module);
        Share share = sharesRepository.findById(id).orElseThrow(() -> new NoEntityException("share", id));
        return mapper.writeValueAsString(share);

    }

    @PutMapping("/shares/{id}")
    public ResponseEntity<Share> updateNote(@PathVariable(value = "id") Long id, @Validated @RequestBody Share newShare) throws NoEntityException {

        Share share = sharesRepository.findById(id)
                .orElseThrow(() -> new NoEntityException("share", id));

        share.setComment(newShare.getComment());
        share.setTotalFaceValue(newShare.getFaceValue() * newShare.getAmount());
        share.setFaceValue(newShare.getFaceValue());
        share.setPaidStateDuty(newShare.getPaidStateDuty());
        share.setAuthorizedFundSize(newShare.getAuthorizedFundSize());
        share.setCodeEDRPOU(newShare.getCodeEDRPOU());
        share.setAmount(newShare.getAmount());
        share.setReleaseDate(newShare.getReleaseDate());

        sharesRepository.save(share);
        return new ResponseEntity<>(share, HttpStatus.OK);
    }

    @GetMapping("/shares/history/{id}")
    public ResponseEntity<List> getShareHistory(@PathVariable Long id) throws NoEntityException {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
        EntityManager em = emf.createEntityManager();
        List readerFactory;
        if (sharesRepository.existsById(id)) {
            readerFactory = AuditReaderFactory
                    .get(em)
                    .createQuery()
                    .forRevisionsOfEntity(Share.class, false, true)
                    .add(AuditEntity.property("id").eq(id))
                    .getResultList();
        } else throw new NoEntityException("share", id);

        return new ResponseEntity<>(readerFactory, HttpStatus.OK);

    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Share> deleteShare(@PathVariable Long id) throws NoEntityException {

        if (sharesRepository.existsById(id)) {
            sharesRepository.deleteById(id);
        } else throw new NoEntityException("share", id);

        return ResponseEntity.noContent().build();
    }

}
