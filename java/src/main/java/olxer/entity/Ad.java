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
    private String criteriaId;

    public Ad() {
    }

    public Ad(String url, String adId) {
        this.url = url;
        this.criteriaId = adId;
    }

    public String getUrl() {
        return url;
    }

    @XmlElement
    public void setUrl(String url) {
        this.url = url;
    }

    public String getCriteriaId() {
        return criteriaId;
    }

    @XmlElement
    public void setCriteriaId(String adId) {
        this.criteriaId = adId;
    }

}
