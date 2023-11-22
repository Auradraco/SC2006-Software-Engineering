// Data model for the Users table in database
package com.syntax.symphony.User;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.syntax.symphony.Favourite.Favourite;
import com.syntax.symphony.History.ParkingHistory;
import com.syntax.symphony.ParkedLocation.Parked;
import com.syntax.symphony.Preference.Preference;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;

    @JsonManagedReference(value="UserToParked")
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional = true)
    @PrimaryKeyJoinColumn
    private Parked parked;

    @JsonManagedReference(value="UserToPref")
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Preference preference;
    
    //@JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Favourite> favourite;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    List<ParkingHistory> history = new ArrayList<>();

    public User() {}

    public Long getId() { return userId; }
    public void setId(Long userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Parked getParked() { return parked; }
    public void setParked(Parked parked) { this.parked = parked; }

    public Preference getPref() { return preference; }
    public void setPref(Preference preference) { this.preference = preference; }

    public List<Favourite> getFavourite() { return this.favourite; }
    public void setFavourite(List<Favourite> favourite) { this.favourite = favourite; } 
    public void addFavourite(Favourite fav) { this.favourite.add(fav); }

    public List<ParkingHistory> getParkingHistories(){
        return history;
    }
    public void setParkingHistory(List<ParkingHistory> history){
        this.history = history;
    }
}