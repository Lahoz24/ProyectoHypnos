package com.hypnos.Hypnos.services.image;

import com.hypnos.Hypnos.models.image.Image;

import java.util.List;

public interface ImageService {
    void save (Image image);
    List<Image> listAll();
    void deleteById(int id);
    Image listById(int id);
}
