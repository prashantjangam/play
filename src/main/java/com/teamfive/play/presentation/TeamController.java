package com.teamfive.play.presentation;

import com.teamfive.play.model.PaginatedResultVO;
import com.teamfive.play.model.TeamVO;
import com.teamfive.play.service.TeamService;
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
@RequestMapping(value = "/team")
public class TeamController extends BaseController implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(TeamController.class);

    private Sanitizer sanitizer = new Sanitizer();

    @Autowired
    private TeamService teamService;

    @GetMapping(value = "/find")
    public ResponseEntity<?> findById(@RequestParam("id") Long id) {
        String sanitizedId = sanitizer.sanitizeInput(id.toString());
        TeamVO category = teamService.findById(Long.parseLong(sanitizedId));
        if (null != category) {
            return new ResponseEntity<TeamVO>(category, HttpStatus.OK);
        }
        return new ResponseEntity<String>(dataNotFound(), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> createOrsave(@RequestBody TeamVO categoryVO) {
        TeamVO category = teamService.save(categoryVO);
        if (null != category) {
            return new ResponseEntity<TeamVO>(category, HttpStatus.OK);
        }
        return new ResponseEntity<String>(unsucessfull(), HttpStatus.OK);
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<?> deleteSoft(@RequestBody TeamVO categoryVO) {
        if (null != categoryVO && null != categoryVO.getId()) {
            TeamVO category = teamService.findById(categoryVO.getId());
            if (null != category) {
                categoryVO.setDeleted(Boolean.TRUE);
                categoryVO = teamService.save(categoryVO);
                return new ResponseEntity<TeamVO>(category, HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>(deleteUnSucessfull(), HttpStatus.OK);
    }

    @PostMapping(value = "/remove/{id}")
    public ResponseEntity<?> deleteHard(@PathVariable("id") String idVariable) {
        if (!StringUtils.hasText(idVariable)) {
            Long id = Long.parseLong(idVariable);
            TeamVO category = teamService.findById(id);
            if (null != category) {
                boolean sucess = teamService.remove(category.getId());
                if (sucess) {
                    return new ResponseEntity<String>(deleteSucessfull(), HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<String>(deleteUnSucessfull(), HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> updateOrSave(@RequestBody TeamVO categoryVO) {
        if (null != categoryVO && null != categoryVO.getId()) {
            TeamVO category = teamService.findById(categoryVO.getId());
            if (null != category) {
                categoryVO = teamService.save(categoryVO);
                return new ResponseEntity<TeamVO>(category, HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>(unsucessfull(), HttpStatus.OK);
    }

    /**
     * category Search.
     *
     * @throws Exception when we have an issue retrieving categorys
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
            List list = teamService.findAllPagedUser(active, pageSize, pageNo, sanitizedSortBy);
            return new ResponseEntity<PaginatedResultVO<TeamVO>>(
                    new PaginatedResultVO<TeamVO>(list, list.size()), HttpStatus.OK);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ResponseEntity<String>(dataNotFound(), HttpStatus.OK);
    }

    /**
     * category Search.
     *
     * @throws Exception when we have an issue retrieving categorys
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
            List list = teamService.findAllSearchUser(active, pageSize, pageNo, sanitizedSortBy);
            return new ResponseEntity<PaginatedResultVO<TeamVO>>(
                    new PaginatedResultVO<TeamVO>(list, list.size()), HttpStatus.OK);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ResponseEntity<String>(dataNotFound(), HttpStatus.OK);
    }

    /**
     * Find All By Active.
     *
     * @throws Exception when we have an issue retrieving categorys
     */
    @GetMapping({"/findAll"})
    public ResponseEntity<?> findAllByActive(

            @RequestParam(value = "active", defaultValue = "true",
                    required = false) final boolean active) {
        try {
            List list = teamService.findAll(active);
            return new ResponseEntity<PaginatedResultVO<TeamVO>>(
                    new PaginatedResultVO<TeamVO>(list, list.size()), HttpStatus.OK);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ResponseEntity<String>(dataNotFound(), HttpStatus.OK);
    }
}
