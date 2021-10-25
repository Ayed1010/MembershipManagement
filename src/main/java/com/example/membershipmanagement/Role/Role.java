package com.example.membershipmanagement.Role;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/*import org.springframework.security.core.GrantedAuthority;*/

import java.util.Collection;

@Document
public class Role /*implements GrantedAuthority */{
    @Id
    private String id ;
    private String name ;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

  /*  @Override
    public String getAuthority() {
        return null;
    }*/
}
