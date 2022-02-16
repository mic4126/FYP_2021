package hk.edu.cityu.cs.FYP.AIRegistry.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@PreAuthorize("hasAuthority('PROJECT_'.concat(#projectId)) or hasRole('admin')")
public @interface IsProjectDeveloperOrAdmin {
    
}
