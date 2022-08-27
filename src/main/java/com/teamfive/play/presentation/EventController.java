package com.teamfive.play.presentation;

import com.teamfive.play.model.EventVO;
import com.teamfive.play.model.PaginatedResultVO;
import com.teamfive.play.service.EventService;
import com.teamfive.play.utils.Sanitizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping(value = "/event")
public class EventController extends BaseController implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(EventController.class);
    private Sanitizer sanitizer = new Sanitizer();

    @Autowired
    private EventService eventService;

    @GetMapping(value = "/find")
    public ResponseEntity<?> findById(@RequestParam("id") Long id) {
        String sanitizedId = sanitizer.sanitizeInput(id.toString());
        EventVO event = eventService.findById(Long.parseLong(sanitizedId));
        if (null != event) {
            return new ResponseEntity<EventVO>(event, HttpStatus.OK);
        }
        return new ResponseEntity<String>(dataNotFound(), HttpStatus.OK);
    }

    @GetMapping(value = "/maches")
    public ResponseEntity<?> findByAllMatchesOfTeamId(@RequestParam("id") Long id) {
        String sanitizedId = sanitizer.sanitizeInput(id.toString());
        List<EventVO> list = eventService.findByAllMatchesOfTeamId(Long.parseLong(sanitizedId));
        if (null != list) {
            return new ResponseEntity<List<EventVO>>(list, HttpStatus.OK);
        }
        return new ResponseEntity<String>(dataNotFound(), HttpStatus.OK);
    }


    @GetMapping(value = "/winners")
    public ResponseEntity<?> getAllWinners() {

        List list = eventService.findAllWinners();
        if (null != list) {
            return new ResponseEntity<List>(list, HttpStatus.OK);
        }
        return new ResponseEntity<String>(dataNotFound(), HttpStatus.OK);
    }



    @PostMapping(value = "/save")
    public ResponseEntity<?> createOrsave(@RequestBody EventVO eventVO) {
        EventVO event = eventService.save(eventVO);
        if (null != event) {
            return new ResponseEntity<EventVO>(event, HttpStatus.OK);
        }
        return new ResponseEntity<String>(unsucessfull(), HttpStatus.OK);
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<?> deleteSoft(@RequestBody EventVO eventVO) {
        if (null != eventVO && null != eventVO.getId()) {
            EventVO event = eventService.findById(eventVO.getId());
            if (null != event) {
                eventVO.setDeleted(Boolean.TRUE);
                eventVO = eventService.save(eventVO);
                return new ResponseEntity<EventVO>(event, HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>(deleteUnSucessfull(), HttpStatus.OK);
    }

    @PostMapping(value = "/remove/{id}")
    public ResponseEntity<?> deleteHard(@PathVariable("id") String idVariable) {
        if (!StringUtils.hasText(idVariable)) {
            Long id = Long.parseLong(idVariable);
            EventVO event = eventService.findById(id);
            if (null != event) {
                boolean sucess = eventService.remove(event.getId());
                if (sucess) {
                    return new ResponseEntity<String>(deleteSucessfull(), HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<String>(deleteUnSucessfull(), HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> updateOrSave(@RequestBody EventVO eventVO) {
        if (null != eventVO && null != eventVO.getId()) {
            EventVO event = eventService.findById(eventVO.getId());
            if (null != event) {
                eventVO = eventService.save(eventVO);
                return new ResponseEntity<EventVO>(event, HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>(unsucessfull(), HttpStatus.OK);
    }

    /**
     * event Search.
     *
     * @throws Exception when we have an issue retrieving events
     */
    @GetMapping({"/search"})
    public ResponseEntity<?> searchListPaginated(
            @RequestParam(value = "page_no", defaultValue = "1", required = false) final Integer pageNo,
            @RequestParam(value = "page_size", defaultValue = "10",
                    required = false) final Integer pageSize,
            @RequestParam(value = "active", defaultValue = "true",
                    required = false) final boolean active,
            @RequestParam(value = "sort_by", defaultValue = "createdAt") final String sortBy) {
        String sanitizedSortBy = sanitizer.sanitizeInput(sortBy);
        try {
            List<EventVO> list = eventService.findAllPagedUser(active, pageSize, pageNo, sanitizedSortBy);
            return new ResponseEntity<PaginatedResultVO<EventVO>>(
                    new PaginatedResultVO<EventVO>(list, list.size()), HttpStatus.OK);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ResponseEntity<String>(dataNotFound(), HttpStatus.OK);
    }

    /**
     * event Search.
     *
     * @throws Exception when we have an issue retrieving events
     */
    @GetMapping({"/searchCriteria"})
    public ResponseEntity<?> searchDataListPaged(
            @RequestParam(value = "page_no", defaultValue = "1", required = false) final Integer pageNo,
            @RequestParam(value = "page_size", defaultValue = "10",
                    required = false) final Integer pageSize,
            @RequestParam(value = "active", defaultValue = "true",
                    required = false) final boolean active,
            @RequestParam(value = "sort_by", defaultValue = "createdAt") final String sortBy) {
        String sanitizedSortBy = sanitizer.sanitizeInput(sortBy);
        try {
            List<EventVO> list = eventService.findAllSearchUser(active, pageSize, pageNo, sanitizedSortBy);
            return new ResponseEntity<PaginatedResultVO<EventVO>>(
                    new PaginatedResultVO<EventVO>(list, list.size()), HttpStatus.OK);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ResponseEntity<String>(dataNotFound(), HttpStatus.OK);
    }

    /**
     * Find All By Active.
     *
     * @throws Exception when we have an issue retrieving events
     */
    @GetMapping({"/findAll"})
    public ResponseEntity<?> findAllByActive(

            @RequestParam(value = "active", defaultValue = "true",
                    required = false) final boolean active) {
        try {
            List<EventVO> list = eventService.findAll(active);
            return new ResponseEntity<PaginatedResultVO<EventVO>>(
                    new PaginatedResultVO<EventVO>(list, list.size()), HttpStatus.OK);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ResponseEntity<String>(dataNotFound(), HttpStatus.OK);
    }
}
