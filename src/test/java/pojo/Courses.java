package pojo;

import java.util.List;

public class Courses {
    private List<webAutomation> webAutomation;
    private List<api> api;
    private List<mobile> mobile;

    public List<pojo.webAutomation> getWebAutomation() {
        return webAutomation;
    }
    public void setWebAutomation(List<webAutomation> webAutomation) {
        this.webAutomation = webAutomation;
    }

    public List<pojo.api> getApi() {
        return api;
    }
    public void setApi(List<api> api) {
        this.api = api;
    }

    public List<pojo.mobile> getMobile() {
        return mobile;
    }
    public void setMobile(List<mobile> mobile) {
        this.mobile = mobile;
    }



}
