package cn.edu.aust.pojo.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "tags")
@Data
@NoArgsConstructor
@ToString
public class TagsDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tag;

    private Integer count;

}