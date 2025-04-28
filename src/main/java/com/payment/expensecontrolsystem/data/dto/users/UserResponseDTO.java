package com.payment.expensecontrolsystem.data.dto.users;

import java.util.Date;

public class UserResponseDTO {
    private final Long id;
    private final String name;
    private final String email;
    private final Date createdAt;

    private UserResponseDTO(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.createdAt = builder.createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public static class Builder {
        private Long id;
        private String name;
        private String email;
        private Date createdAt;

        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder email(String email){
            this.email = email;
            return this;
        }

        public Builder createdAt(Date createdAt){
            this.createdAt = createdAt;
            return this;
        }

        public UserResponseDTO build(){
            return new UserResponseDTO(this);
        }
    }


}
