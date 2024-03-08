package com.hypnos.Hypnos.controllers;

import com.hypnos.Hypnos.models.image.Image;
import com.hypnos.Hypnos.services.image.ImageService;
import com.hypnos.Hypnos.services.uploadFile.UploadFileService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.MalformedURLException;

@RestController
@RequestMapping("/images")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @Autowired
    private UploadFileService uploadFileService;

    @GetMapping
    public String listImages(Model model){
        try{
            model.addAttribute("listImages",imageService.listAll());
        } catch (Exception e){
            e.printStackTrace();
        }
        return "image/listImages";
    }
    @GetMapping(value = "/uploads/{filename}")
    public ResponseEntity<Resource> goImage (@PathVariable String filename){
        Resource resource = null;
        try{
            resource = uploadFileService.load(filename);
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
    @GetMapping("/new")
    public String newImage(Model model){
        model.addAttribute("image", new Image());
        model.addAttribute("listImages", imageService.listAll());
        return "image/image";
    }
    @PostMapping("/save")
    public String saveImage (@Validated @ModelAttribute("image") Image image, BindingResult result, Model model,
            @RequestParam("file") MultipartFile picture, RedirectAttributes flash, SessionStatus status)
            throws  Exception{
        if (result.hasErrors()){
            System.out.println(result.getFieldError());
            return "image/image";
        } else {
            if (!picture.isEmpty()){
                if (image.getId() > 0 && image.getImage() != null && image.getImage().length() > 0 ){
                    uploadFileService.delete(image.getImage());
                }
                String uniqueFileName = uploadFileService.copy(picture);
                image.setImage(uniqueFileName);
            }
            imageService.save(image);
            status.setComplete();
        }
        return "redirect: /images";
    }
}
