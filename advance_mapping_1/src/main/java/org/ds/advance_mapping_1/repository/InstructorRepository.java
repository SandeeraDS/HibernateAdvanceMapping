package org.ds.advance_mapping_1.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.ds.advance_mapping_1.bean.InstructorBean;
import org.ds.advance_mapping_1.bean.InstructorDetailsBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InstructorRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public void persist(InstructorBean instructorBean) {
        entityManager.persist(instructorBean);
    }

    public void merge(InstructorBean instructorBean) {
        entityManager.merge(instructorBean);
    }

    public List<InstructorBean> getAll(){
        TypedQuery<InstructorBean> typedQuery = entityManager.createQuery("select a from InstructorBean a",InstructorBean.class);
        return typedQuery.getResultList();
    }

    public InstructorBean getById(long id){
       return entityManager.find(InstructorBean.class,id);
    }

    public void remove(InstructorBean instructorBean){
        entityManager.remove(instructorBean);
    }

    public InstructorDetailsBean getByInstructorDetailsId(long instructorDetailsId){
        return entityManager.find(InstructorDetailsBean.class,instructorDetailsId);
    }

    public void removeInstructorDetailsId(InstructorDetailsBean instructorDetailsBean){
        entityManager.remove(instructorDetailsBean);
    }



}
