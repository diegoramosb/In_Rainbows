package com.inrainbows.persistence.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * @author diego on 19/07/2018.
 */
@Entity
public abstract class BaseEntity {

    @ColumnInfo(name = "ID")
    @PrimaryKey(autoGenerate = true)
    protected long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
