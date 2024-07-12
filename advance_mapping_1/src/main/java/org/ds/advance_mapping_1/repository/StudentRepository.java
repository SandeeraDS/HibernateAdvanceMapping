package org.ds.advance_mapping_1.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.ds.advance_mapping_1.bean.StudentBean;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public StudentBean findStudentAndCoursesByStudentId(long studentId) {
        TypedQuery<StudentBean> query = entityManager.createQuery("select c from StudentBean c join fetch c.courseBeanList where c.id = :id", StudentBean.class);
        query.setParameter("id", studentId);
        return query.getSingleResult();
    }

    public StudentBean findById(long studentId) {
        return entityManager.find(StudentBean.class, studentId);
    }

    public void merge(StudentBean studentBean) {
        entityManager.merge(studentBean);
    }

    public void save(StudentBean studentBean) {
        entityManager.persist(studentBean);
    }

    public void delete(StudentBean studentBean) {
        entityManager.remove(studentBean);
    }
}
