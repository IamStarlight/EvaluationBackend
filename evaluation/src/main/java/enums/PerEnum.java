package enums;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum PerEnum implements IEnum<String> {
    ROLE_NORMAL("1","ROLE_STUDENT"),
    ROLE_COMPANY("2","ROLE_TEACHER"),
    ROLE_ADMIN("3","ROLE_ADMIN");

    private String per;
    private String desc;

    PerEnum(String per, String desc){
        this.per = per;
        this.desc = desc;
    }

    @Override
    public String toString(){
        return desc;
    }

    @Override
    public String getValue() {
        return per;
    }
}