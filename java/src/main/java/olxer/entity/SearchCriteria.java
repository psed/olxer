/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olxer.entity;

/**
 *
 * @author user
 */
public class SearchCriteria {

    private long id;
    private String criteriaUrl;
    private String emailTo;

    public SearchCriteria(long id, String criteriaUrl, String emailTo) {
        this.id = id;
        this.criteriaUrl = criteriaUrl;
        this.emailTo = emailTo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCriteriaUrl() {
        return criteriaUrl;
    }

    public void setCriteriaUrl(String criteriaUrl) {
        this.criteriaUrl = criteriaUrl;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

}
