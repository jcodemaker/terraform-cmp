package com.crcloud.cloudros.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crcloud.cloudros.dao.entity.ResEcs;
import com.crcloud.cloudros.request.ResEcsRequest;
import com.crcloud.cloudros.response.ResEcsResponse;
import com.crcloud.cloudros.service.ResEcsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author xingcl
 * @Date 2022/4/13
 * 资源编排服务模板管理接口
 */
@RestController
@RequestMapping("/ecs")
public class EcsController {


    @Autowired
    private ResEcsService resEcsService;

    /**
     * 创建ECS接口
     * @return
     */
    @PostMapping(value = "")
    public ResponseEntity<?> createEcs(@RequestBody ResEcsRequest requestBody) throws Exception {
        ResEcs resEcs = new ResEcs();
        resEcs.setName(requestBody.getName());
        resEcs.setDescription(requestBody.getDescription());
        resEcs.setUpgrade(requestBody.isUpgrade());
        boolean i = resEcsService.save(resEcs);
        if (i){
            ResEcsResponse resEcsResponse = new ResEcsResponse();
            resEcsResponse.setId(resEcs.getId());
            resEcsResponse.setName(resEcs.getName());
            resEcsResponse.setDescription(resEcs.getDescription());
            resEcsResponse.setUpgrade(resEcs.isUpgrade());
            return new ResponseEntity<ResEcsResponse>(resEcsResponse, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("internal server error ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 编辑ECS接口
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEcs(@PathVariable("id") String id, @RequestBody ResEcsRequest requestBody) throws Exception {
        ResEcs resEcs = new ResEcs();
        resEcs.setId(id);
        resEcs.setName(requestBody.getName());
        resEcs.setDescription(requestBody.getDescription());
        resEcs.setUpgrade(requestBody.isUpgrade());
        boolean i = resEcsService.updateById(resEcs);
        if (i){
            ResEcsResponse resEcsResponse = new ResEcsResponse();
            resEcsResponse.setId(resEcs.getId());
            resEcsResponse.setName(resEcs.getName());
            resEcsResponse.setDescription(resEcs.getDescription());
            resEcsResponse.setUpgrade(resEcs.isUpgrade());
            return new ResponseEntity<ResEcsResponse>(resEcsResponse, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("internal server error ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 查询ECS接口
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getEcs(@PathVariable("id") String id) throws Exception {

        ResEcs resEcs = resEcsService.getById(id);
        ResEcsResponse resEcsResponse = new ResEcsResponse();
        if (resEcs!= null){

            resEcsResponse.setId(resEcs.getId());
            resEcsResponse.setName(resEcs.getName());
            resEcsResponse.setDescription(resEcs.getDescription());
            resEcsResponse.setUpgrade(resEcs.isUpgrade());
            return new ResponseEntity<ResEcsResponse>(resEcsResponse, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("internal server error ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 查询ECS接口
     * @return
     */
    @GetMapping("")
    public ResponseEntity<?> getEcsList(@RequestParam(name = "name") String name) throws Exception {

        QueryWrapper<ResEcs> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(name)){
            queryWrapper.lambda().like(ResEcs::getName, name);
        }
        List<ResEcs> resEcsList = resEcsService.list(queryWrapper);
        if (resEcsList!= null){
            return new ResponseEntity<List<ResEcs>>(resEcsList, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("internal server error ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 删除ECS接口
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteEcs(@PathVariable("id") String id) throws Exception {
        boolean i = resEcsService.removeById(id);
        if (i){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>("internal server error ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 查询ECS接口
     * @return
     */
    @GetMapping("")
    public ResponseEntity<?> getEcsList(@RequestParam(name = "name", required = false) String name) throws Exception {

        QueryWrapper<ResEcs> queryWrapper = new QueryWrapper<ResEcs>();
        if(StringUtils.isNotEmpty(name)){
            queryWrapper.lambda().like(ResEcs::getName, name);
        }

        List<ResEcs> resEcs = resEcsService.list(queryWrapper);
        if (resEcs!= null){
            return new ResponseEntity<List<ResEcs>>(resEcs, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("internal server error ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
