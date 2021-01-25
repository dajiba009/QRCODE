package com.example.flyaudioqrcode.model.domain;

public class QrCodeInfo {

    /**
     * msg : success
     * code : 200
     * data : {"msg":"success","flag":"10000","tag":"flyAudioCarInfo","value":"c6fc50b8180f4fd29140c4f3eef70579"}
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
         * msg : success
         * flag : 10000
         * tag : flyAudioCarInfo
         * value : c6fc50b8180f4fd29140c4f3eef70579
         */

        private String msg;
        private String flag;
        private String tag;
        private String value;

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

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
