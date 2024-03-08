package com.hypnos.Hypnos.services.image;

import com.hypnos.Hypnos.models.image.Image;
import com.hypnos.Hypnos.repositories.image.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService{

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public void save(Image image) {
        imageRepository.save(image);
    }

    @Override
    public List<Image> listAll() {
        return imageRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        imageRepository.deleteById(id);
    }

    @Override
    public Image listById(int id) {
        return imageRepository.findById(id).get();
    }
}
