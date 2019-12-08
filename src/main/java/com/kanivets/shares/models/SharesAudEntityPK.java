package com.kanivets.shares.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class SharesAudEntityPK implements Serializable {
    private long id;
    private int rev;

    @Column(name = "id", nullable = false)
    @Id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "rev", nullable = false)
    @Id
    public int getRev() {
        return rev;
    }

    public void setRev(int rev) {
        this.rev = rev;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SharesAudEntityPK that = (SharesAudEntityPK) o;
        return id == that.id &&
                rev == that.rev;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rev);
    }
}
