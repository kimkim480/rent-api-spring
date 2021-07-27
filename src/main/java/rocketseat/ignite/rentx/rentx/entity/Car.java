package rocketseat.ignite.rentx.rentx.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cars")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, name = "daily_rate")
    private Double dailyRate;

    @Column(nullable = false)
    private Boolean available;

    @Column(nullable = false, name = "license_plate")
    private String licensePlate;

    @Column(nullable = false, name = "fine_amount")
    private Double fineAmount;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "specifications_cars",
            joinColumns =  {@JoinColumn(
                    name = "car_id",
                    referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(
                    name = "specification_id",
                    referencedColumnName = "id")})
    private List<Specification> specifications;

}
