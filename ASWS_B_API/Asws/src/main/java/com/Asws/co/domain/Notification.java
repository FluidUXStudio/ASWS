package com.Asws.co.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "notifictions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notification {


    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long notificationId;
	
	private String message;
	
	private String createdAt;
	
	private boolean isRead;
    
}
