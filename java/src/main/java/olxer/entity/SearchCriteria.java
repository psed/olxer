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
public class SearchCriteria {

    private long id;
    private String criteriaUrl;
    private String emailTo;

    public SearchCriteria() {
    }

    public SearchCriteria(long id, String criteriaUrl, String emailTo) {
        this.id = id;
        this.criteriaUrl = criteriaUrl;
        this.emailTo = emailTo;
    }

    public long getId() {
        return id;
    }

    @XmlElement
    public void setId(long id) {
        this.id = id;
    }

    public String getCriteriaUrl() {
        return criteriaUrl;
    }

    @XmlElement
    public void setCriteriaUrl(String criteriaUrl) {
        this.criteriaUrl = criteriaUrl;
    }

    public String getEmailTo() {
        return emailTo;
    }

    @XmlElement
    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

}
