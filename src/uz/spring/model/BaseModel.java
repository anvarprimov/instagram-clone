package uz.spring.model;

import java.util.Date;
import java.util.UUID;

public abstract class BaseModel {
    private UUID id;
    private String name;
    private Date date;

    public BaseModel() {
    }

    public UUID getId() {
        return id;
    }

    public void setId() {
        this.id = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate() {
        this.date = new Date();
    }
}
