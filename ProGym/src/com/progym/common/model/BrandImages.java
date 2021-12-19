package com.progym.common.model;

import javax.persistence.*;

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

    @Column
    private String appBanner_1;

    @Column
    private String appBanner_2;

    @Column
    private String appBanner_3;

    @Column
    private String appBanner_4;

    @Column
    private String appAdvertise_1;

    @Column
    private String appAdvertise_2;

    @Column
    private String appAdvertise_3;

    @Column
    private String appAdvertise_4;

    @Column
    private String appBanner_1Contact;

    @Column
    private String appBanner_2Contact;;

    @Column
    private String appBanner_3Contact;;

    @Column
    private String appBanner_4Contact;;

    @Column
    private String appAdvertise_1Contact;;

    @Column
    private String appAdvertise_2Contact;;

    @Column
    private String appAdvertise_3Contact;;

    @Column
    private String appAdvertise_4Contact;;

    @Column
    private String h1;

    @Column
    private String h2;

    @Column
    private String h3;

    @Column
    private String h4;

    @Column
    private String h5;

    @Column
    private String upgradePlan1_img;

    @Column
    private String upgradePlan2_img;

    @Column
    private String upgradePlan3_img;

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
        return owner_1;
    }

    public void setOwner_1(String owner_1) {
        this.owner_1 = owner_1;
    }

    public String getOwner_2() {
        return owner_2;
    }

    public void setOwner_2(String owner_2) {
        this.owner_2 = owner_2;
    }

    public String getTrainer_1() {
        return trainer_1;
    }

    public void setTrainer_1(String trainer_1) {
        this.trainer_1 = trainer_1;
    }

    public String getTrainer_2() {
        return trainer_2;
    }

    public void setTrainer_2(String trainer_2) {
        this.trainer_2 = trainer_2;
    }

    public String getBanner_1() {
        return banner_1;
    }

    public void setBanner_1(String banner_1) {
        this.banner_1 = banner_1;
    }

    public String getLogin_brand_logo() {
        return login_brand_logo;
    }

    public void setLogin_brand_logo(String login_brand_logo) {
        this.login_brand_logo = login_brand_logo;
    }

    public String getLogin_screen_banner() {
        return login_screen_banner;
    }

    public void setLogin_screen_banner(String login_screen_banner) {
        this.login_screen_banner = login_screen_banner;
    }

    public String getAppBanner_1() {
        return appBanner_1;
    }

    public void setAppBanner_1(String appBanner_1) {
        this.appBanner_1 = appBanner_1;
    }

    public String getAppBanner_2() {
        return appBanner_2;
    }

    public void setAppBanner_2(String appBanner_2) {
        this.appBanner_2 = appBanner_2;
    }

    public String getAppBanner_3() {
        return appBanner_3;
    }

    public void setAppBanner_3(String appBanner_3) {
        this.appBanner_3 = appBanner_3;
    }

    public String getAppBanner_4() {
        return appBanner_4;
    }

    public void setAppBanner_4(String appBanner_4) {
        this.appBanner_4 = appBanner_4;
    }

    public String getAppAdvertise_1() {
        return appAdvertise_1;
    }

    public void setAppAdvertise_1(String appAdvertise_1) {
        this.appAdvertise_1 = appAdvertise_1;
    }

    public String getAppAdvertise_2() {
        return appAdvertise_2;
    }

    public void setAppAdvertise_2(String appAdvertise_2) {
        this.appAdvertise_2 = appAdvertise_2;
    }

    public String getAppAdvertise_3() {
        return appAdvertise_3;
    }

    public void setAppAdvertise_3(String appAdvertise_3) {
        this.appAdvertise_3 = appAdvertise_3;
    }

    public String getAppAdvertise_4() {
        return appAdvertise_4;
    }

    public void setAppAdvertise_4(String appAdvertise_4) {
        this.appAdvertise_4 = appAdvertise_4;
    }

    public String getH1() {
        return h1;
    }

    public void setH1(String h1) {
        this.h1 = h1;
    }

    public String getH2() {
        return h2;
    }

    public void setH2(String h2) {
        this.h2 = h2;
    }

    public String getH3() {
        return h3;
    }

    public void setH3(String h3) {
        this.h3 = h3;
    }

    public String getH4() {
        return h4;
    }

    public void setH4(String h4) {
        this.h4 = h4;
    }

    public String getH5() {
        return h5;
    }

    public void setH5(String h5) {
        this.h5 = h5;
    }

    public String getAppBanner_1Contact() {
        return appBanner_1Contact;
    }

    public void setAppBanner_1Contact(String appBanner_1Contact) {
        this.appBanner_1Contact = appBanner_1Contact;
    }

    public String getAppBanner_2Contact() {
        return appBanner_2Contact;
    }

    public void setAppBanner_2Contact(String appBanner_2Contact) {
        this.appBanner_2Contact = appBanner_2Contact;
    }

    public String getAppBanner_3Contact() {
        return appBanner_3Contact;
    }

    public void setAppBanner_3Contact(String appBanner_3Contact) {
        this.appBanner_3Contact = appBanner_3Contact;
    }

    public String getAppBanner_4Contact() {
        return appBanner_4Contact;
    }

    public void setAppBanner_4Contact(String appBanner_4Contact) {
        this.appBanner_4Contact = appBanner_4Contact;
    }

    public String getAppAdvertise_1Contact() {
        return appAdvertise_1Contact;
    }

    public void setAppAdvertise_1Contact(String appAdvertise_1Contact) {
        this.appAdvertise_1Contact = appAdvertise_1Contact;
    }

    public String getAppAdvertise_2Contact() {
        return appAdvertise_2Contact;
    }

    public void setAppAdvertise_2Contact(String appAdvertise_2Contact) {
        this.appAdvertise_2Contact = appAdvertise_2Contact;
    }

    public String getAppAdvertise_3Contact() {
        return appAdvertise_3Contact;
    }

    public void setAppAdvertise_3Contact(String appAdvertise_3Contact) {
        this.appAdvertise_3Contact = appAdvertise_3Contact;
    }

    public String getAppAdvertise_4Contact() {
        return appAdvertise_4Contact;
    }

    public void setAppAdvertise_4Contact(String appAdvertise_4Contact) {
        this.appAdvertise_4Contact = appAdvertise_4Contact;
    }

    public String getUpgradePlan1_img() {
        return upgradePlan1_img;
    }

    public void setUpgradePlan1_img(String upgradePlan1_img) {
        this.upgradePlan1_img = upgradePlan1_img;
    }

    public String getUpgradePlan2_img() {
        return upgradePlan2_img;
    }

    public void setUpgradePlan2_img(String upgradePlan2_img) {
        this.upgradePlan2_img = upgradePlan2_img;
    }

    public String getUpgradePlan3_img() {
        return upgradePlan3_img;
    }

    public void setUpgradePlan3_img(String upgradePlan3_img) {
        this.upgradePlan3_img = upgradePlan3_img;
    }
}
