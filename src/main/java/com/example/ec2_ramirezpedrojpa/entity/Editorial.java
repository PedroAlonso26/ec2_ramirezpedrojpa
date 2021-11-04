/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ec2_ramirezpedrojpa.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author usuario
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "editorial")
public class Editorial implements Serializable{
    private static final long serialVersionUID = 3754851399214200439L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ideditorial")
    private int id;
    @Column(name = "nom_edi")
    private String nomedi;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idlibro")
    private Set<Libro> libros;
    

}
