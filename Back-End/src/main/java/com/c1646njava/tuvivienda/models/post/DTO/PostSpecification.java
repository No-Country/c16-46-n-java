package com.c1646njava.tuvivienda.models.post.DTO;

import com.c1646njava.tuvivienda.models.post.Post;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class PostSpecification {

    public static Specification<Post> columnEqual(List<FilterDTO> filterDTOList)
    {
        return new Specification<Post>()
        {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)
            {
                List<Predicate> predicates = new ArrayList<>();
                filterDTOList.forEach(filter -> {

                    Predicate predicate = criteriaBuilder.equal(root.get(filter.getColumnName()), filter.getColumnValue());
                    predicates.add(predicate);
                });

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}










