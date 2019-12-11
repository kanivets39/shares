package com.kanivets.shares.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "revinfo", schema = "public", catalog = "SharesDB")
public class RevinfoEntity {
    private int rev;
    private Long revtstmp;

    @Id
    @Column(name = "rev", nullable = false)
    public int getRev() {
        return rev;
    }

    public void setRev(int rev) {
        this.rev = rev;
    }

    @Basic
    @Column(name = "revtstmp")
    public Long getRevtstmp() {
        return revtstmp;
    }

    public void setRevtstmp(Long revtstmp) {
        this.revtstmp = revtstmp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RevinfoEntity that = (RevinfoEntity) o;
        return rev == that.rev &&
                Objects.equals(revtstmp, that.revtstmp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rev, revtstmp);
    }
}
