package com.kanivets.shares.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "shares", schema = "public", catalog = "SharesDB")
public class SharesEntity {
    private long id;
    private long amount;
    private long authorizedFundSize;
    private long codeedrpou;
    private String comment;
    private long faceValue;
    private long paidStateDuty;
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

    @Basic
    @Column(name = "amount", nullable = false)
    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "authorized_fund_size", nullable = false)
    public long getAuthorizedFundSize() {
        return authorizedFundSize;
    }

    public void setAuthorizedFundSize(long authorizedFundSize) {
        this.authorizedFundSize = authorizedFundSize;
    }

    @Basic
    @Column(name = "codeedrpou", nullable = false)
    public long getCodeedrpou() {
        return codeedrpou;
    }

    public void setCodeedrpou(long codeedrpou) {
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
    @Column(name = "face_value", nullable = false)
    public long getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(long faceValue) {
        this.faceValue = faceValue;
    }

    @Basic
    @Column(name = "paid_state_duty", nullable = false)
    public long getPaidStateDuty() {
        return paidStateDuty;
    }

    public void setPaidStateDuty(long paidStateDuty) {
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
        SharesEntity that = (SharesEntity) o;
        return id == that.id &&
                amount == that.amount &&
                authorizedFundSize == that.authorizedFundSize &&
                codeedrpou == that.codeedrpou &&
                faceValue == that.faceValue &&
                paidStateDuty == that.paidStateDuty &&
                Objects.equals(comment, that.comment) &&
                Objects.equals(releaseDate, that.releaseDate) &&
                Objects.equals(totalFaceValue, that.totalFaceValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, authorizedFundSize, codeedrpou, comment, faceValue, paidStateDuty, releaseDate, totalFaceValue);
    }
}
