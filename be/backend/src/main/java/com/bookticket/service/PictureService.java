/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bookticket.service;

import com.bookticket.dto.PictureDemo;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ADMIN
 */
public interface PictureService {
    PictureDemo sendPicToCloud(MultipartFile file);
}
