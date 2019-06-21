package ru.geekbrains.musicportal.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "CATEGORY")
public class Category extends AbstractEntity {
    @Column(name = "TITLE")
    private String title;

    @ManyToOne
    @JoinColumn(name = "parent")
    private Category parent;

    public List<Category> getHierarchy(List<Category> hrList){
        if(parent != null){
            hrList = parent.getHierarchy(hrList);
        }
        hrList.add(this);
        return hrList;
    }
}
