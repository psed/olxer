/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olxer.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@XmlRootElement
public class Ad {

    private String url;
    private long criteriaId;
    private int price;

    public Ad() {
    }

    public Ad(String url, long criteriaId, int price) {
        this.url = url;
        this.criteriaId = criteriaId;
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    @XmlElement
    public void setUrl(String url) {
        this.url = url;
    }

    public long getCriteriaId() {
        return criteriaId;
    }

    @XmlElement
    public void setCriteriaId(long adId) {
        this.criteriaId = adId;
    }

    public int getPrice() {
        return price;
    }

    @XmlElement
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Ad{" + "url=" + url + ", criteriaId=" + criteriaId + ", price=" + price + '}';
    }

}
