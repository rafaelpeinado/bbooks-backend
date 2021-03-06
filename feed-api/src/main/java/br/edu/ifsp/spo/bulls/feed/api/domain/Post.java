package br.edu.ifsp.spo.bulls.feed.api.domain;

import br.edu.ifsp.spo.bulls.feed.api.enums.PostPrivacy;
import br.edu.ifsp.spo.bulls.feed.api.enums.TypePost;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.PrePersist;

@Data
@Entity
@ApiModel(value = "Objeto de domínio: Publicações ")
@Table
public class Post {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @ApiModelProperty(value = "Identificador")
    private UUID id;

    @JoinTable(name="profile",
            joinColumns={@JoinColumn(name="profile",
                    referencedColumnName="id")})
    private int profileId;

    @Column(length = 32)
    private String description;

    private LocalDateTime creationDate;

    @ApiModelProperty(value = "Link da imagem")
    private String image;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypePost tipoPost;

    private UUID upperPostId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PostPrivacy privacy;

    //TODO: Campo da enquete
    //TODO: Reações

    @PrePersist
    public void prePersist() {
        creationDate = LocalDateTime.now();
    }
}
