package com.brokeroffice.springbootws.controllers;

import com.brokeroffice.springbootws.entities.*;
import com.brokeroffice.springbootws.helpers.ApiResponse;

import com.brokeroffice.springbootws.models.PostId;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/v1/api")
public interface ImplReports {

    @PostMapping(value = "login")
    ApiResponse login(@RequestBody Users users)  throws Exception;

    @PostMapping(value = "register")
    ApiResponse register(@RequestBody Users users)  throws Exception;

    @GetMapping(value = "usertypes")
    ApiResponse usertypes()  throws Exception;

    @PostMapping(value = "product")
    ApiResponse product(@RequestBody Product product)  throws Exception;

    @GetMapping(value = "product")
    ApiResponse product()  throws Exception;

    @PostMapping(value = "product_id")
    ApiResponse product_id(@RequestBody PostId postId)  throws Exception;

    @PostMapping(value = "products")
    ApiResponse products(@RequestPart MultipartFile multipartFile,@RequestPart Products products)  throws Exception;

    @PostMapping(value = "products_id")
    ApiResponse products_id(@RequestBody PostId postId)  throws Exception;

    @PostMapping(value = "products_delete")
    ApiResponse products_delete(@RequestBody PostId postId)  throws Exception;

    @GetMapping(value = "products")
    ApiResponse products()  throws Exception;

    @PostMapping(value = "video")
    ApiResponse videos(@RequestPart MultipartFile file,@RequestPart VideoTutorials videoTutorials)  throws Exception;

    @GetMapping(value = "videos")
    ApiResponse videos()  throws Exception;

    @PostMapping(value = "videos_id")
    ApiResponse videos_id(@RequestBody PostId postId)  throws Exception;

    @PostMapping(value = "pdf")
    ApiResponse pdf(@RequestPart MultipartFile file,@RequestPart PdfTutorials pdfTutorials)  throws Exception;

    @GetMapping(value = "pdf")
    ApiResponse pdf()  throws Exception;

    @PostMapping(value = "pdf_id")
    ApiResponse pdf_id(@RequestBody PostId postId)  throws Exception;


    /**
     * This is the EndPoint to Post and Edit **/
    @PostMapping(value = "deals")
    ApiResponse deals(@RequestBody Deals deals)  throws Exception;

    /**
     * This is the EndPoint that fetches all deals **/
    @GetMapping(value = "deals")
    ApiResponse deals()  throws Exception;

    @GetMapping(value = "deals_approved")
    ApiResponse dealsApproved(long user_id)  throws Exception;

    @GetMapping(value = "deals_nonapproved")
    ApiResponse dealsNonApproved(long user_id)  throws Exception;

    @GetMapping(value = "deals_adminapproved")
    ApiResponse dealsAdminApproved()  throws Exception;

    @GetMapping(value = "deals_adminnonapproved")
    ApiResponse dealsAdminNonApproved()  throws Exception;

    @PostMapping(value = "SmsUpload")
    ApiResponse SmsUpload(@RequestParam("file") MultipartFile file,
                          String message)  throws Exception;


}
