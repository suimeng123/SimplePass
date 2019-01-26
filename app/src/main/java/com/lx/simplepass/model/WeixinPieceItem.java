package com.lx.simplepass.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * com.lx.simplepass.model
 * SimplePass
 * Created by lixiao2
 * 2019/1/25.
 */

public class WeixinPieceItem implements Parcelable {

    private String cid;
    private String hitCount;
    private String id;
    private String pubTime;
    private String sourceUrl;
    private String subTitle;
    private String thumbnails;
    private String title;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getHitCount() {
        return hitCount;
    }

    public void setHitCount(String hitCount) {
        this.hitCount = hitCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(String thumbnails) {
        this.thumbnails = thumbnails;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cid);
        dest.writeString(this.hitCount);
        dest.writeString(this.id);
        dest.writeString(this.pubTime);
        dest.writeString(this.sourceUrl);
        dest.writeString(this.subTitle);
        dest.writeString(this.thumbnails);
        dest.writeString(this.title);
    }

    public WeixinPieceItem() {
    }

    protected WeixinPieceItem(Parcel in) {
        this.cid = in.readString();
        this.hitCount = in.readString();
        this.id = in.readString();
        this.pubTime = in.readString();
        this.sourceUrl = in.readString();
        this.subTitle = in.readString();
        this.thumbnails = in.readString();
        this.title = in.readString();
    }

    public static final Parcelable.Creator<WeixinPieceItem> CREATOR = new Parcelable.Creator<WeixinPieceItem>() {
        @Override
        public WeixinPieceItem createFromParcel(Parcel source) {
            return new WeixinPieceItem(source);
        }

        @Override
        public WeixinPieceItem[] newArray(int size) {
            return new WeixinPieceItem[size];
        }
    };
}
