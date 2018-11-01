package com.example.nadavlotan.gameofthrones;

class FamilyMember {

    String name;
    int picture;

    public FamilyMember(String name,int picture){
        this.name = name;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public int getPicture() {
        return picture;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }
}
