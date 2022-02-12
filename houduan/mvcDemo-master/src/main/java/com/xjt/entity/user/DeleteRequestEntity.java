package com.xjt.entity.user;

import java.util.List;

public class DeleteRequestEntity {
    //要删除的userId
    private List<Integer> userIds;

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }
}
