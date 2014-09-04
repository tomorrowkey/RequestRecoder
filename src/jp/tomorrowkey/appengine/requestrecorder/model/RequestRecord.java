package jp.tomorrowkey.appengine.requestrecorder.model;

import com.google.appengine.api.datastore.Key;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

@Model
public class RequestRecord {

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute
    private int count;

    @Attribute
    private String userAgent;

    @Attribute
    private long createAt;

    @Attribute
    private long updateAt;

    public RequestRecord() {
    }

    public RequestRecord(HttpServletRequest request) {
        userAgent = request.getHeader("User-Agent");
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void incrementCount() {
        count++;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    public Date getCreateAtAsDate() {
        return new Date(createAt);
    }

    public long getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(long updateAt) {
        this.updateAt = updateAt;
    }

    public Date getUpdateAtAsDate() {
        return new Date(updateAt);
    }

}
