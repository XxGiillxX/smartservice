package br.com.smartservice.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@DynamicUpdate
@Table(name = "organizacaoFila")
public class Queue implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence_pass")
    @SequenceGenerator(name = "sequence_pass", sequenceName = "sequence_pass",initialValue = 1, allocationSize = 1)
    @Column(name = "position", nullable = false)
    private Integer position;

    @Column(name = "dh_entrada", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dhInput;

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private User user;

}
