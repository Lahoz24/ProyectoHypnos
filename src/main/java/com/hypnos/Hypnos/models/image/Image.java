package com.hypnos.Hypnos.models.image;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String image;

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getImage(){
        return image;
    }
    public void setImage(String image){
        this.image = image;
    }
}
