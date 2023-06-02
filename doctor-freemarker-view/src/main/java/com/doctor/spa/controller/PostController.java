package com.doctor.spa.controller;

import com.doctor.spa.common.response.ResponseBody;
import com.doctor.spa.dto.PostDto;
import com.doctor.spa.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

import static com.doctor.spa.util.ViewConstant.GET_SINGLE_POST;

@Controller
@RequestMapping(value = "/store/post")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping(value = "/")
    public ResponseEntity<ResponseBody<PostDto>> getPost(@RequestHeader Map<String, String> headers, @RequestParam Map<String, String> parameters) {
        return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, postService.getSinglePost(GET_SINGLE_POST, parameters)));
    }
}
