package rh.calorietracker.data.impl;

import java.io.Serializable;

public abstract class DatabaseEntity implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
