package com.example.pm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "attachment")
public class Attachment {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "attachment_name", length = 128)
    private String attachmentName;

    @Column(name = "attachment_uri", length = 128)
    private String attachmentUri;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
}

