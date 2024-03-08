package com.hypnos.Hypnos.repositories.image;

import com.hypnos.Hypnos.models.image.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
