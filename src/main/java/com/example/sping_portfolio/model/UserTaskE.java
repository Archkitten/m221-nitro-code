package com.example.sping_portfolio.model;

import java.util.Collection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserTaskE extends User {

    private JSONObject taskList;

    public UserTaskE(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.taskList = null;
    }

    public UserTaskE(String username, String password, boolean enabled,
                     boolean accountNonExpired, boolean credentialsNonExpired,
                     boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public UserTaskE(User user) {
        super(user.getUsername(), user.getPassword(), user.getAuthorities());
        this.taskList = null;
    }

    public void addTask(JSONObject task, long unixDate) {
        JSONArray array = new JSONArray();
        array.add(task);
        taskList.put(unixDate, array);
    }

    public JSONObject getTaskList() {
        return taskList;
    }
}