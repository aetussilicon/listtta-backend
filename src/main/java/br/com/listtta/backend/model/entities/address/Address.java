package br.com.listtta.backend.model.entities.address;

import br.com.listtta.backend.model.entities.users.Users;
import br.com.listtta.backend.model.enums.CitiesZone;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users_address")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "addressId")
public class Address {

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq_generator")
    @SequenceGenerator(name = "address_seq_generator", sequenceName = "address_seq", allocationSize = 1)
    private Long addressId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users users;
    private String puid;
    private String state;
    private String city;

    @Column(name = "city_zone")
    @Enumerated(EnumType.STRING)
    private CitiesZone cityZone;
    private String district;
    private String street;
//    private int number;
    private String complement;

    @Column(name = "zip_code")
    private String zipCode;
}
