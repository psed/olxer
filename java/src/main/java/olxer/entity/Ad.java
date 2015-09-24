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

    private int id;
    private String adId;

    public Ad() {
    }

    public Ad(int id, String adId) {
        this.id = id;
        this.adId = adId;
    }

    public int getId() {
        return id;
    }

    @XmlElement
    public void setId(int id) {
        this.id = id;
    }

    public String getAdId() {
        return adId;
    }

    @XmlElement
    public void setAdId(String adId) {
        this.adId = adId;
    }

}
