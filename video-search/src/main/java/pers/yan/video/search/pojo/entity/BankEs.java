package pers.yan.video.search.pojo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author likaiyan
 * @date 2020/9/30 1:39 下午
 */
@Data
@Document(indexName = "bank", replicas = 0)
public class BankEs {

    @Id
    private Long id;

    private Integer account_number;

    private Integer balance;

    private String firstname;

    private String lastname;

    private Integer age;

    private String address;

    private String employer;

    @Field(type = FieldType.Keyword)
    private String email;

    private String city;

    private String state;

}
