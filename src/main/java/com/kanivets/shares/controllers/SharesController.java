package com.kanivets.shares.controllers;

import com.kanivets.shares.dto.PublicShareDTO;
import com.kanivets.shares.exeptions.NoEntityException;
import com.kanivets.shares.models.Share;
import com.kanivets.shares.repo.SharesRepository;
import com.kanivets.shares.services.AuditService;
import com.kanivets.shares.services.PaginationService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Type;
import java.util.List;

@Slf4j
@RestController
public class SharesController {

    @Autowired
    private SharesRepository sharesRepository;

    @Autowired
    ModelMapper modelMapper;



    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
    @PersistenceContext
    private EntityManager entityManager = entityManagerFactory.createEntityManager();


    @PostMapping("/shares")
    public ResponseEntity<Share> createOrSaveRequest(@Validated @RequestBody Share newShare) {

        sharesRepository.save(newShare);
        log.info("Share was saved");
        return new ResponseEntity<>(newShare, HttpStatus.OK);
    }


    @GetMapping("/shares/public/{id}")
    public ResponseEntity<PublicShareDTO> getShareById(@PathVariable Long id) throws NoEntityException {

        Share share = sharesRepository.findById(id).orElseThrow(() -> new NoEntityException("share", id));
        log.info("Share was sent as public access");

        return new ResponseEntity<>(convertToPublicDto(share), HttpStatus.OK);

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
        log.info("Share was updated");
        return new ResponseEntity<>(share, HttpStatus.OK);
    }

    @GetMapping("/shares/private/{id}")
    public ResponseEntity<List> getShareHistory(@PathVariable Long id) throws NoEntityException {

        List sharesList = AuditService.getHistoryById(id, sharesRepository, entityManager);
        log.info("History of chosen share was sent on client");

        return new ResponseEntity<>(sharesList, HttpStatus.OK);

    }

    @GetMapping("/shares/private/all")
    public ResponseEntity<List> getShareAsPrivate() throws NoEntityException {

        List sharesList = AuditService.getHistoryOfAll( entityManager);
        log.info("History of chosen share was sent on client");

        return new ResponseEntity<>(sharesList, HttpStatus.OK);

    }


    @DeleteMapping("/shares/{id}")
    public ResponseEntity<Share> deleteShare(@PathVariable Long id) throws NoEntityException {

        if (sharesRepository.existsById(id)) {
            sharesRepository.deleteById(id);
            log.info("Share was deleted");
        } else throw new NoEntityException("share", id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/shares/search/private/{codeERDPOU}")
    public ResponseEntity<List<PublicShareDTO>> getSharesByEDRPOU(@PathVariable Long codeERDPOU) throws NoEntityException {


        List<Share> share = sharesRepository.findAllByCodeEDRPOU(codeERDPOU);
        log.info("Shares was sent as public access");

        return new ResponseEntity<>(convertToPublicDtos(share), HttpStatus.OK);


    }

    @GetMapping("/shares/all/public")
    public ResponseEntity<List<PublicShareDTO>> getAllSharesAsPublic(@RequestParam(defaultValue = "0") Integer pageNo,
                                                            @RequestParam(defaultValue = "10") Integer pageSize,
                                                            @RequestParam(defaultValue = "id") String sortBy){
        List<Share> list = PaginationService.getAllShares(pageNo, pageSize, sortBy, sharesRepository);

        return new ResponseEntity<>(convertToPublicDtos(list), new HttpHeaders(), HttpStatus.OK);

    }


    @GetMapping("/shares/all/private")
    public ResponseEntity<List<Share>> getAllSharesAsPrivate(@RequestParam(defaultValue = "0") Integer pageNo,
                                                            @RequestParam(defaultValue = "10") Integer pageSize,
                                                            @RequestParam(defaultValue = "id") String sortBy){
        List<Share> list = PaginationService.getAllShares(pageNo, pageSize, sortBy, sharesRepository);

        return new ResponseEntity<List<Share>>(list, new HttpHeaders(), HttpStatus.OK);

    }



    private PublicShareDTO convertToPublicDto(Share share) {

        return modelMapper.map(share, PublicShareDTO.class);
    }

    private List<PublicShareDTO> convertToPublicDtos(List<Share> shares) {

        Type listType = new TypeToken<List<PublicShareDTO>>(){}.getType();

        return modelMapper.map(shares, listType);
    }

}
