package com.example.tt.repositories.specifications;

import com.example.tt.repositories.GroupRepo;
import org.springframework.data.jpa.domain.Specification;
import com.example.tt.models.entities.Visitant;

public class VisitantSpecifications {

    public static Specification<Visitant> buildSpecification(String searchTerm) {
        return Specification.where(VisitantSpecifications.containsName(searchTerm));
    }

    private static Specification<Visitant> containsName(String searchTerm) {
        return (root, query, criteriaBuilder) -> {
            if (searchTerm == null || searchTerm.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            String likePattern = "%" + searchTerm + "%";
            return criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), likePattern.toLowerCase()),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), likePattern.toLowerCase()),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("middleName")), likePattern.toLowerCase())
            );
        };
    }

}
