package com.qianglovepei.model;

import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Document(indexName="secisland",type="city",indexStoreType="fs",shards=5,replicas=1,refreshInterval="-1")
public class City implements Serializable{

    private static final long serialVersionUID = -7139563948013697027L;

    private Integer id;

    private Double score;

    private String name;

    private String description;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override public String toString() {
        return "City{" +
                "id=" + id +
                ", score=" + score +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
