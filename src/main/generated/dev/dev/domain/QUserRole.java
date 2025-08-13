package dev.dev.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserRole is a Querydsl query type for UserRole
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QUserRole extends EnumPath<UserRole> {

    private static final long serialVersionUID = 955213241L;

    public static final QUserRole userRole = new QUserRole("userRole");

    public QUserRole(String variable) {
        super(UserRole.class, forVariable(variable));
    }

    public QUserRole(Path<UserRole> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserRole(PathMetadata metadata) {
        super(UserRole.class, metadata);
    }

}

