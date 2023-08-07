/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.service.impl;

import com.bookticket.dto.Response.PictureResponse;
import com.bookticket.service.PictureService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ADMIN
 */
@Service
@Transactional
public class PictureServiceImpl implements PictureService{
//    private final Cloudinary cloudinary;
//
//    @Autowired
//    public PictureServiceImpl(Cloudinary cloudinary) {
//        this.cloudinary = cloudinary;
    
//    }
    @Autowired
    private Cloudinary cloudinary;
    
    @Override
    public PictureResponse sendPicToCloud(MultipartFile file) {
        if(file != null){
            try {
                Map res = this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type","auto"));
                return PictureResponse.builder().url(res.get("secure_url").toString()).build();
            } catch (IOException ex) {
                Logger.getLogger(PictureServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
