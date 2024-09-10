package com.example.yone3.springwebsample.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class UsrSpecification<T> {

    /**
     * reviewerNameで検索
     */
    public Specification<T> reviewerNameEqual(String reviewerName) {

        return reviewerName == null ? null : new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root,
                                         CriteriaQuery<?> query, CriteriaBuilder builder) {

                return builder.equal(root.get("reviewerName"), reviewerName);
            }
        };
    }

    /**
     * bookNameで検索
     */
    public Specification<T> bookNameEqual(String bookName) {

        return bookName == null ? null : new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root,
                                         CriteriaQuery<?> query, CriteriaBuilder builder) {

                return builder.equal(root.get("bookName"), bookName);
            }
        };
    }

    /**
     * ipAddressで検索
     */
    public Specification<T> ipAddressEqual(String ipAddress) {

        return ipAddress == null ? null : new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root,
                                         CriteriaQuery<?> query, CriteriaBuilder builder) {

                return builder.equal(root.get("ipAddress"), ipAddress);
            }
        };
    }
}
