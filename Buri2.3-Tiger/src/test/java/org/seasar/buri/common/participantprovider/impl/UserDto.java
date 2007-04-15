package org.seasar.buri.common.participantprovider.impl;

public class UserDto {

    private long userId;
    private String loginName;

    public UserDto(long userId, String loginName) {
        this.userId = userId;
        this.loginName = loginName;
    }

    public UserDto() {
        super();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return userId + ":" + loginName;
    }

}
