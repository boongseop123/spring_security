package com.cos.photogramstart.domain.image;

import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String caption;
    private String postImageUrl;//사진을 전송받아서 그 사진을 서버의 특정 폴더에 저장->DB에 저장경로를 INSERT
    @JsonIgnoreProperties({"images"})
    @JoinColumn(name="userId")
    @ManyToOne
    private User user;
    private LocalDateTime createDate;
    @PrePersist
    public void createDate(){
        this.createDate= LocalDateTime.now();
    }
}
