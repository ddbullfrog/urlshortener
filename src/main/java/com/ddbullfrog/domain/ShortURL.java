package com.ddbullfrog.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.time.Instant;

@RedisHash("shorturls")
public class ShortURL implements Serializable {

    @Id
    @JsonIgnore
    private int id;

    @Indexed
    private String slug;
    @JsonIgnore
    private String url;
    @JsonIgnore
    private Instant createDate = Instant.now();
    @JsonIgnore
    private int hit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    @Override
    public String toString() {
        return "ShortURL{" +
                "id=" + id +
                ", slug='" + slug + '\'' +
                ", url='" + url + '\'' +
                ", createDate=" + createDate +
                ", hit=" + hit +
                '}';
    }
}
