package com.example.flyaudioqrcode.model.domain;

public class ServiceInfo {

    /**
     * msg : success
     * code : 200
     * data : {"msg":"同意登录","flag":"40002","userId":"1331204105523171329","token":"a86a0d4fd36544eeac93b735377dada0"}
     */

    private String msg;
    private int code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * msg : 同意登录
         * flag : 40002
         * userId : 1331204105523171329
         * token : a86a0d4fd36544eeac93b735377dada0
         */

        private String msg;
        private String flag;
        private String userId;
        private String token;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
