package org.ds.advance_mapping_1.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.ds.advance_mapping_1.bean.CourseBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void persist(CourseBean courseBean) {
        entityManager.persist(courseBean);
    }

    public void merge(CourseBean courseBean) {
        entityManager.merge(courseBean);
    }

    public List<CourseBean> getAll() {
        TypedQuery<CourseBean> typedQuery = entityManager.createQuery("select a from CourseBean a", CourseBean.class);
        return typedQuery.getResultList();
    }

    public CourseBean getById(long id) {
        return entityManager.find(CourseBean.class, id);
    }

    public void delete(CourseBean courseBean) {
        entityManager.remove(courseBean);
    }

    public CourseBean findCourseAndStudentsByCourseId(long courseId) {
        TypedQuery<CourseBean> query = entityManager.createQuery("select c from CourseBean c join fetch c.studentBeanList where c.id = :id", CourseBean.class);
        query.setParameter("id", courseId);
        return query.getSingleResult();
    }
}
