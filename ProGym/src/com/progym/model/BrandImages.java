package com.progym.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "BRAND_IMAGES")
public class BrandImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String brandName;

    @Column
    private String owner_1;

    @Column
    private String owner_2;

    @Column
    private String trainer_1;

    @Column
    private String trainer_2;

    @Column
    private String banner_1;

    @Column
    private String login_brand_logo;

    @Column
    private String login_screen_banner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getOwner_1() {
        return owner_1+"?"+new Date();
    }

    public void setOwner_1(String owner_1) {
        this.owner_1 = owner_1;
    }

    public String getOwner_2() {
        return owner_2+"?"+new Date();
    }

    public void setOwner_2(String owner_2) {
        this.owner_2 = owner_2;
    }

    public String getTrainer_1() {
        return trainer_1+"?"+new Date();
    }

    public void setTrainer_1(String trainer_1) {
        this.trainer_1 = trainer_1;
    }

    public String getTrainer_2() {
        return trainer_2+"?"+new Date();
    }

    public void setTrainer_2(String trainer_2) {
        this.trainer_2 = trainer_2;
    }

    public String getBanner_1() {
        return banner_1+"?"+new Date();
    }

    public void setBanner_1(String banner_1) {
        this.banner_1 = banner_1;
    }

    public String getLogin_brand_logo() {
        return login_brand_logo+"?"+new Date();
    }

    public void setLogin_brand_logo(String login_brand_logo) {
        this.login_brand_logo = login_brand_logo;
    }

    public String getLogin_screen_banner() {
        return login_screen_banner+"?"+new Date();
    }

    public void setLogin_screen_banner(String login_screen_banner) {
        this.login_screen_banner = login_screen_banner;
    }
}
