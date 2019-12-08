package com.kanivets.shares.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "shares_aud", schema = "public", catalog = "SharesDB")
@IdClass(SharesAudEntityPK.class)
public class SharesAudEntity {
    private long id;
    private int rev;
    private Short revtype;
    private Long amount;
    private Long authorizedFundSize;
    private Long codeedrpou;
    private String comment;
    private Long faceValue;
    private Long paidStateDuty;
    private Timestamp releaseDate;
    private Long totalFaceValue;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Id
    @Column(name = "rev", nullable = false)
    public int getRev() {
        return rev;
    }

    public void setRev(int rev) {
        this.rev = rev;
    }

    @Basic
    @Column(name = "revtype")
    public Short getRevtype() {
        return revtype;
    }

    public void setRevtype(Short revtype) {
        this.revtype = revtype;
    }

    @Basic
    @Column(name = "amount")
    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "authorized_fund_size")
    public Long getAuthorizedFundSize() {
        return authorizedFundSize;
    }

    public void setAuthorizedFundSize(Long authorizedFundSize) {
        this.authorizedFundSize = authorizedFundSize;
    }

    @Basic
    @Column(name = "codeedrpou")
    public Long getCodeedrpou() {
        return codeedrpou;
    }

    public void setCodeedrpou(Long codeedrpou) {
        this.codeedrpou = codeedrpou;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "face_value")
    public Long getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(Long faceValue) {
        this.faceValue = faceValue;
    }

    @Basic
    @Column(name = "paid_state_duty")
    public Long getPaidStateDuty() {
        return paidStateDuty;
    }

    public void setPaidStateDuty(Long paidStateDuty) {
        this.paidStateDuty = paidStateDuty;
    }

    @Basic
    @Column(name = "release_date")
    public Timestamp getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Timestamp releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Basic
    @Column(name = "total_face_value")
    public Long getTotalFaceValue() {
        return totalFaceValue;
    }

    public void setTotalFaceValue(Long totalFaceValue) {
        this.totalFaceValue = totalFaceValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SharesAudEntity that = (SharesAudEntity) o;
        return id == that.id &&
                rev == that.rev &&
                Objects.equals(revtype, that.revtype) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(authorizedFundSize, that.authorizedFundSize) &&
                Objects.equals(codeedrpou, that.codeedrpou) &&
                Objects.equals(comment, that.comment) &&
                Objects.equals(faceValue, that.faceValue) &&
                Objects.equals(paidStateDuty, that.paidStateDuty) &&
                Objects.equals(releaseDate, that.releaseDate) &&
                Objects.equals(totalFaceValue, that.totalFaceValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rev, revtype, amount, authorizedFundSize, codeedrpou, comment, faceValue, paidStateDuty, releaseDate, totalFaceValue);
    }
}
